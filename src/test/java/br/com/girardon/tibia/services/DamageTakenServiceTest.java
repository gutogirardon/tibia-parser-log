package br.com.girardon.tibia.services;

import br.com.girardon.tibia.service.DamageTakenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class DamageTakenServiceTest {

    @Test
    void testFindDamageTakenValues() {
        String logContent = "10:05 You lose 50 hitpoints.\n" +
                "10:10 You lose 30 hitpoints.\n" +
                "10:15 You lose 20 hitpoints.";

        DamageTakenService damageTakenService = new DamageTakenService();

        List<Integer> damageValues = damageTakenService.findDamageTakenValues(logContent);

        Assertions.assertEquals(3, damageValues.size());
        Assertions.assertEquals(50, damageValues.get(0));
        Assertions.assertEquals(30, damageValues.get(1));
        Assertions.assertEquals(20, damageValues.get(2));
    }

    @Test
    void testFindDamageTakenByMonster() {
        String logContent = "10:05 You lose 50 hitpoints due to an attack by a Dragon.\n" +
                "10:10 You lose 30 hitpoints due to an attack by a Troll.\n" +
                "10:15 You lose 20 hitpoints due to an attack by a Dragon.";

        DamageTakenService damageTakenService = new DamageTakenService();

        Map<String, Integer> damageTakenByMonster = damageTakenService.findDamageTakenByMonster(logContent);

        Assertions.assertEquals(2, damageTakenByMonster.size());
        Assertions.assertEquals(70, damageTakenByMonster.get("Dragon"));
        Assertions.assertEquals(30, damageTakenByMonster.get("Troll"));
    }

    @Test
    void testCalculateDamageDifference() {
        // realizamos a soma de todos os danos e diminuimos os danos conhecidos de monstros,
        // o que sobrar, é dano de causa desconhecida
        String logContent = "10:05 You lose 50 hitpoints due to an attack by a Dragon.\n" +
                "10:10 You lose 30 hitpoints.\n" +
                "10:15 You lose 20 hitpoints due to an attack by a Dragon.\n" +
                "10:20 You lose 10 hitpoints.";

        DamageTakenService damageTakenService = new DamageTakenService();

        int damageDifference = damageTakenService.calculateDamageDifference(logContent);

        Assertions.assertEquals(40, damageDifference);
    }

    @Test
    void testGetAllDamageTakenValue() {
        String logContent = "10:05 You lose 50 hitpoints.\n" +
                "10:10 You lose 30 hitpoints.\n" +
                "10:15 You lose 20 hitpoints.";

        DamageTakenService damageTakenService = new DamageTakenService();

        int allDamageTakenValue = damageTakenService.getAllDamageTakenValue(logContent);

        Assertions.assertEquals(100, allDamageTakenValue);
    }
}
