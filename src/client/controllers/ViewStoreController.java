package client.controllers;


import client.entityClass.Store;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ViewStoreController {

    @FXML
    private TableView<Store> StoreTable;

    @FXML
    private TableColumn<Store, String> storeColumn;

    @FXML
    private TableColumn<Store, String> streetColumn;

    @FXML
    private TableColumn<Store, Integer> housenumberColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button addB;

    @FXML
    private Button delB;


    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getStore");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Store> stores = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 4);
            Store store = new Store();
            store.setStorenumber(infoString[0]);
            store.setStreet(infoString[1]);
            store.setHousenumber(Integer.valueOf(infoString[2]));
            stores.add(store);
        }
        storeColumn.setCellValueFactory(new PropertyValueFactory<>("storenumber"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        housenumberColumn.setCellValueFactory(new PropertyValueFactory<>("housenumber"));
        StoreTable.setItems(stores);
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }

    @FXML
    void add() {
        addB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addStore", "");
    }

    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delStore", "");
    }
}
