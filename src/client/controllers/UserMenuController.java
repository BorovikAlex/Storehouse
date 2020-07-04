package client.controllers;

import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserMenuController {

    @FXML
    private Button backButton;

    @FXML
    private Button storehouseB;

    @FXML
    private Button storeB;

    @FXML
    private Button productsB;

    @FXML
    void store() {
        storeB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStoreUser", "");
    }

    @FXML
    void storehouse() {
        storehouseB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStorehouseUser", "");
    }

    @FXML
    void products() {
        productsB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewProductUser", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }
}
