package engulfy.command;

import engulfy.error.EngulfyError;
import engulfy.storage.Storage;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * Represents a command that can be executed within the Engulfy application.
 * Each implementing class defines specific behavior for a command, affecting tasks, UI, and/or storage.
 */
public interface Command {
    /**
     * Executes the command's primary logic using the provided dependencies.
     *
     * @param tasks   The list of current tasks. Used to perform task-related operations.
     * @param ui      The user interface component. Used to interact with the user and display messages.
     * @param storage The storage component. Used for persisting task data if required by the command.
     * @return A string message representing the result of the command execution.
     * @throws EngulfyError If an error occurs during execution, such as invalid input or storage issues.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError;

    /**
     * Determines if the command results in application exit.
     *
     * @return true if the command is an exit command, false otherwise
     */
    boolean isExit();
}
