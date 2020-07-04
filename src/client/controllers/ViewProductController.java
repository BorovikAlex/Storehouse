package client.controllers;

import client.entityClass.Product;
import client.entityClass.Products;
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
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ViewProductController {

    @FXML
    private AnchorPane storesTable;

    @FXML
    private TableView<Product> StoreTable;

    @FXML
    private TableColumn<Products, String> vendorcodeColumn;

    @FXML
    private TableColumn<Products, String> productColumn;

    @FXML
    private TableColumn<Products, String> brandColumn;

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
        ClientInstance.INSTANCE.getInstance().send("getProduct");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Product> stores = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 4);
            Product store = new Product();
            store.setVendorcode(Integer.valueOf(infoString[0]));
            store.setProduct(infoString[1]);
            store.setBrand(infoString[2]);
            stores.add(store);
        }
        vendorcodeColumn.setCellValueFactory(new PropertyValueFactory<>("vendorcode"));
        productColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
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
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addProduct", "");
    }

    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delProduct", "");
    }
}
