public class Engulfy {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;
    private final Parser parser;

    public Engulfy() {
        ui = new UI();
        storage = new Storage();
        parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        try {
            tasks = new TaskList(storage.load());
        } catch (EngulfyErrors e) {
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
            } catch (EngulfyErrors e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Engulfy().run();
    }
}