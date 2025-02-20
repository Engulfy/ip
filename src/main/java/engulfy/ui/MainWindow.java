package engulfy.ui;

import engulfy.error.EngulfyError;
import engulfy.main.Engulfy;
import engulfy.task.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the Engulfy application.
 * This class handles user input and displays dialog boxes for communication between the user and Engulfy.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Engulfy engulfy;

    private Image userImage = new Image(this.getClass().getResourceAsStream(("/images/DaUser.png")));
    private Image engulfyImage = new Image(this.getClass().getResourceAsStream(("/images/DaEngulfy.png")));

    /**
     * Initializes the main window by binding the scroll pane to the height of the dialog container.
     * This ensures that the scroll pane automatically scrolls to the bottom when new messages are added.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane should not be null.";
        assert dialogContainer != null : "Dialog container should not be null.";
        assert userInput != null : "User input field should not be null.";
        assert sendButton != null : "Send button should not be null.";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Engulfy instance into the main window and displays the welcome message.
     *
     * @param e the Engulfy instance to be injected
     * @throws EngulfyError if there is an error in setting up the Engulfy instance
     */
    public void setEngulfy(Engulfy e) throws EngulfyError {
        assert e != null : "Engulfy instance should not be null.";
        engulfy = e;
        showWelcomeMessage();
    }

    /**
     * Displays a welcome message in the dialog container.
     * If there is an error loading the tasks, an error message is displayed instead.
     */
    private void showWelcomeMessage() {
        TaskList tasks;
        try {
            tasks = new TaskList(engulfy.getStorage().load());
        } catch (Exception e) {
            tasks = new TaskList();
            String errorMessage = engulfy.getUi().showLoadingError();
            dialogContainer.getChildren().add(DialogBox.getZenitsuDialog(errorMessage, engulfyImage));
        }

        String welcomeMessage = engulfy.getUi().showWelcome(tasks);
        assert welcomeMessage != null : "Welcome message should not be null.";
        dialogContainer.getChildren().add(DialogBox.getZenitsuDialog(welcomeMessage, engulfyImage));
    }

    /**
     * Handles the user's input when the send button is clicked.
     * It creates two dialog boxes: one for the user's input and one for Engulfy's response, and appends them
     * to the dialog container. After processing the input, the text field is cleared.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null.";
        String response;
        try {
            response = engulfy.getResponse(input);
        } catch (Exception e) {
            response = engulfy.getUi().showError("Zenitsu cannot handle this!");
        }

        assert response != null : "Engulfy's response should not be null.";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getZenitsuDialog(response, engulfyImage)
        );
        userInput.clear();
    }
}
