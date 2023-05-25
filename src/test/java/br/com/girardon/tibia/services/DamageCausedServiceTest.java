package br.com.girardon.tibia.services;

import br.com.girardon.tibia.service.DamageCausedService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DamageCausedServiceTest {

    @Test
    void testCalculateTotalDamage() {
        String logContent = "10:05 A Black Knight loses 50 hitpoints due to your attack.\n" +
                "10:10 A Black Knight loses 30 hitpoints due to your attack.\n" +
                "10:15 A Black Knight loses 20 hitpoints due to your attack.";

        DamageCausedService damageCausedService = new DamageCausedService();

        int totalDamage = damageCausedService.calculateTotalDamage(logContent);
        Assertions.assertEquals(100, totalDamage);
    }
}

