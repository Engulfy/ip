package engulfy.command;

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
     * Runs the Engulfy application.
     * Initializes the task list, displays the welcome message, and processes user commands.
     */
    public void run() {
        ui.showWelcome();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (EngulfyError e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        if (!tasks.isEmpty()) {
            ui.showTaskList(tasks.getAllTasks());
        } else {
            ui.showNoTasks();
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (EngulfyError e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the Engulfy application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Engulfy().run();
    }
}
