package br.com.girardon.tibia.service;

import br.com.girardon.tibia.utils.ReadLogFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TibiaParserService {

    private static final Logger logger = LoggerFactory.getLogger(TibiaParserService.class);
    private static final String DAMAGE_HEALED_LOG_PATTERN = "\\d{2}:\\d{2} You healed yourself for (\\d+) hitpoints\\.";
    private static final String DAMAGE_TAKEN_LOG_PATTERN = "\\d{2}:\\d{2} You lose (\\d+) hitpoints*";

    public TibiaParserService parseLogFile() {
        String logContent = ReadLogFileUtils.readLogFile();
        List<Integer> healingValues = findHealingValues(logContent);
        List<Integer> damageValues = findDamageTakenValues(logContent);

        int totalHealing = calculateTotalHealing(healingValues);
        int totalDamageTaken = calculateTotalDamageTaken(damageValues);

        return null;
    }

    private List<Integer> findValues(String logContent, String logPattern) {
        List<Integer> values = new ArrayList<>();
        Pattern pattern = Pattern.compile(logPattern);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            values.add(value);
        }

        return values;
    }

    private List<Integer> findHealingValues(String logContent) {
        String logPattern = DAMAGE_HEALED_LOG_PATTERN;
        List<Integer> healingValues = findValues(logContent, logPattern);

        logger.info("Healing values: {} self-healing actions were found.", healingValues.size());
        return healingValues;
    }

    private List<Integer> findDamageTakenValues(String logContent) {
        String logPattern = DAMAGE_TAKEN_LOG_PATTERN;
        List<Integer> damageValues = findValues(logContent, logPattern);

        logger.info("Damage taken values: {} damage actions were found.", damageValues.size());
        return damageValues;
    }

    private int calculateTotal(List<Integer> values) {
        int total = values.stream().mapToInt(Integer::intValue).sum();
        return total;
    }

    private int calculateTotalHealing(List<Integer> healingValues) {
        int totalHealing = calculateTotal(healingValues);
        logger.info("Total healing: {} hitpoints.", totalHealing);
        return totalHealing;
    }

    private int calculateTotalDamageTaken(List<Integer> damageValues) {
        int totalDamageTaken = calculateTotal(damageValues);
        logger.info("Total damage taken: {} hitpoints.", totalDamageTaken);
        return totalDamageTaken;
    }
}