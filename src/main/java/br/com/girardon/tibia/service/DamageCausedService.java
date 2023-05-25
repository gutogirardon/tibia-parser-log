package br.com.girardon.tibia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DamageCausedService {

    private static final Logger logger = LoggerFactory.getLogger(DamageCausedService.class);
    private static final String BK_DAMAGE_LOG_PATTERN = "\\d{2}:\\d{2} A Black Knight loses (\\d+) hitpoints due to your attack\\.";

    public int calculateTotalDamage(String logContent) {
        int totalDamage = 0;
        Pattern pattern = Pattern.compile(BK_DAMAGE_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            String damageStr = matcher.group(1);
            int damage = Integer.parseInt(damageStr);
            totalDamage += damage;
        }

        logger.info("Total damage caused to the Black Knight: {}", totalDamage);
        return totalDamage;
    }
}

