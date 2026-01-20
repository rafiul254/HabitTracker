package com.habittracker.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Random;

public class CalendarController {

    @FXML private Label monthYearLabel;
    @FXML private GridPane calendarGrid;
    @FXML private VBox calendarContainer;

    private YearMonth currentMonth;
    private Random random = new Random();

    @FXML
    public void initialize() {
        System.out.println("✓ CalendarController initialized");
        currentMonth = YearMonth.now();
        createCalendar();
    }

    private void createCalendar() {
        if (calendarGrid == null) return;

        calendarGrid.getChildren().clear();

        if (monthYearLabel != null) {
            monthYearLabel.setText(currentMonth.getMonth() + " " + currentMonth.getYear());
        }


        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < 7; i++) {
            Label dayLabel = new Label(days[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #666;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setAlignment(Pos.CENTER);
            calendarGrid.add(dayLabel, i, 0);
        }

        LocalDate firstDay = currentMonth.atDay(1);
        int dayOfWeek = firstDay.getDayOfWeek().getValue() % 7; // Sunday = 0
        int daysInMonth = currentMonth.lengthOfMonth();

        int row = 1;
        int col = dayOfWeek;

        for (int day = 1; day <= daysInMonth; day++) {
            VBox dayCell = createDayCell(day);
            calendarGrid.add(dayCell, col, row);

            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }

        System.out.println("✓ Calendar created for " + currentMonth);
    }

    private VBox createDayCell(int day) {
        VBox cell = new VBox(5);
        cell.setAlignment(Pos.TOP_CENTER);
        cell.setPrefSize(100, 100);
        cell.setStyle("-fx-background-color: white; -fx-border-color: #ddd; -fx-border-width: 1; -fx-padding: 8;");

        Label dayLabel = new Label(String.valueOf(day));
        dayLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        int completedHabits = random.nextInt(5);

        if (completedHabits > 0) {
            Rectangle indicator = new Rectangle(60, 8);
            double percentage = completedHabits / 4.0;

            if (percentage >= 0.75) {
                indicator.setFill(Color.web("#4CAF50")); // Green - Great
            } else if (percentage >= 0.5) {
                indicator.setFill(Color.web("#FFC107")); // Yellow - Good
            } else {
                indicator.setFill(Color.web("#FF9800")); // Orange - Okay
            }

            indicator.setArcWidth(4);
            indicator.setArcHeight(4);

            Label count = new Label(completedHabits + "/4");
            count.setStyle("-fx-font-size: 10px; -fx-text-fill: #666;");

            cell.getChildren().addAll(dayLabel, indicator, count);
        } else {
            cell.getChildren().add(dayLabel);
        }

        LocalDate today = LocalDate.now();
        if (day == today.getDayOfMonth() &&
                currentMonth.getMonth() == today.getMonth() &&
                currentMonth.getYear() == today.getYear()) {
            cell.setStyle("-fx-background-color: #E3F2FD; -fx-border-color: #2196F3; -fx-border-width: 2; -fx-padding: 8;");
        }

        return cell;
    }

    @FXML
    private void handlePreviousMonth() {
        currentMonth = currentMonth.minusMonths(1);
        createCalendar();
    }

    @FXML
    private void handleNextMonth() {
        currentMonth = currentMonth.plusMonths(1);
        createCalendar();
    }

    @FXML
    private void handleToday() {
        currentMonth = YearMonth.now();
        createCalendar();
    }
}
