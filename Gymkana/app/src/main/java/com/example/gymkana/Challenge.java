package com.example.gymkana;

public class Challenge {

    private String description;
    private String title;
    private String code;
    private int icon;

    public Challenge(String description, String title, int resId, String code) {
        this.description = description;
        this.title = title;
        this.icon = resId;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public int getIcon() {
        return icon;
    }


}
