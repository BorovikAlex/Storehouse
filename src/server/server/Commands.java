package server.server;

import server.Database.Database;

public class Commands {

    public static Object split(String command) {
        String[] commandNumber = command.split(" ", 2);
        String[] commands;
        Object result = true;
        switch (commandNumber[0]) {
            case "addAdmin":
                commands = command.split(" ", 5);
                result = Database.addAdmin(commands[1], commands[2]);
                break;
            case "addclient":
                commands = command.split(" ", 6);
                result = Database.addClient(commands[1], commands[2], commands[3], commands[4], commands[5]);
                break;
            case "login":
                commands = command.split(" ", 3);
                result = Database.login(commands[1], commands[2]);
                break;
            case "loginAdm":
                commands = command.split(" ", 3);
                result = Database.loginAdm(commands[1], commands[2]);
                break;
            case "delAdmin":
                commands = command.split(" ", 2);
                result = Database.delAdmin(commands[1]);
                break;
            case "delUser":
                commands = command.split(" ", 2);
                result = Database.delUser(commands[1]);
                break;
            case "getUsers":
                result = Database.getUsers();
                break;
            case "getAdmin":
                result = Database.getAdmin();
                break;
            case "getAdminLogin":
                result = Database.getAdminLogin();
                break;
            case "getUserLogin":
                result = Database.getUserLogin();
                break;
            case "getProduct":
                result = Database.getProduct();
                break;
            case "getProducts":
                result = Database.getProducts();
                break;
            case "getStore":
                result = Database.getStore();
                break;
            case "getStoreNumber":
                result = Database.getStorenumber();
                break;
            case "addStore":
                result = Database.addStore(command);
                break;
            case "delStore":
                commands = command.split(" ", 2);
                result = Database.delStore(commands[1]);
                break;
            case "getStorehouse":
                result = Database.getStorehouse();
                break;
            case "addStorehouse":
                result = Database.addStorehouse(command);
                break;
            case "getBrand":
                result = Database.getBrand();
                break;
            case "delProducts":
                commands = command.split(" ", 2);
                result = Database.delProducts(commands[1]);
                break;
            case "addProducts":
                result = Database.addProducts(command);
                break;
            case "delBrand":
                commands = command.split(" ", 2);
                result = Database.delBrand(commands[1]);
                break;
            case "getBrandName":
                result = Database.getBrandName();
                break;
            case "addBrand":
                result = Database.addBrand(command);
                break;
            case "getVendorcode":
                result = Database.getVendorcode();
                break;
            case "addProduct":
                result = Database.addProduct(command);
                break;
            case "delProduct":
                commands = command.split(" ", 2);
                result = Database.delProduct(commands[1]);
                break;
            case "setAddStorehouse":
                result = Database.setAddStorehouse(command);
                break;
            case "setDelStorehouse":
                result = Database.setDelStorehouse(command);
                break;
        }
        return result;
    }
}
