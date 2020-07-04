package client.controllers;

import client.entityClass.Brand;
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

import java.util.ArrayList;

public class ViewBrandController {

    @FXML
    private TableView<Brand> brandTable;

    @FXML
    private TableColumn<Brand, String> brandColumn;

    @FXML
    private TableColumn<Brand, String> countryColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Products> productTable;

    @FXML
    private TableColumn<Products, String> productColumn;

    @FXML
    private Button addB;

    @FXML
    private Button delB;

    @FXML
    private Button addB1;

    @FXML
    private Button delB1;

    @FXML
    void initialize() {
        fillTableView();
        fillTableViewBrand();
    }

    public void fillTableView() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getProducts");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Products> products = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 2);
            Products product = new Products();
            product.setProducts(infoString[0]);
            products.add(product);
        }
        productColumn.setCellValueFactory(new PropertyValueFactory<>("products"));
        productTable.setItems(products);
    }

    public void fillTableViewBrand() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        ClientInstance.INSTANCE.getInstance().send("getBrand");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Brand> brands = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 3);
            Brand brand = new Brand();
            brand.setBrand(infoString[0]);
            brand.setCountry(infoString[1]);
            brands.add(brand);
        }
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        brandTable.setItems(brands);
    }
    @FXML
    void add() {
        addB1.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addProducts", "");
    }
    @FXML
    void add1() {
        addB1.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("addBrand", "");
    }
    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delProducts", "");
    }
    @FXML
    void del1() {
        delB1.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delBrand", "");
    }
    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}
