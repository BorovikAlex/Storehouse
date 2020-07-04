package client.entityClass;

public class Brand {
    private int id;
    private String brand;
    private String country;

    public Brand() {
    }

    public Brand(int id, String brand, String country) {
        this.id = id;
        this.brand = brand;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
