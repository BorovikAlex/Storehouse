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

public class AddProductController {

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> brandBox;

    @FXML
    private ChoiceBox<String> productBox;

    @FXML
    private TextField vendorcode;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getProducts");
        ArrayList<String> productsList = client.receiveResultList();
        client.send("getBrandName");
        ArrayList<String> brandList = client.receiveResultList();
        ObservableList<String> product = FXCollections.observableArrayList(productsList);
        ObservableList<String> brand = FXCollections.observableArrayList(brandList);

        productBox.setItems(product);
        brandBox.setItems(brand);

    }

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (vendorcode.getText().equals("") || productBox.getValue() == null || brandBox.getValue() == null) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("addProduct " + vendorcode.getText() + " " + productBox.getValue() + " " + brandBox.getValue());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Добавлен успешно!");
            brandBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewProduct", "");
        }
        else {
            AlertWindow.display("Такой продукт уже существует!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewProduct", "");
    }
}
