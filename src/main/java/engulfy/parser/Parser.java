package engulfy.parser;

import engulfy.command.AddDeadlineCommand;
import engulfy.command.AddEventCommand;
import engulfy.command.AddTodoCommand;
import engulfy.command.Command;
import engulfy.command.DeleteCommand;
import engulfy.command.ExitCommand;
import engulfy.command.ListCommand;
import engulfy.command.MarkCommand;
import engulfy.command.UnmarkCommand;
import engulfy.error.EngulfyError;

/**
 * A parser class to interpret user commands and return corresponding command objects.
 * This class supports commands such as "bye", "list", "delete", "mark", "unmark", "todo", "deadline", and "event".
 */
public class Parser {
    /**
     * Parses a full command string and returns the appropriate Command object.
     *
     * @param fullCommand the full command string, potentially including arguments
     * @return the corresponding Command object based on the parsed command word
     * @throws EngulfyError if the command word is not recognized or if an error occurs during parsing
     */
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
            default:
                throw new EngulfyError("I AM SO SORRY!! But this is not something Engulfy is capable of doing for now ;-;");
        }
    }
}