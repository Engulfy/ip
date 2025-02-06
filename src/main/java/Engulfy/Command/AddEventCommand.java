package Engulfy.Command;

import Engulfy.Errors.EngulfyErrors;
import Engulfy.Task.Event;

public class AddEventCommand extends AddCommand {
    public AddEventCommand(String arguments) throws EngulfyErrors {
        if (arguments.isEmpty()) {
            throw new EngulfyErrors("I need a description to help you keep track ;-;");
        }
        String[] parts = arguments.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new EngulfyErrors("Invalid event format! Use: event <description> /from <datetime> /to <datetime>");
        }
        String[] timeParts = parts[1].split(" /to ", 2);
        this.task = new Event(parts[0], timeParts[0], timeParts[1]);
    }
}