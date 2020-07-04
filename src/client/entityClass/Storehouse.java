package client.entityClass;

public class Storehouse {
    private int id;
    private String storenumber;
    private int vendorcode;
    private int amount;

    public Storehouse() {
    }

    public Storehouse(int id, String storenumber, int vendorcode, int amount) {
        this.id = id;
        this.storenumber = storenumber;
        this.vendorcode = vendorcode;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorenumber() {
        return storenumber;
    }

    public void setStorenumber(String storenumber) {
        this.storenumber = storenumber;
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
}
