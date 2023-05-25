package br.com.girardon.tibia.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DropService {

    private static final Logger logger = LoggerFactory.getLogger(DropService.class);
    private static final String LOOT_LOG_PATTERN = "\\d{2}:\\d{2} Loot of [^:]+: ([^\\.]+)\\.";

    public Map<String, Integer> parseLootItems(String logContent) {
        Map<String, Integer> lootItems = findLootItems(logContent);

        logger.info("{} unique loot items were found: {}", lootItems.size(), lootItems);
        return lootItems;
    }

    private Map<String, Integer> findLootItems(String logContent) {
        Map<String, Integer> lootItems = new HashMap<>();
        Pattern pattern = Pattern.compile(LOOT_LOG_PATTERN);
        Matcher matcher = pattern.matcher(logContent);

        while (matcher.find()) {
            String loot = matcher.group(1);
            if (!loot.equals("nothing")) {
                String[] items = loot.split(", ");
                for (String item : items) {
                    int itemValue = getItemValue(item);
                    if (itemValue > 0) {
                        String itemName = getItemName(item);
                        lootItems.put(itemName, lootItems.getOrDefault(itemName, 0) + itemValue);
                    }
                }
            }
        }

        return lootItems;
    }

    private int getItemValue(String item) {
        String[] parts = item.split(" ", 2);

        if (parts.length == 1) {
            String valueStr = parts[0];
            if (startsWithAlphabeticCharacter(valueStr)) {
                return 1;
            }
        }

        if (parts.length == 2) {
            String valueStr = parts[0];
            if (startsWithAlphabeticCharacter(valueStr)) {
                return 1;
            } else {
                try {
                    return Integer.parseInt(parts[0]);
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        }

        return 0;
    }

    private String getItemName(String item) {
        String[] parts = item.split(" ", 2);

        if (parts.length == 1) {
            return parts[0] + "s";
        }

        if (parts.length == 2) {
            if (parts[0].equals("a")) {
                return parts[1] + "s";
            } else if (startsWithNumericCharacter(parts[0])) {
                return parts[1];
            } else {
                return item;
            }
        }

        return item;
    }

    private boolean startsWithAlphabeticCharacter(String str) {
        return str.matches("^[a-zA-Z].*");
    }

    private boolean startsWithNumericCharacter(String str) {
        return str.matches("^[0-9].*");
    }
}
