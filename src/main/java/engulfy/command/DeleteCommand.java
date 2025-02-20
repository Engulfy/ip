package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * A command to delete a task from the task list.
 * This command removes a task at a specified index and updates the storage and UI accordingly.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param arguments the string representation of the task index to delete
     * @throws EngulfyError if the arguments cannot be parsed as a valid integer
     */
    public DeleteCommand(String arguments) throws EngulfyError {
        assert arguments != null : "Arguments should not be null";
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("Please specify a valid task number to delete");
        }
    }


    /**
     * Executes the command by deleting the task at the specified index, saving the updated task list,
     * and displaying a confirmation message.
     *
     * @param tasks   The task list from which the task is to be removed.
     * @param ui      The user interface for displaying the task removal message.
     * @param storage The storage handler to persist the updated task list.
     * @return A confirmation message indicating the task has been removed.
     * @throws EngulfyError If the index is out of bounds or an error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks);
        return ui.showTaskRemoved(removedTask, tasks.size());
    }

    /**
     * Checks if the command results in an exit action.
     *
     * @return false since the delete command does not cause the application to exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
