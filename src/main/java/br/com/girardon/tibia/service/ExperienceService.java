package br.com.girardon.tibia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExperienceService {

    private static final Logger logger = LoggerFactory.getLogger(ExperienceService.class);
    private static final String EXPERIENCE_GAIN_LOG_PATTERN = "\\d{2}:\\d{2} You gained (\\d+) experience points\\.";

    public List<Integer> findExperienceGainValues(String logContent) {
        List<Integer> experienceGainValues = findValues(logContent);

        logger.info("{} experience gain actions were found. Total experience gained: {} points.", experienceGainValues.size(), calculateTotal(experienceGainValues));
        return experienceGainValues;
    }

    private List<Integer> findValues(String logContent) {
        List<Integer> values = new ArrayList<>();
        Pattern pattern = Pattern.compile(ExperienceService.EXPERIENCE_GAIN_LOG_PATTERN);
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
