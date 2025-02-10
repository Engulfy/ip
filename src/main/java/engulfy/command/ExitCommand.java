package Engulfy.Command;

import Engulfy.Storage.Storage;
import Engulfy.Task.TaskList;
import Engulfy.Ui.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}