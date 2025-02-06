package Engulfy.Command;

import Engulfy.Error.EngulfyError;
import Engulfy.Task.Task;
import Engulfy.Task.TaskList;
import Engulfy.Storage.Storage;
import Engulfy.Ui.Ui;

public class MarkCommand implements Command {
    private int index;

    public MarkCommand(String arguments) throws EngulfyError {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("Please specify a valid task number to mark!");
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        Task task = tasks.markTask(index);
        storage.save(tasks);
        ui.showTaskMark(task);
    }

    public boolean isExit() {
        return false;
    }
}