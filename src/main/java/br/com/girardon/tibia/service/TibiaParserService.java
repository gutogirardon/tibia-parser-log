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



        return null;
    }

    private List<Integer> findHealingValues(String logContent) {
        List<Integer> healingValues = new ArrayList<>();
        Pattern pattern = Pattern.compile(DAMAGE_HEALED_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            int healingValue = Integer.parseInt(matcher.group(1));
            healingValues.add(healingValue);
        }

        logger.info("Healing values: {} self-healing actions were found.", healingValues.size());
        return healingValues;
    }

    private List<Integer> findDamageTakenValues(String logContent) {
        List<Integer> damageValues = new ArrayList<>();
        Pattern pattern = Pattern.compile(DAMAGE_TAKEN_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            int damageValue = Integer.parseInt(matcher.group(1));
            damageValues.add(damageValue);
        }

        logger.info("Damage taken values: {} damage actions were found.", damageValues.size());
        return damageValues;
    }
}
