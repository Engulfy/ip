package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * A command to mark a task as completed.
 * This command marks a task at the specified index and updates the storage and UI accordingly.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param arguments the string representation of the task index to mark
     * @throws EngulfyError if the arguments cannot be parsed as a valid integer
     */
    public MarkCommand(String arguments) throws EngulfyError {
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("This does not seem like a number to Zenitsu :/");
        }
    }

    /**
     * Executes the command by marking the task at the specified index, saving the updated task list,
     * and displaying the updated UI.
     *
     * @param tasks the task list containing tasks
     * @param ui the user interface to display the task's updated status
     * @param storage the storage to persist the updated task list
     * @return a String message that is displayed in the UI after marking the task
     * @throws EngulfyError if the task index is out of bounds or if an error occurs during task marking or saving
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        Task task = tasks.markTask(index);
        storage.save(tasks);
        return ui.showTaskMarked(task);
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
