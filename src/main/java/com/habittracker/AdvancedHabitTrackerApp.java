package com.habittracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import com.habittracker.util.ThemeManager;
import com.habittracker.util.NotificationManager;

import java.io.IOException;

public class AdvancedHabitTrackerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("========================================");
            System.out.println("Starting Advanced Habit Tracker Pro...");
            System.out.println("========================================");

            // Create TabPane for navigation
            TabPane tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

            // Load all views
            Tab dashboardTab = createTab("🏠 Dashboard", "/com/habittracker/view/dashboard.fxml");
            Tab calendarTab = createTab("📅 Calendar", "/com/habittracker/view/calendar.fxml");
            Tab statisticsTab = createTab("📊 Statistics", "/com/habittracker/view/statistics.fxml");
            Tab settingsTab = createTab("⚙️ Settings", "/com/habittracker/view/settings.fxml");

            tabPane.getTabs().addAll(dashboardTab, calendarTab, statisticsTab, settingsTab);

            // Create scene
            Scene scene = new Scene(tabPane, 1200, 800);
            ThemeManager.setCurrentScene(scene);
            ThemeManager.applyTheme(scene, ThemeManager.Theme.LIGHT);

            // Setup stage
            primaryStage.setTitle("Advanced Habit Tracker Pro");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(900);
            primaryStage.setMinHeight(600);
            primaryStage.show();

            // Show welcome notification
            NotificationManager.getInstance().showSuccess(
                    "Welcome to Advanced Habit Tracker! 🎉"
            );

            System.out.println("========================================");
            System.out.println("✓ Application started successfully!");
            System.out.println("✓ All features loaded");
            System.out.println("========================================");

        } catch (Exception e) {
            System.err.println("========================================");
            System.err.println("✗ FATAL ERROR:");
            System.err.println(e.getMessage());
            System.err.println("========================================");
            e.printStackTrace();
        }
    }

    private Tab createTab(String name, String fxmlPath) {
        Tab tab = new Tab(name);
        tab.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            Parent content = loader.load();
            tab.setContent(content);
            System.out.println("✓ Loaded: " + name);
        } catch (IOException e) {
            System.err.println("✗ Failed to load: " + name);
            System.err.println("  Path: " + fxmlPath);
            System.err.println("  Error: " + e.getMessage());
            e.printStackTrace();
        }

        return tab;
    }

    @Override
    public void stop() {
        System.out.println("Application closing...");
        NotificationManager.getInstance().shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
