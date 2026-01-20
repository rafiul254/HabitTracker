package com.habittracker.controller;

import com.habittracker.model.Habit;
import com.habittracker.model.WeatherData;
import com.habittracker.service.HabitAPIService;
import com.habittracker.service.WeatherService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Random;

public class DashboardController {

    @FXML private Label totalHabitsLabel;
    @FXML private Label completedTodayLabel;
    @FXML private Label currentStreakLabel;
    @FXML private Label totalPointsLabel;
    @FXML private Label weatherLabel;
    @FXML private ProgressBar todayProgressBar;
    @FXML private ListView<Habit> habitListView;
    @FXML private Button addHabitButton;
    @FXML private Button themeToggleButton;
    @FXML private HBox statsContainer;
    @FXML private HBox chartContainer;

    private HabitAPIService habitService;
    private WeatherService weatherService;
    private boolean isDarkMode = false;

    public DashboardController() {
        this.habitService = new HabitAPIService();
        this.weatherService = new WeatherService();
    }

    @FXML
    public void initialize() {
        try {
            System.out.println("========================================");
            System.out.println("DashboardController initializing...");

            // Setup ListView
            if (habitListView != null) {
                habitListView.setCellFactory(param -> new HabitListCell());
            }

            // Setup Buttons
            if (addHabitButton != null) {
                addHabitButton.setOnAction(e -> handleAddHabit());
            }

            if (themeToggleButton != null) {
                themeToggleButton.setOnAction(e -> handleThemeToggle());
            }

            // Load Data
            loadHabits();
            loadWeather();
            updateStatistics();

            // Create Chart
            createWeeklyChart();

            System.out.println("Dashboard initialized successfully!");
            System.out.println("========================================");
        } catch (Exception e) {
            System.err.println("Error during initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadHabits() {
        try {
            List<Habit> habits = habitService.getAllHabits();
            if (habitListView != null) {
                habitListView.getItems().clear();
                habitListView.getItems().addAll(habits);
            }
            System.out.println("Loaded " + habits.size() + " habits");
        } catch (Exception e) {
            System.err.println("Error loading habits: " + e.getMessage());
        }
    }

    private void loadWeather() {
        try {
            WeatherData weather = weatherService.getWeather();
            if (weatherLabel != null) {
                weatherLabel.setText(weather.getIcon() + " " + weather.getTemperature() + "°C - " + weather.getCondition());
            }
            System.out.println("Weather loaded successfully");
        } catch (Exception e) {
            System.err.println("Weather load failed: " + e.getMessage());
            if (weatherLabel != null) {
                weatherLabel.setText("🌤️ Weather unavailable");
            }
        }
    }

    private void updateStatistics() {
        try {
            if (habitListView == null || habitListView.getItems() == null) {
                return;
            }

            List<Habit> habits = habitListView.getItems();
            int total = habits.size();
            int completed = (int) habits.stream().filter(Habit::isCompleted).count();
            int maxStreak = habits.stream().mapToInt(Habit::getStreak).max().orElse(0);
            int totalPoints = habits.stream().mapToInt(Habit::getPoints).sum();

            if (totalHabitsLabel != null) {
                totalHabitsLabel.setText(String.valueOf(total));
            }

            if (completedTodayLabel != null) {
                completedTodayLabel.setText(completed + "/" + total);
            }

            if (currentStreakLabel != null) {
                currentStreakLabel.setText(maxStreak + " days");
            }

            if (totalPointsLabel != null) {
                totalPointsLabel.setText(String.valueOf(totalPoints));
            }

            if (todayProgressBar != null) {
                double progress = total > 0 ? (double) completed / total : 0.0;
                todayProgressBar.setProgress(progress);
            }

            // Update chart when stats change
            createWeeklyChart();

            System.out.println("Statistics updated: " + completed + "/" + total + " completed");
        } catch (Exception e) {
            System.err.println("Error updating statistics: " + e.getMessage());
        }
    }

    private void createWeeklyChart() {
        if (chartContainer == null) {
            return;
        }

        try {
            chartContainer.getChildren().clear();
            chartContainer.setAlignment(Pos.CENTER);
            chartContainer.setSpacing(10);
            chartContainer.setPadding(new Insets(20));

            // Sample weekly data (you can make this dynamic)
            String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
            int[] completions = {1, 2, 4, 3, 2, 1, 0}; // Sample data

            // Find max for scaling
            int max = 5;

            for (int i = 0; i < days.length; i++) {
                VBox dayColumn = new VBox(5);
                dayColumn.setAlignment(Pos.BOTTOM_CENTER);
                dayColumn.setPrefWidth(80);

                // Bar
                double height = (completions[i] / (double) max) * 100;
                Rectangle bar = new Rectangle(60, Math.max(height, 5));

                // Color based on completion
                if (completions[i] >= 4) {
                    bar.setFill(Color.web("#4CAF50")); // Green
                } else if (completions[i] >= 2) {
                    bar.setFill(Color.web("#FFC107")); // Yellow
                } else {
                    bar.setFill(Color.web("#FF9800")); // Orange
                }

                bar.setArcWidth(8);
                bar.setArcHeight(8);

                // Count label
                Label countLabel = new Label(String.valueOf(completions[i]));
                countLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");

                // Day label
                Label dayLabel = new Label(days[i]);
                dayLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #666;");

                dayColumn.getChildren().addAll(countLabel, bar, dayLabel);
                chartContainer.getChildren().add(dayColumn);
            }

            System.out.println("✓ Weekly chart created");
        } catch (Exception e) {
            System.err.println("✗ Error creating chart: " + e.getMessage());
            // Fallback to simple label
            Label errorLabel = new Label("Chart visualization");
            errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
            chartContainer.getChildren().add(errorLabel);
        }
    }

    @FXML
    private void handleAddHabit() {
        try {
            // Create custom dialog
            Dialog<Habit> dialog = new Dialog<>();
            dialog.setTitle("Add New Habit");
            dialog.setHeaderText("Create a new habit to track");

            // Set buttons
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create form
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20));

            TextField nameField = new TextField();
            nameField.setPromptText("Habit name");
            nameField.setPrefWidth(300);

            ComboBox<String> categoryBox = new ComboBox<>();
            categoryBox.getItems().addAll("Study", "Exercise", "Meditation", "Outdoor");
            categoryBox.setValue("Study");

            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Category:"), 0, 1);
            grid.add(categoryBox, 1, 1);

            dialog.getDialogPane().setContent(grid);

            // Focus on name field
            nameField.requestFocus();

            // Convert result
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    String name = nameField.getText().trim();
                    if (!name.isEmpty()) {
                        return new Habit(name, categoryBox.getValue());
                    }
                }
                return null;
            });

            // Show and process
            dialog.showAndWait().ifPresent(habit -> {
                habitService.addHabit(habit);
                loadHabits();
                updateStatistics();

                // Show success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Habit '" + habit.getName() + "' added successfully! 🎉");
                alert.showAndWait();

                System.out.println("✓ Habit added: " + habit.getName());
            });

        } catch (Exception e) {
            System.err.println("✗ Error adding habit: " + e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not add habit");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleThemeToggle() {
        try {
            isDarkMode = !isDarkMode;
            if (themeToggleButton != null && themeToggleButton.getScene() != null) {
                String bgColor = isDarkMode ? "#1e1e1e" : "#f5f5f5";
                String textColor = isDarkMode ? "white" : "black";

                themeToggleButton.getScene().getRoot().setStyle(
                        "-fx-background-color: " + bgColor + ";"
                );

                themeToggleButton.setText(isDarkMode ? "☀️ Light Mode" : "🌙 Dark Mode");

                System.out.println("✓ Theme toggled to: " + (isDarkMode ? "Dark" : "Light"));
            }
        } catch (Exception e) {
            System.err.println("✗ Error toggling theme: " + e.getMessage());
        }
    }

    // Custom ListCell for Habit display
    private class HabitListCell extends ListCell<Habit> {
        @Override
        protected void updateItem(Habit habit, boolean empty) {
            super.updateItem(habit, empty);

            if (empty || habit == null) {
                setText(null);
                setGraphic(null);
                setStyle("");
            } else {
                HBox container = new HBox(15);
                container.setAlignment(Pos.CENTER_LEFT);
                container.setPadding(new Insets(10));

                String bgColor = habit.isCompleted() ? "#e8f5e9" : "white";
                container.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 8;");

                CheckBox checkBox = new CheckBox();
                checkBox.setSelected(habit.isCompleted());
                checkBox.setStyle("-fx-cursor: hand;");
                checkBox.setOnAction(e -> {
                    habit.toggleComplete();
                    updateStatistics();
                    habitListView.refresh();

                    // Show celebration for completion
                    if (habit.isCompleted()) {
                        System.out.println("🎉 Habit completed: " + habit.getName());
                    }
                });

                VBox details = new VBox(5);
                Label nameLabel = new Label(habit.getCategoryIcon() + " " + habit.getName());
                nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

                Label infoLabel = new Label(habit.getCategory() + " | 🔥 Streak: " + habit.getStreak() + " days | 🏆 Points: " + habit.getPoints());
                infoLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

                details.getChildren().addAll(nameLabel, infoLabel);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button deleteBtn = new Button("🗑️ Delete");
                deleteBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-cursor: hand; -fx-background-radius: 5;");
                deleteBtn.setOnAction(e -> {
                    Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmAlert.setTitle("Confirm Delete");
                    confirmAlert.setHeaderText("Delete Habit");
                    confirmAlert.setContentText("Are you sure you want to delete '" + habit.getName() + "'?");

                    confirmAlert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            habitService.removeHabit(habit);
                            loadHabits();
                            updateStatistics();
                            System.out.println("✓ Habit deleted: " + habit.getName());
                        }
                    });
                });

                container.getChildren().addAll(checkBox, details, spacer, deleteBtn);
                setGraphic(container);
                setText(null);
            }
        }
    }
}
