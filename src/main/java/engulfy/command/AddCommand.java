package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * Represents an abstract command for adding tasks.
 * This class implements the Command interface and provides
 * the execution logic for adding a task to the task list.
 */
public abstract class AddCommand implements Command {
    private Task task;

    /**
     * Executes the add command by adding a task to the task list,
     * saving the updated list to storage, and returning a confirmation message.
     *
     * @param tasks   The task list to which the task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler for saving task data.
     * @return A message confirming that the task has been added.
     * @throws EngulfyError If an error occurs while saving the task.
     * @throws NullPointerException If the task has not been initialized.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        tasks.addTask(task);
        storage.save(tasks);
        return ui.showTaskAdded(task, tasks.size());
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Indicates whether this command causes the application to exit.
     *
     * @return false, as adding a task does not terminate the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
