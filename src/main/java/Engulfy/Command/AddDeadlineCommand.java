package Engulfy.Command;

import Engulfy.Error.EngulfyError;
import Engulfy.Task.Deadline;

public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(String arguments) throws EngulfyError {
        if (arguments.isEmpty()) {
            throw new EngulfyError("I need a description to help you keep track ;-;");
        }

        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new EngulfyError("Invalid deadline format! Use: deadline <description> /by <datetime>");
        }

        setTask(new Deadline(parts[0], parts[1]));
    }
}