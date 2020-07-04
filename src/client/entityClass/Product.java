package client.entityClass;

public class Product {
    private int id;
    private String product;
    private int vendorcode;
    private int amount;
    private String brand;

    public Product() {
    }

    public Product(int id, String product, int vendorcode, int amount) {
        this.id = id;
        this.product = product;
        this.vendorcode = vendorcode;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getVendorcode() {
        return vendorcode;
    }

    public void setVendorcode(int vendorcode) {
        this.vendorcode = vendorcode;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
