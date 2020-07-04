package client.controllers;

import client.sample.AlertWindow;
import client.sample.Client;
import client.sample.ClientInstance;
import client.sceneLoaders.SceneLoaderInstance;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBrandController {

    @FXML
    private TextField brand;

    @FXML
    private Button addB;

    @FXML
    private Button backButton;

    @FXML
    private TextField country;

    public void add() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        if (brand.getText().equals("") || country.getText().equals("") ) {
            AlertWindow.display("Заполните все поля!");
            return;
        }

        ClientInstance.INSTANCE.getInstance().send(("addBrand " + brand.getText() + " " + country.getText()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Добавлен успешно!");
            country.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
        } else {
            AlertWindow.display("Такой бренд уже существует!");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("brand", "");
    }
}
