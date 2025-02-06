public class ExitCommand implements Command {
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isExit() {
        return true;
    }
}