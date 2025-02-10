package engulfy.command;

import engulfy.task.TaskList;
import engulfy.storage.Storage;
import engulfy.ui.Ui;
import engulfy.error.EngulfyError;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError;
    boolean isExit();
}