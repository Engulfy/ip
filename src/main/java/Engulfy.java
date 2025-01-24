import java.util.Scanner;

public class Engulfy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Engulfy");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        while (true) {
            String userInput = sc.nextLine();
            System.out.println("____________________________________________________________");

            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                sc.close();
                return;
            } else if (userInput.toLowerCase().equals("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks yet!");
                    System.out.println("____________________________________________________________");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("____________________________________________________________");
                }
            } else {
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("added: "+ userInput);
                System.out.println("____________________________________________________________");
                continue;
            }
        }
    }
}
