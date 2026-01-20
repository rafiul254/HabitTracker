package com.habittracker.model;

public class UserSettings {
    private boolean darkMode;
    private boolean notificationsEnabled;
    private String language;
    private int reminderTime;

    public UserSettings() {
        this.darkMode = false;
        this.notificationsEnabled = true;
        this.language = "English";
        this.reminderTime = 9; // 9 AM
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }
}


