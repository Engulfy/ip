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
     * If there are no tasks, a message indicating this is printed to the console.
     *
     * @param tasks the task list containing all tasks
     * @param ui the user interface to display the task list
     * @param storage the storage (not used in this command)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (!tasks.isEmpty()) {
            ui.showTaskList(tasks.getAllTasks());
        } else {
            System.out.println("You seem very free now... Start doing something productive!");
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Checks if the command results in an exit action.
     *
     * @return false since this command does not exit the application
     */
    public boolean isExit() {
        return false;
    }
}