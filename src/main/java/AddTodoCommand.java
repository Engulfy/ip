public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String arguments) throws EngulfyErrors {
        if (arguments.isEmpty()) {
            throw new EngulfyErrors("I need a description to help you keep track ;-;");
        }
        this.task = new Todo(arguments);
    }
}