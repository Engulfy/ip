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
                            System.out.println("    " + (i + 1) + ". " + tasks[i]);
                        }
                    }
                    continue;

                case "mark":
                    try {
                        int taskNumber = Integer.parseInt(index);
                        if (taskNumber >= 1 && taskNumber <= taskCount) {
                            tasks[taskNumber - 1].markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("    " + tasks[taskNumber - 1]);
                        } else {
                            System.out.println("Task number out of range.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please specify a valid task number.");
                    }
                    continue;

                case "unmark":
                    try {
                        int taskNumber = Integer.parseInt(index);
                        if (taskNumber >= 1 && taskNumber <= taskCount) {
                            tasks[taskNumber - 1].markAsNotDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("    " + tasks[taskNumber - 1]);
                        } else {
                            System.out.println("Task number out of range.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please specify a valid task number.");
                    }
                    continue;

                default:
                    if (instruction.equals("todo")) {
                        tasks[taskCount] = new Todo(inputArr[1]);
                    } else if (instruction.equals("deadline")) {
                        String[] deadlineSplit = inputArr[1].split(" /by ", 2);
                        tasks[taskCount] = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                    } else {
                        String[] eventSplit = inputArr[1].split(" /from | /to ");
                        tasks[taskCount] = new Event(eventSplit[0], eventSplit[1], eventSplit[2]);
                    }
                    System.out.println("Got it. I've added this task: \n    " + tasks[taskCount]);
                    taskCount++;
                    String task_form = "task";
                    if (taskCount > 1) {
                        task_form = "tasks";
                    }
                    System.out.printf("Now you have %d %s in the list.\n", taskCount, task_form);
            }
        }
    }
}
