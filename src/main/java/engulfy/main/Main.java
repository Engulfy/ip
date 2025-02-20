package engulfy.main;

import java.io.IOException;

import engulfy.error.EngulfyError;
import engulfy.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Engulfy application using FXML.
 * This class serves as the entry point for the JavaFX application, setting up the GUI window
 * and linking it with the Engulfy logic.
 */
public class Main extends Application {

    private final Engulfy engulfy = new Engulfy();

    /**
     * Initializes and displays the main window of the Engulfy application.
     * This method loads the FXML file for the main window, sets up the scene, and shows the stage.
     * It also connects the main window controller to the Engulfy logic.
     *
     * @param stage The primary stage for this application, onto which the scene is set.
     */
    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage must not be null";
        assert engulfy != null : "Engulfy instance must be initialized";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Zenitsu ⚡ ");
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setEngulfy(engulfy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EngulfyError e) {
            throw new RuntimeException(e);
        }
    }
}
