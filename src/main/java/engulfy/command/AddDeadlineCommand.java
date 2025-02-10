package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.task.Deadline;

/**
 * A command for adding a deadline task.
 * Parses the arguments to extract the task description and due date.
 */
public class AddDeadlineCommand extends AddCommand {
    /**
     * Constructs an AddDeadlineCommand with the given arguments.
     *
     * @param arguments The arguments containing the description and deadline.
     * @throws EngulfyError If the arguments are empty or incorrectly formatted.
     */
    public AddDeadlineCommand(String arguments) throws EngulfyError {
        if (arguments.isEmpty()) {
            throw new EngulfyError("I need a description to help you keep track ;-;");
        }

        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new EngulfyError("Engulfy does not understand your date! Use: deadline <description> /by <datetime>");
        }

        setTask(new Deadline(parts[0], parts[1]));
    }
}