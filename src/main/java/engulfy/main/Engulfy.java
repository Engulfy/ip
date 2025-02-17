package engulfy.main;

import engulfy.command.Command;
import engulfy.error.EngulfyError;
import engulfy.parser.Parser;
import engulfy.storage.Storage;
import engulfy.task.TaskList;
import engulfy.ui.Ui;

/**
 * The main class for the Engulfy application.
 * It handles initialization, user interaction, and execution of commands.
 */
public class Engulfy {
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs an Engulfy instance and initializes the UI, storage, and parser.
     */
    public Engulfy() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

    /**
     * Retrieves the current user interface instance.
     *
     * @return The current instance of the UI.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Retrieves the current storage instance.
     *
     * @return The current instance of the storage.
     */
    public Storage getStorage() {
        return storage;
    }

    /**
     * Processes the user input, parses the corresponding command, and executes it.
     * It uses the UI, task list, and storage to perform actions and return responses.
     *
     * @param input The user's input to be processed.
     * @return A response message from the executed command, which can be an
     *      update on task list, error message, or feedback.
     * @throws EngulfyError If the input cannot be parsed or if an error occurs while executing the command.
     */
    public String getResponse(String input) {
        try {
            TaskList tasks = new TaskList(storage.load());
            Command command = parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (EngulfyError e) {
            return e.getMessage();
        }
    }
}
