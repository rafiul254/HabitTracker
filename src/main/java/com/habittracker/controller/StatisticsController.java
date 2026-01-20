package com.habittracker.controller;

import com.habittracker.model.Habit;
import com.habittracker.service.HabitAPIService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsController {

    @FXML private VBox pieChartContainer;
    @FXML private VBox lineChartContainer;
    @FXML private VBox statsCardsContainer;
    @FXML private Label totalHabitsStatLabel;
    @FXML private Label completionRateLabel;
    @FXML private Label averageStreakLabel;

    private HabitAPIService habitService;

    public StatisticsController() {
        this.habitService = new HabitAPIService();
    }

    @FXML
    public void initialize() {
        System.out.println("✓ StatisticsController initialized");
        loadStatistics();
        createPieChart();
        createLineChart();
        createStatsCards();
    }

    private void loadStatistics() {
        List<Habit> habits = habitService.getAllHabits();

        if (totalHabitsStatLabel != null) {
            totalHabitsStatLabel.setText(String.valueOf(habits.size()));
        }

        if (completionRateLabel != null) {
            long completed = habits.stream().filter(Habit::isCompleted).count();
            double rate = habits.isEmpty() ? 0 : (completed * 100.0 / habits.size());
            completionRateLabel.setText(String.format("%.1f%%", rate));
        }

        if (averageStreakLabel != null) {
            double avgStreak = habits.stream()
                    .mapToInt(Habit::getStreak)
                    .average()
                    .orElse(0.0);
            averageStreakLabel.setText(String.format("%.1f days", avgStreak));
        }
    }

    private void createPieChart() {
        if (pieChartContainer == null) return;

        pieChartContainer.getChildren().clear();
        pieChartContainer.setAlignment(Pos.CENTER);

        // Get category data
        Map<String, Integer> categoryData = getCategoryData();

        if (categoryData.isEmpty()) {
            Label noData = new Label("No habits to display");
            noData.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
            pieChartContainer.getChildren().add(noData);
            return;
        }

        // Create canvas for pie chart
        Canvas canvas = new Canvas(300, 300);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Colors for categories
        Map<String, Color> colors = new HashMap<>();
        colors.put("Study", Color.web("#FF5252"));
        colors.put("Exercise", Color.web("#FFC107"));
        colors.put("Meditation", Color.web("#4CAF50"));
        colors.put("Outdoor", Color.web("#2196F3"));

        // Calculate total
        int total = categoryData.values().stream().mapToInt(Integer::intValue).sum();

        // Draw pie slices
        double startAngle = 0;
        double centerX = 150;
        double centerY = 150;
        double radius = 120;

        for (Map.Entry<String, Integer> entry : categoryData.entrySet()) {
            String category = entry.getKey();
            int count = entry.getValue();

            double angle = (count / (double) total) * 360;

            gc.setFill(colors.getOrDefault(category, Color.GRAY));
            gc.fillArc(centerX - radius, centerY - radius,
                    radius * 2, radius * 2,
                    startAngle, angle, ArcType.ROUND);

            startAngle += angle;
        }

        // Create legend
        VBox legend = new VBox(10);
        legend.setAlignment(Pos.CENTER_LEFT);

        for (Map.Entry<String, Integer> entry : categoryData.entrySet()) {
            HBox legendItem = new HBox(10);
            legendItem.setAlignment(Pos.CENTER_LEFT);

            // Color box
            VBox colorBox = new VBox();
            colorBox.setPrefSize(20, 20);
            Color color = colors.getOrDefault(entry.getKey(), Color.GRAY);
            colorBox.setStyle("-fx-background-color: " + toHexString(color) + "; -fx-background-radius: 3;");

            // Label
            double percentage = (entry.getValue() / (double) total) * 100;
            Label label = new Label(entry.getKey() + ": " + entry.getValue() +
                    " (" + String.format("%.1f%%", percentage) + ")");
            label.setStyle("-fx-font-size: 13px;");

            legendItem.getChildren().addAll(colorBox, label);
            legend.getChildren().add(legendItem);
        }

        HBox chartWithLegend = new HBox(30);
        chartWithLegend.setAlignment(Pos.CENTER);
        chartWithLegend.getChildren().addAll(canvas, legend);

        pieChartContainer.getChildren().add(chartWithLegend);

        System.out.println("✓ Pie chart created");
    }

    private void createLineChart() {
        if (lineChartContainer == null) return;

        lineChartContainer.getChildren().clear();
        lineChartContainer.setAlignment(Pos.CENTER);

        // Create canvas
        Canvas canvas = new Canvas(600, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Sample data for last 7 days
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int[] completions = {2, 3, 4, 3, 4, 2, 3};

        // Draw axes
        gc.setStroke(Color.web("#666"));
        gc.setLineWidth(2);

        double marginLeft = 50;
        double marginBottom = 30;
        double chartWidth = 500;
        double chartHeight = 200;

        // Y-axis
        gc.strokeLine(marginLeft, 20, marginLeft, chartHeight + 20);
        // X-axis
        gc.strokeLine(marginLeft, chartHeight + 20, marginLeft + chartWidth, chartHeight + 20);

        // Draw line chart
        gc.setStroke(Color.web("#2196F3"));
        gc.setLineWidth(3);

        double xStep = chartWidth / (days.length - 1);
        int maxValue = 5;

        for (int i = 0; i < days.length - 1; i++) {
            double x1 = marginLeft + (i * xStep);
            double y1 = chartHeight + 20 - (completions[i] / (double) maxValue * chartHeight);

            double x2 = marginLeft + ((i + 1) * xStep);
            double y2 = chartHeight + 20 - (completions[i + 1] / (double) maxValue * chartHeight);

            gc.strokeLine(x1, y1, x2, y2);

            // Draw points
            gc.setFill(Color.web("#2196F3"));
            gc.fillOval(x1 - 5, y1 - 5, 10, 10);
        }

        // Last point
        double lastX = marginLeft + ((days.length - 1) * xStep);
        double lastY = chartHeight + 20 - (completions[days.length - 1] / (double) maxValue * chartHeight);
        gc.fillOval(lastX - 5, lastY - 5, 10, 10);

        // Draw labels
        gc.setFill(Color.web("#666"));
        gc.setFont(javafx.scene.text.Font.font(12));

        for (int i = 0; i < days.length; i++) {
            double x = marginLeft + (i * xStep);
            gc.fillText(days[i], x - 15, chartHeight + 40);
        }

        // Y-axis labels
        for (int i = 0; i <= maxValue; i++) {
            double y = chartHeight + 20 - (i / (double) maxValue * chartHeight);
            gc.fillText(String.valueOf(i), 25, y + 5);
        }

        lineChartContainer.getChildren().add(canvas);

        System.out.println("✓ Line chart created");
    }

    private void createStatsCards() {
        if (statsCardsContainer == null) return;

        List<Habit> habits = habitService.getAllHabits();

        // Best streak
        int bestStreak = habits.stream()
                .mapToInt(Habit::getStreak)
                .max()
                .orElse(0);

        // Total points
        int totalPoints = habits.stream()
                .mapToInt(Habit::getPoints)
                .sum();

        // Most productive category
        Map<String, Integer> categoryData = getCategoryData();
        String topCategory = categoryData.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");

        VBox card1 = createStatCard("🔥 Best Streak", bestStreak + " days", "#FF9800");
        VBox card2 = createStatCard("🏆 Total Points", String.valueOf(totalPoints), "#FFC107");
        VBox card3 = createStatCard("⭐ Top Category", topCategory, "#4CAF50");

        HBox cards = new HBox(15);
        cards.setAlignment(Pos.CENTER);
        cards.getChildren().addAll(card1, card2, card3);

        statsCardsContainer.getChildren().clear();
        statsCardsContainer.getChildren().add(cards);
    }

    private VBox createStatCard(String title, String value, String color) {
        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-padding: 20; -fx-pref-width: 200;");

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");

        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: " + color + ";");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    private Map<String, Integer> getCategoryData() {
        Map<String, Integer> data = new HashMap<>();
        List<Habit> habits = habitService.getAllHabits();

        for (Habit habit : habits) {
            data.merge(habit.getCategory(), 1, Integer::sum);
        }

        return data;
    }

    private String toHexString(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
