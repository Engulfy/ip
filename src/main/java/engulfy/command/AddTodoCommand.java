package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.task.Todo;

/**
 * A command for adding a to-do task.
 * Parses the arguments to extract the task description.
 */
public class AddTodoCommand extends AddCommand {
    /**
     * Constructs an AddTodoCommand with the given arguments.
     *
     * @param arguments The arguments containing the description of the to-do task.
     * @throws EngulfyError If the arguments are empty.
     */
    public AddTodoCommand(String arguments) throws EngulfyError {
        assert arguments != null : "Arguments should not be null";
        if (arguments.isEmpty()) {
            throw new EngulfyError("Zenitsu needs a description to help you keep track ;-;");
        }

        setTask(new Todo(arguments));
    }
}
