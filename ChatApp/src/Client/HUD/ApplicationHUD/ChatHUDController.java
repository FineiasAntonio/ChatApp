package Client.HUD.ApplicationHUD;

import Client.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lombok.Getter;

public class ChatHUDController {

    @FXML
    private HBox Hbox;

    @FXML
    private AnchorPane painel;

    @FXML
    @Getter
    private TextArea chatArea;

    @FXML
    private TextField messageField;

    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
    }

    @FXML
    private void enviarMensagem() {
        User.send(messageField.getText());
        messageField.clear();
    }

    public String append(String message) {
        this.chatArea.appendText(message + "\n");
        return "messaga appended sucessfully";
    }

}
