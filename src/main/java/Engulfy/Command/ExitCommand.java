package Engulfy.Command;

import Engulfy.Storage.Storage;
import Engulfy.Task.TaskList;
import Engulfy.Ui.Ui;

public class ExitCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}