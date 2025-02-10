package Engulfy.Command;

import Engulfy.Error.EngulfyError;
import Engulfy.Storage.Storage;
import Engulfy.Task.Task;
import Engulfy.Task.TaskList;
import Engulfy.Ui.Ui;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(String arguments) throws EngulfyError {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("Please specify a valid task number to delete");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.showTaskRemoved(removedTask, tasks.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}