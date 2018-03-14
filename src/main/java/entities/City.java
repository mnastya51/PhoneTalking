package entities;

public class City extends Entity {
    private int id;
    private String cityName;

    public City(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public City(int id) {
        this.id = id;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public String getNameCity() {
        return cityName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameCity(String cityName) {
        this.cityName = cityName;
    }
}
