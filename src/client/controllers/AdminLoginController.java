package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminLoginController {


    @FXML
    private Button backButton;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private TextField adminLogin;

    public void logInAdmin() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("loginAdm " + adminLogin.getText() + " " + adminPassword.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            adminPassword.getScene().getWindow().hide();
            client.send("getadmintdata " + adminLogin.getText() + " " + adminPassword.getText());
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
        } else {
            AlertWindow.display("Неверные логин или пароль!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }
}
