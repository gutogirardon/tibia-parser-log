package br.com.girardon.tibia.dto;

import java.util.Map;

public class TibiaLogDTO {
    private Integer hitpointsHealed;
    private Integer experienceGained;
    private Damage damage;
    private Map<String, Integer> loot;

    public TibiaLogDTO(Integer hitpointsHealed, Integer experienceGained,
                       Integer damageTakenTotalValue, Map<String, Integer> damageTakenByMonster,
                       int damageTakenByUnknownSource, int damageCausedToBlackKnight,
                       Map<String, Integer> loot) {
        this.hitpointsHealed = hitpointsHealed;
        this.experienceGained = experienceGained;
        this.damage = new Damage(damageTakenTotalValue, damageTakenByMonster, damageTakenByUnknownSource, damageCausedToBlackKnight);
        this.loot = loot;
    }

    public Integer getHitpointsHealed() {
        return hitpointsHealed;
    }

    public void setHitpointsHealed(Integer hitpointsHealed) {
        this.hitpointsHealed = hitpointsHealed;
    }

    public Integer getExperienceGained() {
        return experienceGained;
    }

    public void setExperienceGained(Integer experienceGained) {
        this.experienceGained = experienceGained;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public Map<String, Integer> getLoot() {
        return loot;
    }

    public void setLoot(Map<String, Integer> loot) {
        this.loot = loot;
    }

    public static class Damage {
        private Integer damageTakenTotalValue;
        private Map<String, Integer> damageTakenByMonster;
        private int damageTakenByUnknownSource;
        private int damageCausedToBlackKnight;

        public Damage(Integer damageTakenTotalValue, Map<String, Integer> damageTakenByMonster,
                      int damageTakenByUnknownSource, int damageCausedToBlackKnight) {
            this.damageTakenTotalValue = damageTakenTotalValue;
            this.damageTakenByMonster = damageTakenByMonster;
            this.damageTakenByUnknownSource = damageTakenByUnknownSource;
            this.damageCausedToBlackKnight = damageCausedToBlackKnight;
        }

        public Integer getDamageTakenTotalValue() {
            return damageTakenTotalValue;
        }

        public void setDamageTakenTotalValue(Integer damageTakenTotalValue) {
            this.damageTakenTotalValue = damageTakenTotalValue;
        }

        public Map<String, Integer> getDamageTakenByMonster() {
            return damageTakenByMonster;
        }

        public void setDamageTakenByMonster(Map<String, Integer> damageTakenByMonster) {
            this.damageTakenByMonster = damageTakenByMonster;
        }

        public int getDamageTakenByUnknownSource() {
            return damageTakenByUnknownSource;
        }

        public void setDamageTakenByUnknownSource(int damageTakenByUnknownSource) {
            this.damageTakenByUnknownSource = damageTakenByUnknownSource;
        }

        public int getDamageCausedToBlackKnight() {
            return damageCausedToBlackKnight;
        }

        public void setDamageCausedToBlackKnight(int damageCausedToBlackKnight) {
            this.damageCausedToBlackKnight = damageCausedToBlackKnight;
        }
    }
}
