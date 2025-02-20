package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * Command to add a tag to a specific task.
 * This class implements the Command interface and is responsible for adding a tag to a specified task in the task list.
 * The tag is saved to the task and the task list is persisted using the Storage class.
 */
public class TagCommand implements Command {
    private static final String OUT_OF_BOUND_ERROR = "Your task number is a little TOOOO big or small! try again :D";
    private final int taskIndex;
    private final String tag;

    /**
     * Constructs a TagCommand to add a tag to a specific task.
     *
     * @param taskIndex The index of the task to tag.
     * @param tag The tag to add to the task.
     */
    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * Executes the command to add the tag to the task at the specified index.
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

        tasks.get(taskIndex).addTag(tag);
        storage.save(tasks);
        return "Tag added: #" + tag + " to task " + (taskIndex + 1);
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
