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
    private static final String EMPTY_MSG = "Zenitsu cannot find the task you are looking for!";
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "Keyword should not be null";
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword
     * and displaying the matching tasks to the user.
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface to interact with the user and display results.
     * @param storage The storage handler (not used in this command, but needed to comply with the Command interface).
     * @return A message containing the list of tasks that match the keyword, or a message stating no match was found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";
        List<Task> matchingTasks = tasks.getAllTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();

        if (matchingTasks.isEmpty()) {
            return (EMPTY_MSG);
        } else {
            StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                output.append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
            }
            return ("To Find You, I Just Listened For The Sound Of Complete"
                    + " And Utter Betrayal And Followed That!!\n\n" + output).trim();
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
