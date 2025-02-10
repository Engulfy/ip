package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(String arguments) throws EngulfyError {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("Please specify a valid task number to delete");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.showTaskRemoved(removedTask, tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}