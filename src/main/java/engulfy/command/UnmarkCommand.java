package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * A command to unmark a task as completed.
 * This command unmarks a task at the specified index and updates the storage and UI accordingly.
 */
public class UnmarkCommand implements Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param arguments the string representation of the task index to unmark
     * @throws EngulfyError if the arguments cannot be parsed as a valid integer
     */
    public UnmarkCommand(String arguments) throws EngulfyError {
        assert arguments != null : "Arguments should not be null";
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("This does not seem like a number to Zenitsu :/");
        }
    }

    /**
     * Executes the command by unmarking the task at the specified index, saving the updated task list,
     * and displaying the updated UI.
     *
     * @param tasks the task list containing tasks
     * @param ui the user interface to display the task's updated status
     * @param storage the storage to persist the updated task list
     * @return a String message that is displayed in the UI after unmarking the task
     * @throws EngulfyError if an error occurs during task unmarking or saving process
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        Task task = tasks.unmarkTask(index);
        storage.save(tasks);
        return ui.showTaskUnmarked(task);
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
