package com.habittracker.controller;

import com.habittracker.util.NotificationManager;
import com.habittracker.util.ThemeManager;
import com.habittracker.util.ThemeManager.Theme;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalTime;

public class SettingsController {

    @FXML private CheckBox notificationsCheckBox;
    @FXML private CheckBox streakNotificationsCheckBox;
    @FXML private CheckBox completionNotificationsCheckBox;
    @FXML private Spinner<Integer> reminderHourSpinner;
    @FXML private Spinner<Integer> reminderMinuteSpinner;
    @FXML private VBox themeContainer;
    @FXML private VBox settingsContainer;
    @FXML private Label currentThemeLabel;

    private NotificationManager notificationManager;

    public SettingsController() {
        this.notificationManager = NotificationManager.getInstance();
    }

    @FXML
    public void initialize() {
        System.out.println("✓ SettingsController initialized");

        setupNotificationSettings();
        setupThemeSelector();
        setupReminderTime();
        createSettingsUI();
    }

    private void setupNotificationSettings() {
        if (notificationsCheckBox != null) {
            notificationsCheckBox.setSelected(notificationManager.isNotificationsEnabled());
            notificationsCheckBox.setOnAction(e -> {
                boolean enabled = notificationsCheckBox.isSelected();
                notificationManager.setNotificationsEnabled(enabled);

                if (enabled) {
                    notificationManager.showSuccess("Notifications enabled!");
                }

                System.out.println("Notifications: " + (enabled ? "ON" : "OFF"));
            });
        }

        if (streakNotificationsCheckBox != null) {
            streakNotificationsCheckBox.setSelected(true);
        }

        if (completionNotificationsCheckBox != null) {
            completionNotificationsCheckBox.setSelected(true);
        }
    }

