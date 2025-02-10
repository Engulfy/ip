package Engulfy.Command;

import Engulfy.Error.EngulfyError;
import Engulfy.Parser.Parser;
import Engulfy.Storage.Storage;
import Engulfy.Task.TaskList;
import Engulfy.Ui.Ui;

public class Engulfy {
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    public Engulfy() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
    }

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

    public static void main(String[] args) {
        new Engulfy().run();
    }
}