package engulfy.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * This class is used for displaying messages in a conversation, with the speaker's image
 * and text displayed in a GUI using JavaFX.
 */
public class DialogBox extends HBox {
    private static final String FXML_PATH = "/view/DialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox instance with the specified text and image.
     * The dialog box is initialized by loading the corresponding FXML layout.
     *
     * @param text the message text to display in the dialog box
     * @param img the image to display in the ImageView
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(FXML_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert dialog != null : "Dialog label should not be null after loading FXML.";
        assert displayPicture != null : "Display picture should not be null after loading FXML.";

        dialog.setWrapText(true);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * This is used to reverse the default layout of the dialog box when displaying a response from Duke.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        assert tmp.size() == 2 : "DialogBox should have exactly two children (Label and ImageView).";
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user with the specified text and image.
     *
     * @param text the message text to display in the dialog box
     * @param img the image to display in the ImageView
     * @return a DialogBox instance for the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null : "User dialog text should not be null.";
        assert img != null : "User dialog image should not be null.";
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Engulfy with the specified text and image.
     * The dialog box is flipped so that the image is on the left and text on the right.
     *
     * @param text the message text to display in the dialog box
     * @param img the image to display in the ImageView
     * @return a DialogBox instance for Engulfy
     */
    public static DialogBox getZenitsuDialog(String text, Image img) {
        assert text != null : "Zenitsu dialog text should not be null.";
        assert img != null : "Zenitsu dialog image should not be null.";
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
