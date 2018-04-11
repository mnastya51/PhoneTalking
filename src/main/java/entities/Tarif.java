package entities;

public class Tarif extends Entity{
    private String startPeriod;
    private String finishPeriod;
    private double cost;
    private String cityName = null;

    public Tarif(String startPeriod, String finishPeriod, double cost, String cityName) {
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

    public String getStartPeriod() {
        return startPeriod;
    }

    public String getFinishPeriod() {
        return finishPeriod;
    }

    public double getCost() {
        return cost;
    }

    public String getNameCity() {
        return cityName;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public void setFinishPeriod(String finishPeriod) {
        this.finishPeriod = finishPeriod;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
