package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ClientLoginController {


    @FXML
    private Button backButton;

    @FXML
    private PasswordField userPassword;

    @FXML
    private TextField userLogin;

    @FXML
    private Button clientRegistrationButton;

    public void logIn() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("login " + userLogin.getText() + " " + userPassword.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {

            userPassword.getScene().getWindow().hide();
            client.send("getclientdata " + userLogin.getText() + " " + userPassword.getText());

            SceneLoaderInstance.INSTANCE.getInstance().loadScene("userMenu", "");
        } else {
            AlertWindow.display("Неверный логин или пароль!");
        }
    }


    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }

    @FXML
    void clientRegistrationButt() {
        clientRegistrationButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientRegistration", "");
    }

}



