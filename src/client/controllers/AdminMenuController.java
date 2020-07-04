package client.controllers;

import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminMenuController {

    @FXML
    private Button backButton;

    @FXML
    private Button adminB;

    @FXML
    private Button userB;

    @FXML
    private Button productsB;

    @FXML
    private Button brandB;

    @FXML
    private Button storeB;

    @FXML
    private Button storehouseB;

    @FXML
    void admin() {
        adminB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewAdmin", "");
    }

    @FXML
    void user() {
        userB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewClients", "");
    }

    @FXML
    void products() {
        productsB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewProduct", "");
    }

    @FXML
    void brand() {
        brandB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
    }

    @FXML
    void store() {
        storeB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStore", "");
    }

    @FXML
    void storehouse() {
        storehouseB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewStorehouse", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("sample", "");
    }
}
