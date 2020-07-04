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

public class DelProductsController {

    @FXML
    private Button delButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> productsBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getProducts");
        ArrayList<String> productsList = client.receiveResultList();
        ObservableList<String> products = FXCollections.observableArrayList(productsList);
        productsBox.setItems(products);
    }

    public void del() {
        if (productsBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали продукт!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("delProducts " + productsBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
            productsBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("drand", "");
    }
}
