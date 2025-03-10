package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.task.Event;

/**
 * A command for adding an event task.
 * Parses the arguments to extract the task description, start time, and end time.
 */
public class AddEventCommand extends AddCommand {
    /**
     * Constructs an AddEventCommand with the given arguments.
     *
     * @param arguments The arguments containing the description, start time, and end time.
     * @throws EngulfyError If the arguments are empty or incorrectly formatted.
     */
    public AddEventCommand(String arguments) throws EngulfyError {
        assert arguments != null : "Arguments should not be null";
        if (arguments.isEmpty()) {
            throw new EngulfyError("I need a description to help you keep track ;-;");
        }

        String[] parts = arguments.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new EngulfyError("Zenitsu does not understand your date!\n "
                    + "Use: event <description> /from <datetime> /to <datetime>");
        }

        String[] timeParts = parts[1].split(" /to ", 2);
        setTask(new Event(parts[0], timeParts[0], timeParts[1]));
    }
}
