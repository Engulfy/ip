package Engulfy.Command;

import Engulfy.Error.EngulfyError;
import Engulfy.Storage.Storage;
import Engulfy.Task.Task;
import Engulfy.Task.TaskList;
import Engulfy.Ui.Ui;

public abstract class AddCommand implements Command {
    private Task task;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showTaskAdded(task, tasks.size());
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}