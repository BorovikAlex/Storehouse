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
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class DelStoreController {

    @FXML
    private Button delButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> storeBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getStoreNumber");
        ArrayList<String> storeList = client.receiveResultList();
        ObservableList<String> store = FXCollections.observableArrayList(storeList);
        storeBox.setItems(store);
    }

    public void del() {
        if (storeBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали номер склада!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("delStore " + storeBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
            storeBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStore", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStore", "");
    }

}
