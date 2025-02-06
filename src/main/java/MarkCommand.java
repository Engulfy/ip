public class MarkCommand implements Command {
    private int index;

    public MarkCommand(String arguments) throws EngulfyErrors {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyErrors("Please specify a valid task number to mark!");
        }
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws EngulfyErrors {
        Task task = tasks.markTask(index);
        storage.save(tasks);
        ui.showMarkResult(task);
    }

    public boolean isExit() {
        return false;
    }
}