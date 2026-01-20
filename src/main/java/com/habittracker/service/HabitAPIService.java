package com.habittracker.service;
import com.habittracker.model.Habit;
import java.util.ArrayList;
import java.util.List;
public class HabitAPIService {
    private List<Habit> habits;
    private int nextId;

    public HabitAPIService() {
        this.habits = new ArrayList<>();
        this.nextId = 1;
        initializeSampleData();
    }

    private void initializeSampleData() {
        addHabit(new Habit("Morning Meditation", "Meditation"));
        addHabit(new Habit("Study Java", "Study"));
        addHabit(new Habit("Exercise 30 min", "Exercise"));
        addHabit(new Habit("Outdoor Walk", "Outdoor"));
    }

    public void addHabit(Habit habit) {
        habit.setId(nextId++);
        habits.add(habit);
        System.out.println("✓ Added habit: " + habit.getName());
    }

    public void removeHabit(Habit habit) {
        habits.remove(habit);
        System.out.println("✓ Removed habit: " + habit.getName());
    }

    public List<Habit> getAllHabits() {
        return new ArrayList<>(habits);
    }

    public Habit getHabitById(int id) {
        return habits.stream()
                .filter(h -> h.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateHabit(Habit habit) {
        for (int i = 0; i < habits.size(); i++) {
            if (habits.get(i).getId() == habit.getId()) {
                habits.set(i, habit);
                System.out.println("✓ Updated habit: " + habit.getName());
                return;
            }
        }
    }

    public List<Habit> getHabitsByCategory(String category) {
        List<Habit> result = new ArrayList<>();
        for (Habit habit : habits) {
            if (habit.getCategory().equals(category)) {
                result.add(habit);
            }
        }
        return result;
    }

    public void clearAllHabits() {
        habits.clear();
        nextId = 1;
        System.out.println("✓ Cleared all habits");
    }
}
