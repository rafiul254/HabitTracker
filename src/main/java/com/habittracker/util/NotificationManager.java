package com.habittracker.util;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationManager {
    private static NotificationManager instance;
    private boolean notificationsEnabled = true;
    private Timer reminderTimer;
    private LocalTime reminderTime = LocalTime.of(9, 0); // 9 AM default

    private NotificationManager() {
        startReminderScheduler();
    }

    public static NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    public void showNotification(String title, String message, NotificationType type) {
        if (!notificationsEnabled) {
            return;
        }

        Platform.runLater(() -> {
            try {
                Notifications notification = Notifications.create()
                        .title(title)
                        .text(message)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT);

                switch (type) {
                    case SUCCESS:
                        notification.showInformation();
                        break;
                    case ERROR:
                        notification.showError();
                        break;
                    case WARNING:
                        notification.showWarning();
                        break;
                    case INFO:
                    default:
                        notification.showInformation();
                        break;
                }
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.show();
            }
        });
    }

    public void showSuccess(String message) {
        showNotification("Success ✓", message, NotificationType.SUCCESS);
    }

    public void showError(String message) {
        showNotification("Error ✗", message, NotificationType.ERROR);
    }

    public void showWarning(String message) {
        showNotification("Warning ⚠", message, NotificationType.WARNING);
    }

    public void showInfo(String message) {
        showNotification("Info ℹ", message, NotificationType.INFO);
    }

    public void showHabitCompleted(String habitName) {
        showSuccess("Great job! You completed: " + habitName + " 🎉");
    }

    public void showStreakMilestone(String habitName, int streak) {
        showSuccess("Amazing! " + streak + " day streak on " + habitName + " 🔥");
    }

    public void showDailyReminder() {
        showInfo("Time to check your habits! Don't break your streak! 💪");
    }

    public void setNotificationsEnabled(boolean enabled) {
        this.notificationsEnabled = enabled;
        System.out.println("Notifications " + (enabled ? "enabled" : "disabled"));
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setReminderTime(LocalTime time) {
        this.reminderTime = time;
        restartReminderScheduler();
    }

    public LocalTime getReminderTime() {
        return reminderTime;
    }

    private void startReminderScheduler() {
        reminderTimer = new Timer(true);


        reminderTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalTime now = LocalTime.now();
                if (now.getHour() == reminderTime.getHour() &&
                        now.getMinute() == reminderTime.getMinute()) {
                    showDailyReminder();
                }
            }
        }, 0, 60000); // Check every minute
    }

    private void restartReminderScheduler() {
        if (reminderTimer != null) {
            reminderTimer.cancel();
        }
        startReminderScheduler();
    }

    public void shutdown() {
        if (reminderTimer != null) {
            reminderTimer.cancel();
        }
    }

    public enum NotificationType {
        SUCCESS, ERROR, WARNING, INFO
    }
}
