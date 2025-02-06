public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(String arguments) throws EngulfyErrors {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyErrors("Please specify a valid task number to delete");
        }
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws EngulfyErrors {
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.showTaskRemoved(removedTask, tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}