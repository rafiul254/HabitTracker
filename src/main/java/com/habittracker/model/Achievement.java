package com.habittracker.model;

import java.time.LocalDateTime;

public class Achievement {
    private String title;
    private String description;
    private String icon;
    private LocalDateTime unlockedAt;
    private boolean unlocked;

    public Achievement(String title, String description, String icon) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.unlocked = false;
    }

    public void unlock() {
        this.unlocked = true;
        this.unlockedAt = LocalDateTime.now();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getUnlockedAt() {
        return unlockedAt;
    }

    public void setUnlockedAt(LocalDateTime unlockedAt) {
        this.unlockedAt = unlockedAt;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}

