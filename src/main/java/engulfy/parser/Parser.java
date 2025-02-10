package engulfy.parser;

import engulfy.command.AddDeadlineCommand;
import engulfy.command.AddEventCommand;
import engulfy.command.AddTodoCommand;
import engulfy.command.Command;
import engulfy.command.DeleteCommand;
import engulfy.command.ExitCommand;
import engulfy.command.FindCommand;
import engulfy.command.ListCommand;
import engulfy.command.MarkCommand;
import engulfy.command.UnmarkCommand;
import engulfy.error.EngulfyError;

public class Parser {
    public Command parse(String fullCommand) throws EngulfyError {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "delete":
                return new DeleteCommand(arguments);
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "find":
                return new FindCommand(arguments);
            default:
                throw new EngulfyError("I AM SO SORRY!! But this is not something I am capable of doing for now ;-;");
        }
    }
}