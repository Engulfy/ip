package Engulfy.Command;

import Engulfy.Error.EngulfyError;
import Engulfy.Task.Task;
import Engulfy.Task.TaskList;
import Engulfy.Storage.Storage;
import Engulfy.Ui.Ui;

public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(String arguments) throws EngulfyError {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("Please specify a valid task number to unmark");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        Task task = tasks.unmarkTask(index);
        storage.save(tasks);
        ui.showTaskUnmark(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}