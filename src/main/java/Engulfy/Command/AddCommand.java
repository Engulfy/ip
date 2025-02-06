package Engulfy.Command;

import Engulfy.Errors.EngulfyErrors;
import Engulfy.Storage.Storage;
import Engulfy.Task.Task;
import Engulfy.Task.TaskList;
import Engulfy.UI.UI;

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