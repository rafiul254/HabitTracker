package com.habittracker.model;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private int totalHabits;
    private int completedToday;
    private int currentStreak;
    private int totalPoints;
    private double completionRate;
    private Map<String, Integer> categoryDistribution;
    private int[] weeklyCompletion;

    public Statistics() {
        this.categoryDistribution = new HashMap<>();
        this.weeklyCompletion = new int[7];
    }

    // Getters and Setters
    public int getTotalHabits() {
        return totalHabits;
    }

    public void setTotalHabits(int totalHabits) {
        this.totalHabits = totalHabits;
    }

    public int getCompletedToday() {
        return completedToday;
    }

    public void setCompletedToday(int completedToday) {
        this.completedToday = completedToday;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(double completionRate) {
        this.completionRate = completionRate;
    }

    public Map<String, Integer> getCategoryDistribution() {
        return categoryDistribution;
    }

    public void setCategoryDistribution(Map<String, Integer> categoryDistribution) {
        this.categoryDistribution = categoryDistribution;
    }

    public int[] getWeeklyCompletion() {
        return weeklyCompletion;
    }

    public void setWeeklyCompletion(int[] weeklyCompletion) {
        this.weeklyCompletion = weeklyCompletion;
    }
}



