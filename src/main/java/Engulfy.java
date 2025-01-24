import java.util.Scanner;
import java.util.ArrayList;

public class Engulfy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Engulfy");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            String userInput = sc.nextLine();
            String[] inputArr = userInput.split(" ", 2);
            String instruction = inputArr[0];
            String index = inputArr.length > 1 ? inputArr[1] : "";
            System.out.println("____________________________________________________________");

            try {
                switch (instruction) {
                    case "bye":
                        validateInstr(inputArr, "I do not fully understand what this means :/");
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        sc.close();
                        return;

                    case "list":
                        validateInstr(inputArr, "I do not fully understand what this means :/");
                        if (!tasks.isEmpty()) {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
                            }
                        } else {
                            System.out.println("No tasks yet!");
                            System.out.println("____________________________________________________________");
                        }
                        continue;

                    case "delete":
                        try {
                            int deleteIndex = Integer.parseInt(index);
                            if (deleteIndex >= 1 && deleteIndex <= tasks.size()) {
                                Task removedTask = tasks.remove(deleteIndex - 1);
                                System.out.println("Noted. I've removed this task:\n    " + removedTask);
                                System.out.printf("Now you have %d %s in the list.%n", tasks.size(), tasks.size() == 1 ? "task" : "tasks");
                            } else {
                                throw new EngulfyErrors("Your task number is not in my database to be deleted! try again :D");
                            }
                        } catch (EngulfyErrors e) {
                            System.out.println(e.getMessage());
                        }
                        continue;

                    case "mark":
                        try {
                            int taskNumber = Integer.parseInt(index);
                            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                                tasks.get(taskNumber - 1).markAsDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println("    " + tasks.get(taskNumber - 1));
                            } else {
                                throw new EngulfyErrors("Your task number is a little TOOOO big or small! try again :D");
                            }
                        } catch (EngulfyErrors e) {
                            System.out.println(e.getMessage());
                        }
                        continue;

                    case "unmark":
                        try {
                            int taskNumber = Integer.parseInt(index);
                            if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                                tasks.get(taskNumber - 1).markAsNotDone();
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println("    " + tasks.get(taskNumber - 1));
                            } else {
                                throw new EngulfyErrors("Your task number is a little TOOOO big or small! try again :D");
                            }
                        } catch (EngulfyErrors e) {
                            System.out.println(e.getMessage());
                        }
                        continue;

                    default:
                        try {
                            if (instruction.equals("todo")) {
                                validateInstr2(inputArr, "I need a description to help you keep track ;-;");
                                tasks.add(tasks.size(), new Todo(inputArr[1]));
                            } else if (instruction.equals("deadline")) {
                                validateInstr2(inputArr, "I need a description to help you keep track ;-;");
                                String[] deadlineSplit = inputArr[1].split(" /by ", 2);
                                tasks.add(tasks.size(), new Deadline(deadlineSplit[0], deadlineSplit[1]));
                            } else if (instruction.equals("event")) {
                                validateInstr2(inputArr, "I need a description to help you keep track ;-;");
                                String[] eventSplit = inputArr[1].split(" /from | /to ");
                                tasks.add(tasks.size(), new Event(eventSplit[0], eventSplit[1], eventSplit[2]));
                            } else {
                                throw new EngulfyErrors("I AM SO SORRY!! But this is not something I am capable of doing for now ;-;");
                            }
                            System.out.println("Got it. I've added this task:\n    " + tasks.get(tasks.size() - 1));
                            String task_form = "task";
                            if (tasks.size() > 1) {
                                task_form = "tasks";
                            }
                            System.out.printf("Now you have %d %s in the list.\n", tasks.size() , task_form);
                        } catch (EngulfyErrors e) {
                            System.out.println(e.getMessage());
                        }
                }
            } catch (EngulfyErrors e) {
                System.out.println("OOPS! " + e.getMessage());
            }
        }
    }

    //to check if its just list/bye instead of erroneous messages
    private static void validateInstr(String[] inputArr, String errorMessage) throws EngulfyErrors {
        if (inputArr.length > 1) {
            throw new EngulfyErrors(errorMessage);
        }
    }
    //to check for todo/deadline/event if their description is given
    private static void validateInstr2(String[] inputArr, String errorMessage) throws EngulfyErrors {
        if (inputArr.length < 2) {
            throw new EngulfyErrors(errorMessage);
        }
    }
}
