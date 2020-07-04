package server.Database;

import java.sql.*;
import java.util.ArrayList;


public class Database {
    private static Connection connection;

    public static void connect(String database, String user, String password, String port) {
        try {
            connection = DriverManager.getConnection(("jdbc:mysql://localhost:" + port + "/" +
                    database + "?serverTimezone=UTC"), user, password);
        } catch (SQLException sqlexc) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sqlexc.printStackTrace();
        }
    }

    public static boolean addClient(String name, String surname, String login, String password, String email) {
        String insertClient = "INSERT INTO storehouse.user (name, surname, login, password, email) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);

            preparedStatementClient.setString(1, name);
            preparedStatementClient.setString(2, surname);
            preparedStatementClient.setString(3, login);
            preparedStatementClient.setString(4, password);
            preparedStatementClient.setString(5, email);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean addAdmin(String login, String password) {
        String insertAdmin = "INSERT INTO storehouse.admin (login, password) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertAdmin);

            preparedStatementClient.setString(1, login);
            preparedStatementClient.setString(2, password);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean login(String login, String password) {
        ResultSet resultSet;

        String select = "SELECT * FROM user WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("login func exception");
            e.printStackTrace();
        }
        return true;
    }

    public static boolean loginAdm(String login, String password) {
        ResultSet resultSet;

        String select = "SELECT * FROM admin WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("login func exception");
            e.printStackTrace();
        }
        return true;
    }

    public static boolean delAdmin(String login) {

        String delete = "DELETE FROM storehouse.admin WHERE login=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getUsers() {
        ResultSet resultSet;
        ArrayList<String> usersList = new ArrayList<>(0);

        String select = "SELECT * FROM storehouse.user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("surname")).append(" ").
                        append(resultSet.getString("login")).append(" ").
                        append(resultSet.getString("email")).append(" ").
                        append(resultSet.getString("password"));
                usersList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public static ArrayList<String> getAdmin() {
        ResultSet resultSet;
        ArrayList<String> admin = new ArrayList<>(0);

        String select = "SELECT * FROM admin";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                admin.add(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static boolean delUser(String login) {

        String delete = "DELETE FROM storehouse.user WHERE login=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getAdminLogin() {
        ResultSet resultSet;
        ArrayList<String> login = new ArrayList<>(0);

        String select = "SELECT login FROM storehouse.admin";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                login.add(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }

    public static ArrayList<String> getUserLogin() {
        ResultSet resultSet;
        ArrayList<String> login = new ArrayList<>(0);

        String select = "SELECT login FROM storehouse.user";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                login.add(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }

    public static ArrayList<String> getProduct() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM storehouse.product";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("vendorcode")).append(" ").
                        append(resultSet.getString("product")).append(" ").
                        append(resultSet.getString("brand"));
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static ArrayList<String> getProducts() {
        ResultSet resultSet;
        ArrayList<String> product = new ArrayList<>(0);

        String select = "SELECT * FROM products";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.add(resultSet.getString("products"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static ArrayList<String> getStorenumber() {
        ResultSet resultSet;
        ArrayList<String> storenumber = new ArrayList<>(0);

        String select = "SELECT storenumber FROM store";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                storenumber.add(resultSet.getString("storenumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storenumber;
    }

    public static ArrayList<String> getStore() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM storehouse.store";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("storenumber")).append(" ").
                        append(resultSet.getString("street")).append(" ").
                        append(resultSet.getString("housenumber"));
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static boolean addStore(String store) {
        String[] infoDetails = store.split(" ", 4);
        String insert = "INSERT INTO storehouse.store (storenumber, street, housenumber)" +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delStore(String storenumber) {

        String delete = "DELETE FROM storehouse.store WHERE storenumber=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, storenumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getStorehouse() {
        ResultSet resultSet;
        ArrayList<String> storeList = new ArrayList<>(0);

        String select = "SELECT * FROM storehouse.storehouse";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("storenumber")).append(" ").
                        append(resultSet.getString("vendorcode")).append(" ").
                        append(resultSet.getString("amount"));
                storeList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeList;
    }

    public static boolean addStorehouse(String store) {
        String[] infoDetails = store.split(" ", 4);
        String insert = "INSERT INTO storehouse.storehouse (storenumber, vendorcode, amount)" +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getBrand() {
        ResultSet resultSet;
        ArrayList<String> brandList = new ArrayList<>(0);

        String select = "SELECT * FROM storehouse.brand";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("brand")).append(" ").
                        append(resultSet.getString("country"));
                brandList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandList;
    }

    public static boolean delProducts(String products) {

        String delete = "DELETE FROM storehouse.products WHERE products=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, products);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean addProducts(String products) {
        String[] infoDetails = products.split(" ", 4);
        String insert = "INSERT INTO storehouse.products (products)" +
                "VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delBrand(String brand) {

        String delete = "DELETE FROM storehouse.brand WHERE brand=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, brand);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getBrandName() {
        ResultSet resultSet;
        ArrayList<String> brandList = new ArrayList<>(0);

        String select = "SELECT brand FROM storehouse.brand";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("brand"));
                brandList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandList;
    }

    public static boolean addBrand(String brand) {
        String[] infoDetails = brand.split(" ", 4);
        String insert = "INSERT INTO storehouse.brand (brand, country)" +
                "VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getVendorcode() {
        ResultSet resultSet;
        ArrayList<String> product = new ArrayList<>(0);

        String select = "SELECT vendorcode FROM product";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.add(resultSet.getString("vendorcode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static boolean addProduct(String product) {
        String[] infoDetails = product.split(" ", 4);
        String insert = "INSERT INTO storehouse.product (vendorcode, product, brand)" +
                "VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delProduct(String vendorcode) {

        String delete = "DELETE FROM storehouse.product WHERE vendorcode=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, vendorcode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean setAddStorehouse(String order) {
        String[] infoDetails = order.split(" ", 4);
        String insert = "UPDATE storehouse.storehouse SET storehouse.amount=? WHERE storenumber=? AND vendorcode=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[3]);
            preparedStatement.setString(2, infoDetails[1]);
            preparedStatement.setString(3, infoDetails[2]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean setDelStorehouse(String order) {
        String[] infoDetails = order.split(" ", 4);
        String insert = "UPDATE storehouse.storehouse SET storehouse.amount=? WHERE storenumber=? AND vendorcode=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[3]);
            preparedStatement.setString(2, infoDetails[1]);
            preparedStatement.setString(3, infoDetails[2]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}