# 🎯Habit Tracker

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-21-blue?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.9-red?style=for-the-badge&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

**A modern, feature-rich desktop application for tracking and building better habits**

[Features](#-features) • [Installation](#-installation) • [Usage](#-usage) • [Technologies](#-technologies) • [Contributing](#-contributing)

</div>

---

## 📋 **Table of Contents**

- [Overview](#-overview)
- [Features](#-features)
- [Installation](#-installation)
- [Usage](#-usage)
- [Technologies](#-technologies)
- [Project Structure](#-project-structure)
- [Configuration](#-configuration)
- [Development](#-development)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)
- [Acknowledgments](#-acknowledgments)

---

## 🌟 **Overview**

Habit Tracker is a comprehensive desktop application built with JavaFX that helps you build and maintain positive habits. With features like streak tracking, detailed analytics, calendar view, and customizable themes, it provides everything you need to stay motivated and achieve your goals.

### **Why Use Habit Tracker?**

- ✅ **Visual Progress Tracking** - See your progress at a glance
- 📊 **Advanced Analytics** - Understand your habit patterns with charts
- 📅 **Calendar View** - Track your entire month's performance
- 🔔 **Smart Notifications** - Never miss your daily habits
- 🎨 **Customizable Themes** - 6 beautiful themes to choose from
- 💾 **Data Persistence** - Your data is saved automatically
- 🏆 **Gamification** - Earn points and maintain streaks

---

## ✨ **Features**

### **Core Features**

#### 📊 Dashboard
- Real-time habit tracking with checkboxes
- Progress bar showing daily completion
- Statistics cards (Total Habits, Completed, Streak, Points)
- Weekly progress chart
- Add/Edit/Delete habits
- Category-based organization (Study, Exercise, Meditation, Outdoor)

#### 📅 Calendar View
- Monthly calendar with habit completion visualization
- Color-coded days (Green: Great, Yellow: Good, Orange: Okay)
- Navigate between months
- Quick jump to today
- Visual history of your habits

#### 📈 Statistics & Analytics
- **Pie Chart** - Category distribution
- **Line Chart** - Weekly trend analysis
- **Performance Metrics** - Completion rate, average streak
- **Best Streak Tracker** - Monitor your longest streak
- **Top Category** - See where you're most consistent

#### ⚙️ Settings & Customization
- **6 Themes**: Light, Dark, Ocean, Sunset, Forest, Purple
- **Notification Settings** - Enable/disable different notification types
- **Daily Reminders** - Set custom reminder time (hour & minute)
- **Auto-save** - Never lose your data
- **Export/Import** - Backup and restore your habits

#### 🔔 Notifications
- Toast notifications for habit completion
- Streak milestone celebrations
- Daily reminders at custom times
- Achievement unlocks
- Success/Warning/Error notifications

---

## 🚀 **Installation**

### **Prerequisites**

- **Java Development Kit (JDK) 17 or higher**
    - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
- **Apache Maven 3.6+**
    - Download from: [Maven Official Site](https://maven.apache.org/download.cgi)
- **Git** (for cloning)
    - Download from: [Git Official Site](https://git-scm.com/downloads)

### **Clone the Repository**

```bash
git clone https://github.com/YOUR_USERNAME/AdvancedHabitTracker.git
cd AdvancedHabitTracker
```

### **Build & Run**

```bash
# Clean and compile
mvn clean compile

# Run the application
mvn javafx:run
```

### **Create Executable JAR**

```bash
# Build JAR with dependencies
mvn clean package

# Run the JAR
java -jar target/advanced-habittracker-2.0-SNAPSHOT.jar
```

---

## 💻 **Usage**

### **Quick Start**

1. **Launch the Application**
   ```bash
   mvn javafx:run
   ```

2. **Add Your First Habit**
    - Click the "Add Habit" button
    - Enter habit name (e.g., "Morning Exercise")
    - Select category (Study, Exercise, Meditation, or Outdoor)
    - Click "Add"

3. **Track Your Progress**
    - Check the box when you complete a habit
    - Watch your streak increase 🔥
    - Earn points for each completion 🏆

4. **Explore Features**
    - Click on different tabs: Dashboard, Calendar, Statistics, Settings
    - Try different themes in Settings
    - Set up daily reminders

### **Navigation**

- **🏠 Dashboard** - Track today's habits
- **📅 Calendar** - View monthly history
- **📊 Statistics** - Analyze your performance
- **⚙️ Settings** - Customize preferences

### **Keyboard Shortcuts**

- `Ctrl + N` - Add new habit
- `Ctrl + S` - Save settings
- `Ctrl + T` - Toggle theme
- `F5` - Refresh data

---

## 🛠️ **Technologies**

### **Core Technologies**

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| JavaFX | 21.0.1 | GUI Framework |
| Maven | 3.9+ | Build Tool |
| FXML | 1.0 | UI Layout |
| CSS3 | - | Styling |

### **Libraries & Dependencies**

- **javafx-controls** - UI components
- **javafx-fxml** - FXML support
- **javafx-graphics** - Graphics rendering
- **javafx-base** - Base JavaFX classes
- **ControlsFX** (11.1.2) - Enhanced UI controls and notifications

### **Architecture**

- **Design Pattern**: MVC (Model-View-Controller)
- **Build System**: Maven
- **Dependency Management**: Maven POM
- **Version Control**: Git

---

## 📁 **Project Structure**

```
AdvancedHabitTracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── habittracker/
│   │   │           ├── AdvancedHabitTrackerApp.java    # Main application
│   │   │           ├── controller/                     # Controllers
│   │   │           │   ├── DashboardController.java
│   │   │           │   ├── CalendarController.java
│   │   │           │   ├── StatisticsController.java
│   │   │           │   └── SettingsController.java
│   │   │           ├── model/                          # Data models
│   │   │           │   ├── Habit.java
│   │   │           │   ├── HabitTracker.java
│   │   │           │   ├── Statistics.java
│   │   │           │   ├── WeatherData.java
│   │   │           │   ├── UserSettings.java
│   │   │           │   └── Achievement.java
│   │   │           ├── service/                        # Business logic
│   │   │           │   ├── HabitAPIService.java
│   │   │           │   ├── WeatherService.java
│   │   │           │   ├── NotificationService.java
│   │   │           │   ├── DataExportService.java
│   │   │           │   └── QuoteService.java
│   │   │           └── util/                           # Utilities
│   │   │               ├── ThemeManager.java
│   │   │               ├── NotificationManager.java
│   │   │               └── APIConfig.java
│   │   └── resources/
│   │       └── com/
│   │           └── habittracker/
│   │               └── view/                           # FXML layouts
│   │                   ├── dashboard.fxml
│   │                   ├── calendar.fxml
│   │                   ├── statistics.fxml
│   │                   └── settings.fxml
│   └── test/                                           # Unit tests
├── docs/                                               # Documentation
│   └── guides/                                         # User guides
├── .gitignore                                          # Git ignore rules
├── pom.xml                                             # Maven configuration
├── README.md                                           # This file
└── LICENSE                                             # MIT License
```

---

## ⚙️ **Configuration**

### **Application Settings**

Edit `src/main/java/com/habittracker/util/APIConfig.java`:

```java
// Notification Settings
public static final int REMINDER_HOUR = 9;          // Default: 9 AM
public static final int REMINDER_MINUTE = 0;        // Default: 0 minutes
public static final boolean ENABLE_NOTIFICATIONS = true;

// Feature Flags
public static final boolean ENABLE_CALENDAR_VIEW = true;
public static final boolean ENABLE_ADVANCED_CHARTS = true;
public static final boolean ENABLE_ACHIEVEMENTS = true;
```

### **Theme Customization**

Available themes in `ThemeManager.java`:
- Light (Default)
- Dark
- Ocean
- Sunset
- Forest
- Purple

---

## 👨‍💻 **Development**

### **Setting Up Development Environment**

1. **Clone the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/AdvancedHabitTracker.git
   cd AdvancedHabitTracker
   ```

2. **Open in IntelliJ IDEA**
    - File → Open → Select project folder
    - IntelliJ will automatically detect Maven project

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run from IntelliJ**
    - Right-click on `AdvancedHabitTrackerApp.java`
    - Select "Run 'AdvancedHabitTrackerApp.main()'"

### **Running Tests**

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=HabitTrackerTest
```

### **Building Documentation**

```bash
# Generate Javadoc
mvn javadoc:javadoc

# View documentation
open target/site/apidocs/index.html
```

---

## 🤝 **Contributing**

Contributions are welcome! Here's how you can help:

### **How to Contribute**

1. **Fork the Project**
2. **Create your Feature Branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. **Commit your Changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. **Push to the Branch**
   ```bash
   git push origin feature/AmazingFeature
   ```
5. **Open a Pull Request**

### **Contribution Guidelines**

- Follow the existing code style
- Write meaningful commit messages
- Add tests for new features
- Update documentation
- Test your changes thoroughly

### **Bug Reports**

If you find a bug, please create an issue with:
- Description of the bug
- Steps to reproduce
- Expected behavior
- Screenshots (if applicable)
- System information (OS, Java version)

---

## 📝 **License**

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2026 Rafiul Islam

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```

---

## 📞 **Contact**

**Rafiul Islam**
- GitHub: https://github.com/rafiul254
- Email: rafuulislam2004@gmail.com
- LinkedIn: https://www.linkedin.com/in/rafi-ul-islam-b9485233b

---

## 🙏 **Acknowledgments**

- [JavaFX](https://openjfx.io/) - UI Framework
- [ControlsFX](https://controlsfx.github.io/) - Enhanced Controls
- [Maven](https://maven.apache.org/) - Build Tool
- [Font Awesome](https://fontawesome.com/) - Icons inspiration
- [Shields.io](https://shields.io/) - README badges
- All contributors who helped improve this project

---

## 🎯 **Roadmap**

### **Version 2.1 (Planned)**
- [ ] Data export to CSV/JSON
- [ ] Cloud synchronization
- [ ] Mobile app companion
- [ ] Habit templates
- [ ] Social features (share achievements)

### **Version 2.2 (Future)**
- [ ] AI-powered habit suggestions
- [ ] Integration with fitness trackers
- [ ] Multi-language support
- [ ] Advanced reporting
- [ ] Team challenges

---

## 📊 **Project Stats**

- **Lines of Code**: ~5,000+
- **Files**: 25+
- **Features**: 30+
- **Themes**: 6
- **Charts**: 2 types (Pie, Line)

---

## ⭐ **Star History**

If you find this project helpful, please consider giving it a star! ⭐

[![Star History Chart](https://api.star-history.com/svg?repos=rafiul254/HabitTracker&type=Date)]

---

<div align="center">

**Made with ❤️ and ☕ by [Rafiul Islam]( https://github.com/rafiul254)**

**[⬆ Back to Top](#habit-tracker)**

</div>
