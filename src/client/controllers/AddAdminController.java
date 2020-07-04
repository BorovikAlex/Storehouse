package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddAdminController {


    @FXML
    private TextField adminLogin;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private Button backButton;

    public void addAdmin() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (adminLogin.getText().equals("") || adminPassword.getText().equals("")) {
            AlertWindow.display("Заполните все поля!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addAdmin " + adminLogin.getText() + " " + adminPassword.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Добавлен успешно!");
            adminPassword.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewAdmin", "");
        }
        else {
            AlertWindow.display("Ошибка добавления!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewAdmin", "");
    }
}