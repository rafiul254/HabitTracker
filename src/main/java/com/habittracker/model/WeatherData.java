package com.habittracker.model;
public class WeatherData {
    private int temperature;
    private String condition;
    private String location;
    private String icon;

    public WeatherData() {
        this.temperature = 25;
        this.condition = "Sunny";
        this.location = "Dhaka";
        this.icon = "☀️";
    }

    public WeatherData(int temperature, String condition, String location) {
        this.temperature = temperature;
        this.condition = condition;
        this.location = location;
        this.icon = getIconForCondition(condition);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
        this.icon = getIconForCondition(condition);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String getIconForCondition(String condition) {
        switch (condition.toLowerCase()) {
            case "sunny":
            case "clear":
                return "☀️";
            case "cloudy":
            case "partly cloudy":
                return "☁️";
            case "rainy":
            case "rain":
                return "🌧️";
            case "windy":
                return "💨";
            default:
                return "🌤️";
        }
    }

    @Override
    public String toString() {
        return icon + " " + temperature + "°C - " + condition;
    }
}
