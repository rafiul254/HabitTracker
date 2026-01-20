package com.habittracker.util;

import javafx.scene.Scene;

public class ThemeManager {
    private static Theme currentTheme = Theme.LIGHT;
    private static Scene currentScene;

    public enum Theme {
        LIGHT("Light", "#f5f5f5", "#000000"),
        DARK("Dark", "#1e1e1e", "#ffffff"),
        OCEAN("Ocean", "#0d47a1", "#ffffff"),
        SUNSET("Sunset", "#ff6f00", "#000000"),
        FOREST("Forest", "#1b5e20", "#ffffff"),
        PURPLE("Purple", "#4a148c", "#ffffff");

        private final String name;
        private final String backgroundColor;
        private final String textColor;

        Theme(String name, String bgColor, String textColor) {
            this.name = name;
            this.backgroundColor = bgColor;
            this.textColor = textColor;
        }

        public String getName() { return name; }
        public String getBackgroundColor() { return backgroundColor; }
        public String getTextColor() { return textColor; }
    }

    public static void setCurrentScene(Scene scene) {
        currentScene = scene;
    }

    public static void applyTheme(Scene scene, Theme theme) {
        currentTheme = theme;

        String style = String.format(
                "-fx-background-color: %s; -fx-text-fill: %s;",
                theme.getBackgroundColor(),
                theme.getTextColor()
        );

        scene.getRoot().setStyle(style);
        System.out.println("✓ Theme applied: " + theme.getName());
    }

    public static void setTheme(Theme theme) {
        currentTheme = theme;
        if (currentScene != null) {
            applyTheme(currentScene, theme);
        }
    }

    public static Theme getCurrentTheme() {
        return currentTheme;
    }

    public static Theme[] getAllThemes() {
        return Theme.values();
    }
}
