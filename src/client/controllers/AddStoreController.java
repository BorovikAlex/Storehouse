package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddStoreController {

    @FXML
    private TextField storenumber;

    @FXML
    private TextField street;

    @FXML
    private Button backButton;

    @FXML
    private TextField housenumber;

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (storenumber.getText().equals("") || street.getText().equals("") || housenumber.getText().equals("")) {
            AlertWindow.display("Заполните все поля!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addStore " + storenumber.getText() + " " + street.getText() + " " + housenumber.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Добавлен успешно!");
            housenumber.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStore", "");
        }
        else {
            AlertWindow.display("Ошибка добавления!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStore", "");
    }

}
