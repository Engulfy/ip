package Engulfy.Command;

import Engulfy.Task.TaskList;
import Engulfy.Storage.Storage;
import Engulfy.Ui.Ui;
import Engulfy.Error.EngulfyError;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError;
    boolean isExit();
}