import java.util.Scanner;

public class Engulfy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Engulfy");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String userInput = sc.nextLine();
            String[] inputArr = userInput.split(" ", 2);
            String instruction = inputArr[0];
            String index = inputArr.length > 1 ? inputArr[1] : "";
            System.out.println("____________________________________________________________");

            switch (instruction) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    sc.close();
                    return;

                case "list":
                    if (taskCount == 0) {
                        System.out.println("No tasks yet!");
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + ". " + tasks[i]);
                        }
                        System.out.println("____________________________________________________________");
                    }
                    continue;

                case "mark":
                    try {
                        int taskNumber = Integer.parseInt(index);
                        if (taskNumber >= 1 && taskNumber <= taskCount) {
                            tasks[taskNumber - 1].markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(tasks[taskNumber - 1]);
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println("Task number out of range.");
                            System.out.println("____________________________________________________________");

                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please specify a valid task number.");
                        System.out.println("____________________________________________________________");
                    }
                    continue;

                case "unmark":
                    try {
                        int taskNumber = Integer.parseInt(index);
                        if (taskNumber >= 1 && taskNumber <= taskCount) {
                            tasks[taskNumber - 1].markAsNotDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(tasks[taskNumber - 1]);
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println("Task number out of range.");
                            System.out.println("____________________________________________________________");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please specify a valid task number.");
                        System.out.println("____________________________________________________________");
                    }
                    continue;

                default:
                    tasks[taskCount] = new Task(userInput);
                    taskCount++;
                    System.out.println("added: " + userInput);
                    System.out.println("____________________________________________________________");
                    continue;
            }
        }
    }
}
