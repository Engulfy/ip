package engulfy.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The main entry point of the application.
     * This method serves as a workaround to avoid classpath issues by launching the JavaFX application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        assert args != null : "Command line arguments must not be null";
        Application.launch(Main.class, args);
    }
}
