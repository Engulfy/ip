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
     * @param tasks the current task list (not used in this command)
     * @param ui the user interface to display the goodbye message
     * @param storage the storage (not used in this command)
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Checks if the command results in an exit action.
     *
     * @return true since this is an exit command
     */
    public boolean isExit() {
        return true;
    }
}