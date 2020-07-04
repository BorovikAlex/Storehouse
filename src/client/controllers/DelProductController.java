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

public class DelProductController {

    @FXML
    private Button delButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> vendorcodeBox;


    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getVendorcode");
        ArrayList<String> productsList = client.receiveResultList();
        ObservableList<String> products = FXCollections.observableArrayList(productsList);
        vendorcodeBox.setItems(products);
    }

    public void del() {
        if (vendorcodeBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали продукт!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("delProduct " + vendorcodeBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
            vendorcodeBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewProduct", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewProduct", "");
    }
}
