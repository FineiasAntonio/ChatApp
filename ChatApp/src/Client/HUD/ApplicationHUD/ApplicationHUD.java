package Client.HUD.ApplicationHUD;

import Client.Connection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import javax.swing.*;
import java.io.IOException;

public class ApplicationHUD extends Application {
    private Stage stageApp;

    @Getter
    private static LoginHUDController controllerLoginHUD;
    @Getter
    private static ChatHUDController controllerChatHUD;

    @Override
    public void start(Stage stage) throws IOException {
        this.stageApp = stage;
        showLoginStage();
        new Thread(this::WaitingConnection).start();
    }

    public void showLoginStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginHUD.fxml"));
        Parent root = loader.load();

        controllerLoginHUD = loader.getController();

        stageApp.setOnCloseRequest(e -> System.exit(0));

        stageApp.setResizable(false);
        stageApp.setTitle("Login");
        stageApp.setScene(new Scene(root, 600, 400));
        stageApp.show();
    }

    public void showChatStage(Stage stage)  {
        Platform.runLater(() -> {

            stage.hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ChatHUD.fxml"));
            Parent root = null;

            try {
                 root = loader.load();
            }catch (IOException e) {e.printStackTrace();}

            controllerChatHUD = loader.getController();

            stageApp.setOnCloseRequest(e -> System.exit(0));

            stageApp.setResizable(false);
            stageApp.setTitle("Chat");
            stageApp.setScene(new Scene(root, 600, 400));
            stageApp.show();
            });
    }

    public static void iniciar(){
        launch(ApplicationHUD.class);
    }

    public void WaitingConnection(){
        while(Connection.getClientSocket() == null){}
        showChatStage(stageApp);
    }

}
