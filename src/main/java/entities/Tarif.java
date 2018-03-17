package entities;

public class Tarif extends Entity{
    private int cityId;
    private String startPeriod;
    private String finishPeriod;
    private Double cost;
    private String cityName = null;

    public Tarif(int cityId, String startPeriod, String finishPeriod, Double cost, String cityName) {
        this.cityId = cityId;
        this.startPeriod = startPeriod;
        this.finishPeriod = finishPeriod;
        this.cost = cost;
        this.cityName = cityName;
    }

    public Tarif(String startPeriod, String finishPeriod, Double cost, String cityName) {
        this.startPeriod = startPeriod;
        this.finishPeriod = finishPeriod;
        this.cost = cost;
        this.cityName = cityName;
    }

    public Tarif(String cityName, String startPeriod, String finishPeriod) {
        this.startPeriod = startPeriod;
        this.finishPeriod = finishPeriod;
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public String getFinishPeriod() {
        return finishPeriod;
    }

    public Double getCost() {
        return cost;
    }

    public String getNameCity() {
        return cityName;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public void setFinishPeriod(String finishPeriod) {
        this.finishPeriod = finishPeriod;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
