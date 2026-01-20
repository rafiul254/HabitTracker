package com.habittracker.service;

import com.habittracker.model.WeatherData;
import java.util.Random;

public class WeatherService {
    private static final String[] CONDITIONS = {"Sunny", "Cloudy", "Rainy", "Partly Cloudy", "Windy"};
    private static final Random random = new Random();

    public WeatherData getWeather() {
        try {
            // Simulated weather data - no API needed
            String condition = CONDITIONS[random.nextInt(CONDITIONS.length)];
            int temperature = 20 + random.nextInt(15); // 20-35°C

            WeatherData data = new WeatherData();
            data.setTemperature(temperature);
            data.setCondition(condition);
            data.setLocation("Kushtia, Bangladesh");

            System.out.println("✓ Weather loaded: " + data);
            return data;
        } catch (Exception e) {
            System.err.println("Weather service error: " + e.getMessage());
            return new WeatherData(); // Return default weather
        }
    }

    public WeatherData fetchWeather() {
        return getWeather();
    }
}
