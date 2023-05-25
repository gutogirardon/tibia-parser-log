package br.com.girardon.tibia.services;

import br.com.girardon.tibia.service.DropService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class DropServiceTest {

    @Test
    void testParseLootItems() {
        String logContent = "10:05 Loot of Dragon: 100 gold coins, 2 dragon scales.\n" +
                "10:10 Loot of Troll: 50 gold coins, 1 spear.\n" +
                "10:15 Loot of Orc: nothing.";

        DropService dropService = new DropService();

        Map<String, Integer> lootItems = dropService.parseLootItems(logContent);

        Assertions.assertEquals(3, lootItems.size()); //remete as linhas e nao aos itens em si
        Assertions.assertEquals(150, lootItems.get("gold coins"));
        Assertions.assertEquals(2, lootItems.get("dragon scales"));
        Assertions.assertEquals(1, lootItems.get("spear"));
    }

    @Test
    void testParseLootItems_NoLootItems() {
        String logContent = "10:05 You gained 100 experience points.\n" +
                "10:10 You healed yourself for 50 hitpoints.";

        DropService dropService = new DropService();

        Map<String, Integer> lootItems = dropService.parseLootItems(logContent);

        Assertions.assertTrue(lootItems.isEmpty());
    }
}

