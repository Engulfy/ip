package Engulfy.Ui;

import Engulfy.Task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Engulfy.Command.Engulfy");
        System.out.println("What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println("Here are your saved tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void showNoTasks() {
        System.out.println("No saved tasks yet!");
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println("OOPS! " + message);
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file. Starting with empty list.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.printf("Now you have %d %s in the list.%n", totalTasks, totalTasks == 1 ? "task" : "tasks");
        System.out.println("____________________________________________________________");
    }

    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.printf("Now you have %d %s in the list.%n", totalTasks, totalTasks == 1 ? "task" : "tasks");
        System.out.println("____________________________________________________________");
    }

    public void showTaskMark(Task task) {
        String taskString = task.toString().trim();
        System.out.print("Nice! I've marked this task as done:\n" +
                "    " + taskString + "\n" +
                "____________________________________________________________\n");
    }

    public void showTaskUnmark(Task task) {
        String taskString = task.toString().trim();
        System.out.print("OK, I've marked this task as not done yet:\n" +
                "    " + taskString + "\n" +
                "____________________________________________________________\n");
    }
}