package com.habittracker.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HabitsController {

    @FXML private Label titleLabel;

    @FXML
    public void initialize() {
        System.out.println("✓ HabitsController initialized");
        if (titleLabel != null) {
            titleLabel.setText("Manage Your Habits");
        }
    }
}
