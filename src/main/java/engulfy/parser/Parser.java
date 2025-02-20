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

/**
 * A parser class to interpret user commands and return corresponding command objects.
 * This class supports commands such as "bye", "list", "delete", "mark", "unmark", "todo", "deadline", and "event".
 */
public class Parser {
    private static final String INVALID_TASK_ERROR = "I AM SO SORRY!! But this is not something I am capable of doing for now ;-;";

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param fullCommand The full input string entered by the user.
     * @return The appropriate Command object based on the parsed input.
     * @throws EngulfyError If the command is unrecognized.
     */
    public Command parse(String fullCommand) throws EngulfyError {
        assert fullCommand != null : "Command string cannot be null";
        String[] parts = splitCommand(fullCommand);
        return createCommand(parts[0], parts.length > 1 ? parts[1] : "");
    }

    /**
     * Splits the given command string into command word and arguments.
     *
     * @param fullCommand The full input string entered by the user.
     * @return An array where the first element is the command word and the second element (if present) is the arguments.
     */
    private String[] splitCommand(String fullCommand) {
        return fullCommand.split(" ", 2);
    }

    /**
     * Creates and returns the appropriate Command object based on the command word and arguments.
     *
     * @param commandWord The command keyword extracted from user input.
     * @param arguments The arguments associated with the command (if any).
     * @return A Command object corresponding to the given command word.
     * @throws EngulfyError If the command word is unrecognized.
     */
    private Command createCommand(String commandWord, String arguments) throws EngulfyError {
        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "delete" -> new DeleteCommand(arguments);
            case "mark" -> new MarkCommand(arguments);
            case "unmark" -> new UnmarkCommand(arguments);
            case "todo" -> new AddTodoCommand(arguments);
            case "deadline" -> new AddDeadlineCommand(arguments);
            case "event" -> new AddEventCommand(arguments);
            case "find" -> new FindCommand(arguments);
            default -> throw new EngulfyError(INVALID_TASK_ERROR);
        };
    }
}
