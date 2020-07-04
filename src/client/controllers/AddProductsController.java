package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddProductsController {

    @FXML
    private TextField products;

    @FXML
    private Button addB;

    @FXML
    private Button backButton;

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (products.getText().equals("")) {
            AlertWindow.display("Заполните все поля!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addProducts " + products.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Добавлен успешно!");
            products.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
        }
        else {
            AlertWindow.display("Такой продукт уже существует!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
    }
}
