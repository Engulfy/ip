package Engulfy.Command;

import Engulfy.Storage.Storage;
import Engulfy.Task.TaskList;
import Engulfy.Ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.isEmpty()) {
            ui.showTaskList(tasks.getAllTasks());
        } else {
            System.out.println("No tasks yet!");
            System.out.println("____________________________________________________________");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}