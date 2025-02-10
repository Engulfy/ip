package engulfy.command;

import engulfy.storage.Storage;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

public class ExitCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}