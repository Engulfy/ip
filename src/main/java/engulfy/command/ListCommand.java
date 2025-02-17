package engulfy.command;

import engulfy.storage.Storage;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * A command to display the list of all tasks.
 * This command shows the tasks in the task list, or a message indicating that there are no tasks.
 */
public class ListCommand implements Command {
    /**
     * Executes the list command by displaying all tasks in the task list.
     * If there are no tasks, a message indicating this is displayed.
     *
     * @param tasks the task list containing all tasks.
     * @param ui the user interface to display the task list.
     * @param storage the storage (not used in this command, but needed to comply with the Command interface).
     * @return A message with the list of tasks, or a message stating there are no tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.isEmpty()) {
            return ui.showTaskList(tasks.getAllTasks());
        } else {
            return ("You seem very free now... Start doing something productive!");
        }
    }

    /**
     * Checks if the command results in an exit action.
     *
     * @return false since this command does not exit the application
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
