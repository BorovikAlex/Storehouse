package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ClientRegistrationController {

    @FXML
    private TextField userName;

    @FXML
    private TextField userSurname;

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Button backButton;

    @FXML
    private TextField userEmail;

    public void registration() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (userName.getText().equals("") || userSurname.getText().equals("") || userLogin.getText().equals("")
                || userPassword.getText().equals("") || userEmail.getText().equals("")) {
            AlertWindow.display("Введите все данные!");
            return;
        }
        if (userLogin.getText().contains("@") == false) {
            AlertWindow.display("Введите правильную почту!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addclient " + userName.getText() + " " + userSurname.getText() + " " +
                userLogin.getText() + " " + userPassword.getText() + " " + userEmail.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Клиент добавлен успешно!");
            userName.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("clientLogin", "");
        } else {
            AlertWindow.display("Такой логин уже существует!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }
}