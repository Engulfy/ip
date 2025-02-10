package engulfy.command;

import engulfy.task.TaskList;
import engulfy.storage.Storage;
import engulfy.ui.Ui;
import engulfy.error.EngulfyError;

/**
 * Represents a command that can be executed within the Engulfy application.
 * Each implementing class defines specific behavior for a command, affecting tasks, UI, and/or storage.
 */
public interface Command {
    /**
     * Executes the command's primary logic using the provided dependencies.
     *
     * @param tasks The task list containing current tasks. Used to perform task-related operations.
     * @param ui The user interface component. Used to interact with the user and display messages.
     * @param storage The storage component. Used for persisting task data if required by the command.
     * @throws EngulfyError If an error occurs during command execution, such as invalid input or storage issues.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws EngulfyError;
    /**
     * Determines if the command results in application exit.
     *
     * @return true if the command is an exit command, false otherwise
     */
    boolean isExit();
}