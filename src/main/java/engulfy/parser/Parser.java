package Engulfy.Parser;

import Engulfy.Command.AddDeadlineCommand;
import Engulfy.Command.AddEventCommand;
import Engulfy.Command.AddTodoCommand;
import Engulfy.Command.Command;
import Engulfy.Command.DeleteCommand;
import Engulfy.Command.ExitCommand;
import Engulfy.Command.ListCommand;
import Engulfy.Command.MarkCommand;
import Engulfy.Command.UnmarkCommand;
import Engulfy.Error.EngulfyError;

public class Parser {
    public Command parse(String fullCommand) throws EngulfyError {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "delete" -> new DeleteCommand(arguments);
            case "mark" -> new MarkCommand(arguments);
            case "unmark" -> new UnmarkCommand(arguments);
            case "todo" -> new AddTodoCommand(arguments);
            case "deadline" -> new AddDeadlineCommand(arguments);
            case "event" -> new AddEventCommand(arguments);
            default ->
                    throw new EngulfyError("I AM SO SORRY!! But this is not something I am capable of doing for now ;-;");
        };
    }
}