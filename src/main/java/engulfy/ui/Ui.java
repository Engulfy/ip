package engulfy.ui;

import java.util.List;
import java.util.Scanner;

import engulfy.task.Task;

/**
 * Represents the UI component that handles user interaction, including displaying messages,
 * reading user input, and showing task lists.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Initializes the Ui object with a new scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello there! I'm Engulfy");
        System.out.println("How can I assist you?");
    }

    /**
     * Display the goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Awww, will i see you again soon?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Show the list of tasks to the user.
     *
     * @param tasks List of tasks to display
     */
    public void showTaskList(List<Task> tasks) {
        System.out.println("hMmmm your day looks quite productive so far:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when no tasks are available in the list.
     */
    public void showNoTasks() {
        System.out.println("No saved tasks yet!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Read the user's command.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Display an error message.
     *
     * @param message The error message to display
     */
    public void showError(String message) {
        System.out.println("OOPS! " + message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Show loading error when tasks cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("I can't seem to load the file ;-; let's start again.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is added successfully.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks after adding the new task.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Keep up the momentum! Continue adding more tasks!");
        System.out.println("    " + task);
        System.out.printf("Now you have %d %s in the list.%n", totalTasks, totalTasks == 1 ? "task" : "tasks");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is removed successfully.
     *
     * @param task The task that was removed.
     * @param totalTasks The total number of tasks after removing the task.
     */
    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("NICEE! Looks like you no longer needs this task here :D");
        System.out.println("    " + task);
        System.out.printf("Now you have %d %s in the list.%n", totalTasks, totalTasks == 1 ? "task" : "tasks");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        String taskString = task.toString().trim();
        System.out.print("NICEE! Keep up the good work!\n"
                + "    " + taskString + "\n"
                + "____________________________________________________________\n");
    }

    /**
     * Displays a message when a task is unmarked (set as not done).
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        String taskString = task.toString().trim();
        System.out.print("Aww, it's ok! You got this!\n"
                + "    " + taskString + "\n"
                + "____________________________________________________________\n");
    }
}
