package com.habittracker.util;
public class APIConfig {

    public static final boolean USE_REAL_WEATHER_API = false;
    public static final String WEATHER_API_KEY = "";
    public static final String WEATHER_API_URL = "";


    public static final int CONNECTION_TIMEOUT = 5000;
    public static final int READ_TIMEOUT = 5000;
    public static final boolean ENABLE_NOTIFICATIONS = true;
    public static final boolean AUTO_SAVE = true;


    public static final boolean ENABLE_CLOUD_SYNC = false;
    public static final boolean ENABLE_ANALYTICS = true;
    public static final boolean ENABLE_ACHIEVEMENTS = true;
    public static final boolean ENABLE_CALENDAR_VIEW = true;
    public static final boolean ENABLE_ADVANCED_CHARTS = true;


    public static final String APP_NAME = "Advanced Habit Tracker";
    public static final String APP_VERSION = "2.0.0";
    public static final String APP_AUTHOR = "Your Name";


    public static final int REMINDER_HOUR = 9;
    public static final int REMINDER_MINUTE = 0;
    public static final boolean SHOW_STREAK_NOTIFICATIONS = true;
    public static final boolean SHOW_COMPLETION_NOTIFICATIONS = true;

    public static boolean isWeatherAPIEnabled() {
        return USE_REAL_WEATHER_API && !WEATHER_API_KEY.isEmpty();
    }

    public static boolean isFeatureEnabled(String featureName) {
        switch (featureName) {
            case "cloud_sync":
                return ENABLE_CLOUD_SYNC;
            case "analytics":
                return ENABLE_ANALYTICS;
            case "achievements":
                return ENABLE_ACHIEVEMENTS;
            case "notifications":
                return ENABLE_NOTIFICATIONS;
            case "calendar":
                return ENABLE_CALENDAR_VIEW;
            case "charts":
                return ENABLE_ADVANCED_CHARTS;
            default:
                return false;
        }
    }
}
