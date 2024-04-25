package com.example.finalproject;

public class SchemesModel {

    private String name;
    private String launchYear;
    private String vision;
    private String icon;

    public SchemesModel(String name, String launchYear, String vision, String icon) {
        this.name = name;
        this.launchYear = launchYear;
        this.vision = vision;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaunchYear() {
        return launchYear;
    }

    public void setLaunchYear(String launchYear) {
        this.launchYear = launchYear;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
