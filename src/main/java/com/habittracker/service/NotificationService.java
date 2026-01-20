package com.habittracker.service;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NotificationService {

    public void showNotification(String title, String message) {
        System.out.println("📢 Notification: " + title + " - " + message);
    }

    public void showSuccessNotification(String message) {
        showNotification("Success", message);
    }

    public void showErrorNotification(String message) {
        showNotification("Error", message);
    }

    public void showHabitReminder(String habitName) {
        showNotification("Reminder", "Time to complete: " + habitName);
    }

    public void showAlert(String title, String message, AlertType type) {
        try {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        } catch (Exception e) {
            System.err.println("Alert error: " + e.getMessage());
        }
    }

    public void showInfo(String title, String message) {
        showAlert(title, message, AlertType.INFORMATION);
    }

    public void showError(String title, String message) {
        showAlert(title, message, AlertType.ERROR);
    }
}
