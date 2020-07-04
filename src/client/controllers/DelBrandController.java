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

public class DelBrandController {

    @FXML
    private Button delButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> brandBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getBrandName");
        ArrayList<String> brandList = client.receiveResultList();
        ObservableList<String> brand = FXCollections.observableArrayList(brandList);
        brandBox.setItems(brand);
    }

    public void del() {
        if (brandBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали бренд!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("delBrand " + brandBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
            brandBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
    }
}
