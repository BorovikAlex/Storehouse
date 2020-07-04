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

public class DellAdminController {

    @FXML
    private Button delButton;

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> adminBox;

    @FXML
    void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        client.send("getAdminLogin");
        ArrayList<String> adminList = client.receiveResultList();
        ObservableList<String> admin = FXCollections.observableArrayList(adminList);
        adminBox.setItems(admin);
    }

    public void del() {
        if (adminBox.getValue().equals("")) {
            AlertWindow.display("Вы не выбрали логин!");
            return;
        }
        if (adminBox.getValue().equals("admin")) {
            AlertWindow.display("Этого администратора нельзя удалить!");
            return;
        }
        ClientInstance.INSTANCE.getInstance().send(("delAdmin " + adminBox.getValue()));
        if (ClientInstance.INSTANCE.getInstance().receiveResultBool()) {
            AlertWindow.display("Данные удалены успешно!");
            adminBox.getScene().getWindow().hide();
            SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewAdmin", "");
        }
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("viewAdmin", "");
    }
}
