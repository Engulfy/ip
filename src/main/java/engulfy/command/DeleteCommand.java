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
        try {
            this.index = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            throw new EngulfyError("Please specify a valid task number to delete");
        }
    }


    /**
     * Executes the command, deleting the task at the specified index, saving the updated task list,
     * and displaying the updated UI.
     *
     * @param tasks the task list from which the task is to be removed
     * @param ui the user interface to display the task removal message
     * @param storage the storage to persist the updated task list
     * @throws EngulfyError if an error occurs during the task removal or saving process
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks);
        ui.showTaskRemoved(removedTask, tasks.size());
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
