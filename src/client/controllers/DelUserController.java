package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class DelUserController {
    @FXML
    private Button delButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> userBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getUserLogin");
        ArrayList<String> userList = client.receiveResultList();
        ObservableList<String> user = FXCollections.observableArrayList(userList);
        userBox.setItems(user);
    }

    public void del() {
        if (userBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали логин!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("delUser " + userBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
            userBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewClients", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewAdmin", "");
    }
}
