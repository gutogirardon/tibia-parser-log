package br.com.girardon.tibia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DropService {

    private static final Logger logger = LoggerFactory.getLogger(DropService.class);
    private static final String CREATURE_DROP_LOG_PATTERN = "\\d{2}:\\d{2} Loot of a ([\\w\\s]+): (.*)\\.";
    private static final String GOLD_COINS_PATTERN = "(\\d+) gold coin(s)?|a gold coin";

    public Map<String, Integer> findCreatureDropValues(String logContent) {
        Map<String, Integer> creatureDrops = new HashMap<>();
        Pattern pattern = Pattern.compile(CREATURE_DROP_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            String creature = matcher.group(1);
            String drop = matcher.group(2);
            int goldCoins = calculateGoldCoins(drop);

            // Verifica se o monstro já existe no mapa
            if (creatureDrops.containsKey(creature)) {
                int currentGoldCoins = creatureDrops.get(creature);
                creatureDrops.put(creature, currentGoldCoins + goldCoins);
            } else {
                creatureDrops.put(creature, goldCoins);
            }
        }

        logger.info("Creature drops:");
        for (Map.Entry<String, Integer> entry : creatureDrops.entrySet()) {
            String creature = entry.getKey();
            int goldCoins = entry.getValue();
            logger.info("- Loot of {}: {} gold coins", creature, goldCoins);
        }

        return creatureDrops;
    }

    private int calculateGoldCoins(String drop) {
        int goldCoins = 0;
        Pattern pattern = Pattern.compile(GOLD_COINS_PATTERN);
        Matcher matcher = pattern.matcher(drop);

        while (matcher.find()) {
            String coinsString = matcher.group();
            int coins = extractGoldCoins(coinsString);
            goldCoins += coins;
        }

        return goldCoins;
    }

    private int extractGoldCoins(String coinsString) {
        if (coinsString.equals("a gold coin")) {
            return 1;
        } else {
            try {
                String coinsValue = coinsString.split(" ")[0];
                return Integer.parseInt(coinsValue);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }
}
