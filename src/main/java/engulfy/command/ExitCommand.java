package engulfy.command;

import engulfy.storage.Storage;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * A command to exit the application.
 * This command displays a goodbye message and indicates that the application should terminate.
 */
public class ExitCommand implements Command {

    /**
     * Executes the exit command by displaying a goodbye message via the UI.
     *
     * @param tasks   The current task list (not used in this command).
     * @param ui      The user interface to display the goodbye message.
     * @param storage The storage handler (not used in this command).
     * @return A goodbye message indicating the application is closing.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Checks if the command results in an exit action.
     *
     * @return true since this is an exit command
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
