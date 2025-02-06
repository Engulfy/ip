package Engulfy.Command;

import Engulfy.Errors.EngulfyErrors;
import Engulfy.Task.Task;
import Engulfy.Task.TaskList;
import Engulfy.Storage.Storage;
import Engulfy.UI.UI;

public class UnmarkCommand implements Command {
    private int index;

    public UnmarkCommand(String arguments) throws EngulfyErrors {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyErrors("Please specify a valid task number to unmark");
        }
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws EngulfyErrors {
        Task task = tasks.unmarkTask(index);
        storage.save(tasks);
        ui.showUnmarkResult(task);
    }

    public boolean isExit() {
        return false;
    }
}