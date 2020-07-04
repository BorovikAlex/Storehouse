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

public class AddStorehouseUserController {

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> vendorcodeBox;

    @FXML
    private ChoiceBox<String> storenumberBox;

    @FXML
    private TextField amount;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getVendorcode");
        ArrayList<String> vendorcodeList = client.receiveResultList();
        client.send("getStoreNumber");
        ArrayList<String> storenumberList = client.receiveResultList();
        ObservableList<String> vendorcode = FXCollections.observableArrayList(vendorcodeList);
        ObservableList<String> storenumber = FXCollections.observableArrayList(storenumberList);

        vendorcodeBox.setItems(vendorcode);
        storenumberBox.setItems(storenumber);

    }

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (amount.getText().equals("") || vendorcodeBox.getValue() == null || storenumberBox.getValue() == null) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("addStorehouse " + storenumberBox.getValue() + " " + vendorcodeBox.getValue()
                + " " + amount.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Добавлен успешно!");
            amount.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStorehouseUser", "");
        }
        else {
            AlertWindow.display("Ошибка добавления!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStorehouseUser", "");
    }
}
