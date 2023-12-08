package Client.HUD.ApplicationHUD;

import Client.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.Getter;


public class LoginHUDController {

    @Getter
    private static boolean validatedConn = false;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton;

    @FXML
    public void sendData() {
        try {

            if(usernameField.getText() == null) {throw new NullPointerException();}
            new User(usernameField.getText());

        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
