package client.controllers;

import client.entityClass.Storehouse;
import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class SetAddStorehouseUserController {

    @FXML
    private TableView<Storehouse> StoreTable;

    @FXML
    private TableColumn<Storehouse, String> storeColumn;

    @FXML
    private TableColumn<Storehouse, String> vendorcodeColumn;

    @FXML
    private TableColumn<Storehouse, Integer> amountColumn;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> vendorcodeBox;

    @FXML
    private ChoiceBox<String> storenumberBox;

    @FXML
    private TextField amount1;

    @FXML
    private TextField amount2;

    @FXML
    private TextField amount3;


    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStorehouseUser", "");
    }

    public void count() {
        amount3.setText(String.valueOf(Integer.parseInt(amount1.getText()) + Integer.parseInt(amount2.getText())));
    }

    public void setCost() {

    }

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
        client.send("getVendorcode");
        ArrayList<String> vendorcodeList = client.receiveResultList();
        client.send("getStoreNumber");
        ArrayList<String> storenumberList = client.receiveResultList();
        ObservableList<String> vendorcode = FXCollections.observableArrayList(vendorcodeList);
        ObservableList<String> storenumber = FXCollections.observableArrayList(storenumberList);

        vendorcodeBox.setItems(vendorcode);
        storenumberBox.setItems(storenumber);

    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getStorehouse");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Storehouse> storehouses = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 4);
            Storehouse storehouse = new Storehouse();
            storehouse.setStorenumber(infoString[0]);
            storehouse.setVendorcode(Integer.valueOf(infoString[1]));
            storehouse.setAmount(Integer.valueOf(infoString[2]));
            storehouses.add(storehouse);
        }
        storeColumn.setCellValueFactory(new PropertyValueFactory<>("storenumber"));
        vendorcodeColumn.setCellValueFactory(new PropertyValueFactory<>("vendorcode"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        StoreTable.setItems(storehouses);
    }

    public void set() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (amount1.getText().equals("") || amount2.getText().equals("") || amount2.getText().equals("") ||
                vendorcodeBox.getValue() == null || storenumberBox.getValue() == null) {
            AlertWindow.display("Вы не заполнили поля!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send("setAddStorehouse " + storenumberBox.getValue() + " " + vendorcodeBox.getValue()
                + " " + amount3.getText());
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Принят успешно!");
            amount3.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStorehouseUser", "");
        } else {
            AlertWindow.display("Ошибка добавления!");
        }
    }
}
