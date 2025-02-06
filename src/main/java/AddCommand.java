public abstract class AddCommand implements Command {
    protected Task task;

    public void execute(TaskList tasks, UI ui, Storage storage) throws EngulfyErrors {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}