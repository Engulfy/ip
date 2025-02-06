public class Parser {
    public Command parse(String fullCommand) throws EngulfyErrors {
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
                throw new EngulfyErrors("I AM SO SORRY!! But this is not something I am capable of doing for now ;-;");
        }
    }
}