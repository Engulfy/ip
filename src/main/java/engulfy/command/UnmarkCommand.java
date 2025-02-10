package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.storage.Storage;
import engulfy.ui.Ui;

public class UnmarkCommand implements Command {
    private int index;

    public UnmarkCommand(String arguments) throws EngulfyError {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("Please specify a valid task number to unmark");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        Task task = tasks.unmarkTask(index);
        storage.save(tasks);
        ui.showTaskUnmark(task);
    }

    public boolean isExit() {
        return false;
    }
}