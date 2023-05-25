package br.com.girardon.tibia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DropService {

    private static final Logger logger = LoggerFactory.getLogger(DropService.class);
    private static final String CREATURE_DROP_LOG_PATTERN = "\\d{2}:\\d{2} Loot of a ([\\w\\s]+): (.*)\\.";

    public Map<String, List<String>> findCreatureDropValues(String logContent) {
        Map<String, List<String>> creatureDrops = new HashMap<>();
        Pattern pattern = Pattern.compile(CREATURE_DROP_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            String creature = matcher.group(1);
            String drop = matcher.group(2);

            // Verifica se o monstro já existe no mapa
            if (creatureDrops.containsKey(creature)) {
                List<String> drops = creatureDrops.get(creature);
                drops.add(drop);
            } else {
                // Se o monstro não existe, cria uma nova lista de drops
                List<String> drops = new ArrayList<>();
                drops.add(drop);
                creatureDrops.put(creature, drops);
            }
        }

        logger.info("Creature drops:");
        for (Map.Entry<String, List<String>> entry : creatureDrops.entrySet()) {
            String creature = entry.getKey();
            List<String> drops = entry.getValue();
            logger.info("- Loot of {}: {}", creature, String.join(", ", drops));
        }
        return creatureDrops;
    }
}