    private void setupReminderTime() {
        LocalTime currentTime = notificationManager.getReminderTime();

        if (reminderHourSpinner != null) {
            SpinnerValueFactory<Integer> hourFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, currentTime.getHour());
            reminderHourSpinner.setValueFactory(hourFactory);

            reminderHourSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
                updateReminderTime();
            });
        }

        if (reminderMinuteSpinner != null) {
            SpinnerValueFactory<Integer> minuteFactory =
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, currentTime.getMinute());
            reminderMinuteSpinner.setValueFactory(minuteFactory);

            reminderMinuteSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
                updateReminderTime();
            });
        }
    }

    private void updateReminderTime() {
        if (reminderHourSpinner != null && reminderMinuteSpinner != null) {
            int hour = reminderHourSpinner.getValue();
            int minute = reminderMinuteSpinner.getValue();
            LocalTime newTime = LocalTime.of(hour, minute);
            notificationManager.setReminderTime(newTime);
            System.out.println("Reminder time set to: " + newTime);
        }
    }

    private void setupThemeSelector() {
        if (themeContainer != null) {
            themeContainer.getChildren().clear();
            themeContainer.setSpacing(10);

            Label title = new Label("🎨 Choose Theme");
            title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            themeContainer.getChildren().add(title);

            Theme currentTheme = ThemeManager.getCurrentTheme();

            if (currentThemeLabel != null) {
                currentThemeLabel.setText("Current: " + currentTheme.getName());
            }

            for (Theme theme : ThemeManager.getAllThemes()) {
                Button themeButton = createThemeButton(theme);
                themeContainer.getChildren().add(themeButton);
            }
        }
    }

    private Button createThemeButton(Theme theme) {
        Button button = new Button(theme.getName());
        button.setPrefWidth(250);
        button.setPrefHeight(50);

        String buttonStyle = String.format(
                "-fx-background-color: %s; " +
                        "-fx-text-fill: %s; " +
                        "-fx-font-size: 14px; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 8; " +
                        "-fx-cursor: hand;",
                theme.getBackgroundColor(),
                theme.getTextColor()
        );

        button.setStyle(buttonStyle);

        button.setOnAction(e -> {
            ThemeManager.setTheme(theme);
            if (currentThemeLabel != null) {
                currentThemeLabel.setText("Current: " + theme.getName());
            }
            notificationManager.showSuccess("Theme changed to " + theme.getName());
        });

        button.setOnMouseEntered(e -> {
            button.setStyle(buttonStyle + "-fx-opacity: 0.8;");
        });

        button.setOnMouseExited(e -> {
            button.setStyle(buttonStyle);
        });

        return button;
    }

    private void createSettingsUI() {
        if (settingsContainer == null) return;

        VBox additionalSettings = new VBox(15);
        additionalSettings.setPadding(new Insets(20));


        HBox autoSaveBox = new HBox(10);
        autoSaveBox.setAlignment(Pos.CENTER_LEFT);
        CheckBox autoSaveCheck = new CheckBox("Auto-save habits");
        autoSaveCheck.setSelected(true);
        autoSaveCheck.setStyle("-fx-font-size: 14px;");
        autoSaveBox.getChildren().add(autoSaveCheck);


        HBox soundBox = new HBox(10);
        soundBox.setAlignment(Pos.CENTER_LEFT);
        CheckBox soundCheck = new CheckBox("Sound effects");
        soundCheck.setSelected(false);
        soundCheck.setStyle("-fx-font-size: 14px;");
        soundBox.getChildren().add(soundCheck);


        HBox autoDarkBox = new HBox(10);
        autoDarkBox.setAlignment(Pos.CENTER_LEFT);
        CheckBox autoDarkCheck = new CheckBox("Auto dark mode at night");
        autoDarkCheck.setSelected(false);
        autoDarkCheck.setStyle("-fx-font-size: 14px;");
        autoDarkBox.getChildren().add(autoDarkCheck);


        Button testNotificationBtn = new Button("🔔 Test Notification");
        testNotificationBtn.setStyle(
                "-fx-background-color: #2196F3; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        );
        testNotificationBtn.setOnAction(e -> {
            notificationManager.showInfo("This is a test notification! 🎉");
        });


        Button resetBtn = new Button("🔄 Reset to Defaults");
        resetBtn.setStyle(
                "-fx-background-color: #f44336; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 14px; " +
                        "-fx-padding: 10 20; " +
                        "-fx-background-radius: 5; " +
                        "-fx-cursor: hand;"
        );
        resetBtn.setOnAction(e -> {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Reset Settings");
            confirmAlert.setHeaderText("Reset all settings to default?");
            confirmAlert.setContentText("This will reset theme, notifications, and preferences.");

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    resetToDefaults();
                    notificationManager.showSuccess("Settings reset to defaults!");
                }
            });
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(testNotificationBtn, resetBtn);

        additionalSettings.getChildren().addAll(
                new Label("Additional Settings"),
                autoSaveBox,
                soundBox,
                autoDarkBox,
                new Separator(),
                buttonBox
        );


    }

    private void resetToDefaults() {
        ThemeManager.setTheme(Theme.LIGHT);
        notificationManager.setNotificationsEnabled(true);
        notificationManager.setReminderTime(LocalTime.of(9, 0));

        if (notificationsCheckBox != null) {
            notificationsCheckBox.setSelected(true);
        }

        if (reminderHourSpinner != null) {
            reminderHourSpinner.getValueFactory().setValue(9);
        }

        if (reminderMinuteSpinner != null) {
            reminderMinuteSpinner.getValueFactory().setValue(0);
        }

        if (currentThemeLabel != null) {
            currentThemeLabel.setText("Current: Light");
        }

        setupThemeSelector();
    }

    @FXML
    private void handleSaveSettings() {
        notificationManager.showSuccess("Settings saved successfully!");
        System.out.println("✓ Settings saved");
    }

    @FXML
    private void handleExportData() {
        notificationManager.showInfo("Export feature coming soon!");
    }

    @FXML
    private void handleImportData() {
        notificationManager.showInfo("Import feature coming soon!");
    }
}
