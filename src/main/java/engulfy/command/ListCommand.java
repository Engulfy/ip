package engulfy.command;

import engulfy.storage.Storage;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

public class ListCommand implements Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.isEmpty()) {
            ui.showTaskList(tasks.getAllTasks());
        } else {
            System.out.println("No tasks yet!");
            System.out.println("____________________________________________________________");
        }
    }

    public boolean isExit() {
        return false;
    }
}