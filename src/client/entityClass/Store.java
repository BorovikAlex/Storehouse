package client.entityClass;

public class Store {

    private int id;
    private String storenumber;
    private String street;
    private int housenumber;

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(int housenumber) {
        this.housenumber = housenumber;
    }
}
