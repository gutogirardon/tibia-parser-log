package br.com.girardon.tibia.dto;

import java.util.ArrayList;
import java.util.List;

public class TibiaLogDTO {
    private String logDate;
    private List<String> actions;
    private List<String> loots;

    public TibiaLogDTO() {
        actions = new ArrayList<>();
        loots = new ArrayList<>();
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public List<String> getLoots() {
        return loots;
    }

    public void setLoots(List<String> loots) {
        this.loots = loots;
    }
}
