package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * Command to remove a tag from a specific task.
 */
public class UntagCommand implements Command {
    private static final String OUT_OF_BOUND_ERROR = "Your task number is a little TOOOO big or small! try again :D";
    private final int taskIndex;
    private final String tag;

    /**
     * Constructs an UntagCommand to remove a tag from a specific task.
     * If the provided tag starts with '#', the prefix is removed before storing.
     *
     * @param taskIndex The index of the task to untag.
     * @param tag the tag to be removed, with an optional '#' prefix that will be removed
     */
    public UntagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag.startsWith("#") ? tag.substring(1).trim() : tag.trim();
    }

    /**
     * Executes the command to remove the tag from the task at the specified index.
     * If the task index is invalid, an error is thrown.
     * The task is updated and saved in the storage.
     *
     * @param tasks The list of tasks to retrieve and update the task.
     * @param ui The user interface to display responses (currently unused in this command).
     * @param storage The storage system to save the updated task list.
     * @return A string message indicating the success of the operation, including the tag and task index.
     * @throws EngulfyError If the task index is invalid or other issues occur.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new EngulfyError(OUT_OF_BOUND_ERROR);
        }

        Task task = tasks.get(taskIndex);

        if (!task.getTag().contains(tag)) {
            return "Tag #" + tag + " not found on task " + (taskIndex + 1);
        }

        task.removeTag(tag);
        storage.save(tasks);
        return "Tag removed: #" + tag + " from task " + (taskIndex + 1);
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return false since this command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
