package client.controllers;

import client.entityClass.Users;
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

public class ViewClientsController {

    @FXML
    private TableView<Users> clientsTable;
    @FXML
    private TableColumn<Users, String> nameColumn;
    @FXML
    private TableColumn<Users, String> surnameColumn;
    @FXML
    private TableColumn<Users, String> loginColumn;

    @FXML
    private TableColumn<Users, String> emailColumn;

    @FXML
    private TableColumn<Users, String> passwordColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button delB;

    public void initialize() {
        Client client = ClientInstance.INSTANCE.getInstance();
        client.connect();
        fillTableView();
    }

    public void fillTableView() {
        ClientInstance.INSTANCE.getInstance().send("getUsers");
        ArrayList<String> list = ClientInstance.INSTANCE.getInstance().receiveResultList();
        ObservableList<Users> information = FXCollections.observableArrayList();
        String[] infoString;
        for (int i = 0; i < list.size(); i++) {
            infoString = list.get(i).split(" ", 6);
            Users info = new Users();
            info.setName(infoString[0]);
            info.setSurname(infoString[1]);
            info.setLogin(infoString[2]);
            info.setEmail(infoString[3]);
            info.setPassword(infoString[4]);
            information.add(info);
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        clientsTable.setItems(information);
    }

    @FXML
    void del() {
        delB.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("delUser", "");
    }

    @FXML
    void back() {
        backButton.getScene().getWindow().hide();
        SceneLoaderInstance.INSTANCE.getInstance().loadScene("adminMenu", "");
    }
}

