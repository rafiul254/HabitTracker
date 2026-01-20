package com.habittracker.model;
import java.util.ArrayList;
import java.util.List;

public class HabitTracker {
    private List<Habit> habits;
    private int totalPoints;
    private int longestStreak;

    public HabitTracker() {
        this.habits = new ArrayList<>();
        this.totalPoints = 0;
        this.longestStreak = 0;
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void removeHabit(Habit habit) {
        habits.remove(habit);
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public int getTotalPoints() {
        return habits.stream().mapToInt(Habit::getPoints).sum();
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getLongestStreak() {
        return habits.stream().mapToInt(Habit::getStreak).max().orElse(0);
    }

    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }

    public int getCompletedToday() {
        return (int) habits.stream().filter(Habit::isCompleted).count();
    }

    public double getCompletionRate() {
        if (habits.isEmpty()) return 0.0;
        return (double) getCompletedToday() / habits.size() * 100;
    }
}
