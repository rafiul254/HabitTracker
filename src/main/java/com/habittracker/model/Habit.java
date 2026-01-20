package com.habittracker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Habit {
    private int id;
    private String name;
    private String category;
    private String description;
    private boolean completed;
    private int streak;
    private LocalDate lastCompletedDate;
    private LocalDateTime createdAt;
    private int points;
    private String color;

    public Habit() {
        this.completed = false;
        this.streak = 0;
        this.points = 0;
        this.createdAt = LocalDateTime.now();
    }

    public Habit(String name, String category) {
        this();
        this.name = name;
        this.category = category;
        this.color = getCategoryColor(category);
    }

    public Habit(int id, String name, String category, String description) {
        this(name, category);
        this.id = id;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        this.color = getCategoryColor(category);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        if (completed) {
            this.lastCompletedDate = LocalDate.now();
        }
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public LocalDate getLastCompletedDate() {
        return lastCompletedDate;
    }

    public void setLastCompletedDate(LocalDate lastCompletedDate) {
        this.lastCompletedDate = lastCompletedDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Business Logic
    public void toggleComplete() {
        if (completed) {
            this.completed = false;
            this.streak = Math.max(0, this.streak - 1);
        } else {
            this.completed = true;
            this.streak++;
            this.points += 5;
            this.lastCompletedDate = LocalDate.now();
        }
    }

    public void incrementStreak() {
        this.streak++;
        this.points += 5;
    }

    public void resetStreak() {
        this.streak = 0;
    }

    private String getCategoryColor(String category) {
        switch (category) {
            case "Study":
                return "#FF5252";
            case "Exercise":
                return "#FFC107";
            case "Meditation":
                return "#4CAF50";
            case "Outdoor":
                return "#2196F3";
            default:
                return "#9E9E9E";
        }
    }

    public String getCategoryIcon() {
        switch (category) {
            case "Study":
                return "📚";
            case "Exercise":
                return "💪";
            case "Meditation":
                return "🧘";
            case "Outdoor":
                return "🌳";
            default:
                return "✓";
        }
    }

    @Override
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", completed=" + completed +
                ", streak=" + streak +
                '}';
    }
}

