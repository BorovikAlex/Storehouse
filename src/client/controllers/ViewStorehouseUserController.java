package client.controllers;

import client.entityClass.Storehouse;
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

public class ViewStorehouseUserController {
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
    private Button newB;

    @FXML
    private Button addB;

    @FXML
    private Button delB;

    @FXML
    void newproduct() {
        newB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addStorehouse", "");
    }

    @FXML
    void add() {
        addB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("setAddStorehouseUser", "");
    }

    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("setDelStorehouseUser", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("userMenu", "");
    }

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();

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
}
