package Engulfy.Command;

import Engulfy.Error.EngulfyError;
import Engulfy.Task.Todo;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String arguments) throws EngulfyError {
        if (arguments.isEmpty()) {
            throw new EngulfyError("I need a description to help you keep track ;-;");
        }

        setTask(new Todo(arguments));
    }
}