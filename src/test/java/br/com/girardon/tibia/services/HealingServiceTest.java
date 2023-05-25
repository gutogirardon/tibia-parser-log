package br.com.girardon.tibia.services;

import br.com.girardon.tibia.service.HealingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HealingServiceTest {

    @Test
    void testFindHealingValues() {
        // simple mocked object
        String logContent = "10:05 You healed yourself for 50 hitpoints.\n" +
                "10:10 You healed yourself for 30 hitpoints.\n" +
                "10:15 You healed yourself for 20 hitpoints.";

        HealingService healingService = new HealingService();

        Integer result = healingService.findHealingValues(logContent);
        Assertions.assertEquals(100, result);
    }
}

