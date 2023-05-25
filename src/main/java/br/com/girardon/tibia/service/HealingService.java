package br.com.girardon.tibia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class HealingService {

    private static final Logger logger = LoggerFactory.getLogger(HealingService.class);
    private static final String DAMAGE_HEALED_LOG_PATTERN = "\\d{2}:\\d{2} You healed yourself for (\\d+) hitpoints\\.";

    public List<Integer> findHealingValues(String logContent) {
        List<Integer> healingValues = new ArrayList<>();
        Pattern pattern = Pattern.compile(DAMAGE_HEALED_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group(1));
            healingValues.add(value);
        }

        logger.info("{} self-healing actions were found. Total self healing: {} hitpoints.", healingValues.size(), calculateTotal(healingValues));
        return healingValues;
    }

    private int calculateTotal(List<Integer> values) {
        return values.stream().mapToInt(Integer::intValue).sum();
    }
}

