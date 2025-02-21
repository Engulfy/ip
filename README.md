# Engulfy User Guide

## ⚡ Introduction ⚡
Welcome to **Engulfy**, your personal task manager chatbot with a theme revolving around Zenitsu!

![Example Image](/docs/Ui.png)

## 🚀 Installation
1. **Ensure Java 17 or later** is installed on your system.
2. **Download** the latest Engulfy JAR file from the [release page](https://github.com/Engulfy/ip/releases).
3. **Open a terminal** and navigate to the folder containing the JAR file.
4. Run the following command to start Engulfy:
   ```sh
   java -jar Engulfy.jar

## 🛠 Features
### ✅ Add Tasks
Engulfy supports three types of tasks:
- **ToDo**: `todo TASK_DESCRIPTION`
- **Deadline**: `deadline TASK_DESCRIPTION /by DATE_TIME`
- **Event**: `event TASK_DESCRIPTION /from DATE_TIME /to DATE_TIME`
- **To Note**:
   - `Ensure DATE_TIME is in format of "M/d/yyyy HHmm" (default), "MMM dd yyyy, h:mm a", "d/M/yyyy HHmm" or "dd MMM yyyy, h:mm a"`

📌 **Example:**
```sh
todo SWORD TRAINING!
deadline MASTER LIGHTNING BREATHING! /by 02/20/2025 2359
event HASHIRA TRAINING /from 01/03/2018 1400 /to 01/03/2018 1600
```

### 📋 List Tasks
View all added tasks:
```sh
list
```

### ✍️ Mark/Unmark Tasks
Mark a task as completed or incomplete:
- `To Note: Ensure TASK_NUMBER is in range of saved tasks`
```sh
mark TASK_NUMBER
unmark TASK_NUMBER
```

### ❌ Delete Tasks
Remove a specific task:
- `To Note: Ensure TASK_NUMBER is in range of saved tasks`
```sh
delete TASK_NUMBER
```

### 🔍 Find Tasks
Search for tasks containing a keyword:
```sh
find KEYWORD
```

### 🏷 Tag Tasks
Add tags to tasks to categorize them:
```sh
tag TASK_NUMBER TAG_NAME
```

### 🚪 Exit Engulfy
Close the chatbot:
```sh
bye
```