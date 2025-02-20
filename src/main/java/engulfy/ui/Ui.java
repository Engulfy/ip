package engulfy.ui;

import java.util.List;

import engulfy.task.Task;
import engulfy.task.TaskList;

/**
 * Represents the UI component that generates messages for the GUI.
 * This class handles the generation of different messages for user interaction, such as welcome, goodbye,
 * task list display, error messages, and task-related notifications.
 */
public class Ui {
    /**
     * Generates a welcome message along with the current task list.
     *
     * @param tasks The list of tasks to display
     * @return A welcome message followed by the task list, or a message indicating no tasks are saved.
     */
    public String showWelcome(TaskList tasks) {
        assert tasks != null : "Task list cannot be null";
        String welcomeMessage = "Hello there! I'm ZENITSUUU!!\nHow can I assist you?";
        String taskMessage = tasks.isEmpty() ? "No saved tasks yet!" : showTaskList(tasks.getAllTasks());
        return (welcomeMessage + "\n" + taskMessage).trim();
    }

    /**
     * Generates a goodbye message.
     *
     * @return A goodbye message
     */
    public String showGoodbye() {
        return "Awww, will Zenitsu see you again soon?\n"
                + "DON'T GIVE UP!!!";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks List of tasks to display
     * @return A string representation of the task list
     */
    public String showTaskList(List<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        StringBuilder sb = new StringBuilder("Your tasks are saved with Zenitsu:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("    ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display
     * @return A string representing the error message
     */
    public String showError(String message) {
        return "OOPS! " + message;
    }

    /**
     * Generates a message when tasks cannot be loaded.
     *
     * @return A loading error message
     */
    public String showLoadingError() {
        return "Zenitsu can't seem to load the file ;-; let's start again.";
    }


    /**
     * Displays a message when a task is added successfully.
     *
     * @param task The task that was added
     * @param totalTasks The total number of tasks after adding the new task
     * @return A message indicating the task was added and showing the total number of tasks
     */
    public String showTaskAdded(Task task, int totalTasks) {
        assert task != null : "Task cannot be null";
        assert totalTasks >= 0 : "Total tasks cannot be negative";
        return "Keep up the momentum! Continue adding more tasks!\n"
                + "    " + task + "\n"
                + String.format("Now you have %d %s in the list.%n", totalTasks, totalTasks == 1 ? "task" : "tasks");
    }

    /**
     * Displays a message when a task is removed successfully.
     *
     * @param task The task that was removed
     * @param totalTasks The total number of tasks after removing the task
     * @return A message indicating the task was removed and showing the remaining tasks
     */
    public String showTaskRemoved(Task task, int totalTasks) {
        assert task != null : "Task cannot be null";
        assert totalTasks >= 0 : "Total tasks cannot be negative";
        return "LET ZENITSU SLASH THAT TASK AWAY FOR YOU :D\n"
                + "    " + task + "\n"
                + String.format("Now you have %d %s in the list.%n", totalTasks, totalTasks == 1 ? "task" : "tasks");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done
     * @return A message indicating the task was marked as done
     */
    public String showTaskMarked(Task task) {
        assert task != null : "Task cannot be null";
        return "NICEE! Keep up the good work!\n"
                + "    " + task;
    }

    /**
     * Displays a message when a task is unmarked (set as not done).
     *
     * @param task The task that was unmarked
     * @return A message indicating the task was unmarked
     */
    public String showTaskUnmarked(Task task) {
        assert task != null : "Task cannot be null";
        return "Aww, it's ok! Zenitsu believes in you!\n"
                + "    " + task;
    }
}
