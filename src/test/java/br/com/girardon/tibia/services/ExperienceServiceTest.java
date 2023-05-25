package br.com.girardon.tibia.services;

import br.com.girardon.tibia.service.ExperienceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExperienceServiceTest {

    @Test
    void testFindExperienceGainValues() {
        // simple mocked object
        String logContent = "10:05 You gained 100 experience points.\n" +
                "10:10 You gained 50 experience points.\n" +
                "10:15 You gained 75 experience points.";

        ExperienceService experienceService = new ExperienceService();

        Integer result = experienceService.findExperienceGainValues(logContent);
        Assertions.assertEquals(225, result);
    }

    @Test
    void testFindExperienceGainValues_NoExperienceGained() {
        // simple mocked object
        String logContent = "10:05 You healed yourself for 50 hitpoints.\n" +
                "10:10 You healed yourself for 30 hitpoints.\n" +
                "10:15 You healed yourself for 20 hitpoints.";

        ExperienceService experienceService = new ExperienceService();

        Integer result = experienceService.findExperienceGainValues(logContent);
        Assertions.assertEquals(0, result);
    }
}
