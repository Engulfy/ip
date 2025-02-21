package engulfy.command;

import engulfy.storage.Storage;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * A command to display the list of all tasks.
 * This command shows the tasks in the task list, or a message indicating that there are no tasks.
 */
public class ListCommand implements Command {
    private static final String NO_TASK_MSG = "You seem very free now... Shouldn't you be training with the HASHIRAs?!?!";

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
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        if (!tasks.isEmpty()) {
            return ui.showTaskList(tasks.getAllTasks());
        } else {
            return (NO_TASK_MSG);
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
