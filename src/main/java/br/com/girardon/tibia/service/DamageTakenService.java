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
public class DamageTakenService {

    private static final Logger logger = LoggerFactory.getLogger(DamageTakenService.class);
    private static final String DAMAGE_TAKEN_LOG_PATTERN = "\\d{2}:\\d{2} You lose (\\d+) hitpoint*";
    private static final String DAMAGE_TAKEN_BY_MONSTER_LOG_PATTERN = "\\d{2}:\\d{2} You lose (\\d+) hitpoints due to an attack by a ([\\w\\s]+)\\.";

    public List<Integer> findDamageTakenValues(String logContent) {
        List<Integer> damageValues = findValues(logContent);

        logger.info("{} damage actions were found. Total damage taken: {} hitpoints.", damageValues.size(), calculateTotal(damageValues));
        return damageValues;
    }

    public Map<String, Integer> findDamageTakenByMonster(String logContent) {
        Map<String, Integer> damageTakenByMonster = new HashMap<>();
        Pattern pattern = Pattern.compile(DAMAGE_TAKEN_BY_MONSTER_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            int damageValue = Integer.parseInt(matcher.group(1));
            String monster = matcher.group(2);

            damageTakenByMonster.put(monster, damageTakenByMonster.getOrDefault(monster, 0) + damageValue);
        }

        logger.info("Damage taken by monsters:");
        for (Map.Entry<String, Integer> entry : damageTakenByMonster.entrySet()) {
            logger.info("- {} damage taken: {} hitpoints.", entry.getKey(), entry.getValue());
        }
        return damageTakenByMonster;
    }

    public int calculateDamageDifference(String logContent) {
        List<Integer> damageTakenValues = findDamageTakenValues(logContent);
        Map<String, Integer> damageTakenByMonster = findDamageTakenByMonster(logContent);
        int totalDamageTaken = calculateTotal(damageTakenValues);
        int totalDamageTakenByMonsters = calculateTotal(new ArrayList<>(damageTakenByMonster.values()));

        int damageDifference = totalDamageTaken - totalDamageTakenByMonsters;
        logger.info("Total damage taken by unknown origins: {} hitpoints.", damageDifference);
        return damageDifference;
    }

    public int getAllDamageTakenValue(String logContent) {
        List<Integer> damageTakenValues = findDamageTakenValues(logContent);
        return calculateTotal(damageTakenValues);
    }

    private List<Integer> findValues(String logContent) {
        List<Integer> values = new ArrayList<>();
        Pattern pattern = Pattern.compile(DAMAGE_TAKEN_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            values.add(value);
        }

        return values;
    }

    private int calculateTotal(List<Integer> values) {
        return values.stream().mapToInt(Integer::intValue).sum();
    }
}
