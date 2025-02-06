public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(String arguments) throws EngulfyErrors {
        if (arguments.isEmpty()) {
            throw new EngulfyErrors("I need a description to help you keep track ;-;");
        }
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new EngulfyErrors("Invalid deadline format! Use: deadline <description> /by <datetime>");
        }
        this.task = new Deadline(parts[0], parts[1]);
    }
}