import java.util.Scanner;

public class Engulfy {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Engulfy");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            System.out.println("____________________________________________________________");

            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                sc.close();
                return;
            } else {
                System.out.println(userInput);
                System.out.println("____________________________________________________________");
                continue;
            }
        }
    }
}
