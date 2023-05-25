package br.com.girardon.tibia.dto;

import java.util.List;
import java.util.Map;

public class TibiaLogDTO {
    private List<Integer> healingValues;
    private List<Integer> experienceGainValues;
    private List<Integer> damageTakenValues;
    private Map<String, Integer> damageTakenByMonster;
    private Map<String, Integer> findLootItems;
    private int damageTakenByUnknownSource;
    private int totalTakenDamage;

    public TibiaLogDTO(List<Integer> healingValues, List<Integer> experienceGainValues,
                       List<Integer> damageTakenValues, Map<String, Integer> damageTakenByMonster,
                       Map<String, Integer> findLootItems, int damageTakenByUnknownSource, int totalTakenDamage) {
        this.healingValues = healingValues;
        this.experienceGainValues = experienceGainValues;
        this.damageTakenValues = damageTakenValues;
        this.damageTakenByMonster = damageTakenByMonster;
        this.findLootItems = findLootItems;
        this.damageTakenByUnknownSource = damageTakenByUnknownSource;
        this.totalTakenDamage = totalTakenDamage;
    }

    public List<Integer> getHealingValues() {
        return healingValues;
    }

    public void setHealingValues(List<Integer> healingValues) {
        this.healingValues = healingValues;
    }

    public List<Integer> getExperienceGainValues() {
        return experienceGainValues;
    }

    public void setExperienceGainValues(List<Integer> experienceGainValues) {
        this.experienceGainValues = experienceGainValues;
    }

    public List<Integer> getDamageTakenValues() {
        return damageTakenValues;
    }

    public void setDamageTakenValues(List<Integer> damageTakenValues) {
        this.damageTakenValues = damageTakenValues;
    }

    public Map<String, Integer> getDamageTakenByMonster() {
        return damageTakenByMonster;
    }

    public void setDamageTakenByMonster(Map<String, Integer> damageTakenByMonster) {
        this.damageTakenByMonster = damageTakenByMonster;
    }

    public Map<String, Integer> getFindLootItems() {
        return findLootItems;
    }

    public void setFindLootItems(Map<String, Integer> findLootItems) {
        this.findLootItems = findLootItems;
    }

    public int getDamageTakenByUnknownSource() {
        return damageTakenByUnknownSource;
    }

    public void setDamageTakenByUnknownSource(int damageTakenByUnknownSource) {
        this.damageTakenByUnknownSource = damageTakenByUnknownSource;
    }

    public int getTotalTakenDamage() {
        return totalTakenDamage;
    }

    public void setTotalTakenDamage(int totalTakenDamage) {
        this.totalTakenDamage = totalTakenDamage;
    }
}