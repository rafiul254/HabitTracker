package com.habittracker.service;

import com.habittracker.model.Habit;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataExportService {

    public void exportToCSV(List<Habit> habits, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {

            writer.write("ID,Name,Category,Completed,Streak,Points\n");

            for (Habit habit : habits) {
                writer.write(String.format("%d,%s,%s,%b,%d,%d\n",
                        habit.getId(),
                        habit.getName(),
                        habit.getCategory(),
                        habit.isCompleted(),
                        habit.getStreak(),
                        habit.getPoints()
                ));
            }

            System.out.println("✓ Exported " + habits.size() + " habits to " + filePath);
        } catch (IOException e) {
            System.err.println("Export error: " + e.getMessage());
        }
    }

    public String exportToJSON(List<Habit> habits) {
        StringBuilder json = new StringBuilder();
        json.append("[\n");

        for (int i = 0; i < habits.size(); i++) {
            Habit habit = habits.get(i);
            json.append("  {\n");
            json.append("    \"id\": ").append(habit.getId()).append(",\n");
            json.append("    \"name\": \"").append(habit.getName()).append("\",\n");
            json.append("    \"category\": \"").append(habit.getCategory()).append("\",\n");
            json.append("    \"completed\": ").append(habit.isCompleted()).append(",\n");
            json.append("    \"streak\": ").append(habit.getStreak()).append(",\n");
            json.append("    \"points\": ").append(habit.getPoints()).append("\n");
            json.append("  }");
            if (i < habits.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }

        json.append("]");
        return json.toString();
    }

    public void saveToFile(String data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
            System.out.println("✓ Data saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Save error: " + e.getMessage());
        }
    }
}
