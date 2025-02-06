package Engulfy.Command;

import Engulfy.Task.TaskList;
import Engulfy.Storage.Storage;
import Engulfy.UI.UI;
import Engulfy.Errors.EngulfyErrors;

public interface Command {
    void execute(TaskList tasks, UI ui, Storage storage) throws EngulfyErrors;
    boolean isExit();
}