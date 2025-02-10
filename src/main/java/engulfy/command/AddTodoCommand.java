package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.task.Todo;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String arguments) throws EngulfyError {
        if (arguments.isEmpty()) {
            throw new EngulfyError("I need a description to help you keep track ;-;");
        }
        this.task = new Todo(arguments);
    }
}