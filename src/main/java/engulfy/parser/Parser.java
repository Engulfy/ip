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
import engulfy.command.TagCommand;
import engulfy.command.UnmarkCommand;
import engulfy.command.UntagCommand;
import engulfy.error.EngulfyError;

/**
 * A parser class to interpret user commands and return corresponding command objects.
 * This class supports commands such as "bye", "list", "delete", "mark", "unmark", "todo", "deadline", and "event".
 */
public class Parser {
    private static final String INVALID_TASK_ERROR = "I AM SO SORRY!! "
            + "But this is not something I am capable of doing for now ;-;";

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
     * @return An array where the first element is the command word and the second element is the arguments.
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
        case "tag" -> createTagCommand(arguments);
        case "untag" -> createUntagCommand(arguments);
        default -> throw new EngulfyError(INVALID_TASK_ERROR);
        };
    }

    /**
     * Creates and returns the TagCommand object.
     * The format for tagging would be something like "tag 1 #fun" where 1 is the task ID and #fun is the tag.
     *
     * @param arguments The arguments for the "tag" command (task ID and tag).
     * @return A TagCommand object.
     * @throws EngulfyError If the arguments are invalid.
     */
    private Command createTagCommand(String arguments) throws EngulfyError {
        String[] parts = arguments.split(" ", 2);

        if (parts.length != 2) {
            throw new EngulfyError("Invalid tag command. Please use the format: tag <taskID> <tag>");
        }

        String taskId = parts[0];
        String tag = parts[1];

        return new TagCommand(Integer.parseInt(taskId) - 1, tag);
    }

    /**
     * Creates and returns the UntagCommand object.
     * The format for untagging would be something like "untag 1 #fun" where 1 is the task ID
     * and #fun is the tag to remove.
     *
     * @param arguments The arguments for the "untag" command (task ID and tag).
     * @return An UntagCommand object that will remove the specified tag from the task.
     * @throws EngulfyError If the arguments are invalid or not in the expected format
     */
    private Command createUntagCommand(String arguments) throws EngulfyError {
        String[] parts = arguments.split(" ", 2);

        if (parts.length != 2) {
            throw new EngulfyError("Invalid untag command. Please use the format: untag <taskID> <tag>");
        }

        String taskId = parts[0];
        String tag = parts[1];

        return new UntagCommand(Integer.parseInt(taskId) - 1, tag);
    }
}
