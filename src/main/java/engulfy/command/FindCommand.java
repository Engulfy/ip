package engulfy.command;

import java.util.List;

import engulfy.storage.Storage;
import engulfy.task.Task;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * Represents a command to find tasks in the task list that contain a specified keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword
     * and displaying the matching tasks to the user.
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.getAllTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();

        System.out.println("____________________________________________________________");
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
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
