package entities;

public class Talking extends Entity{
    private int talkId;
    private double talkCost;
    private int minCount;
    private String talkDate;
    private String talkTime;
    private String phoneAbonent = null;
    private String cityName = null;


    public Talking(int talkId, String phoneAbonent, String cityName, int minCount, String talkDate, String talkTime, double talkCost) {
        this.talkId = talkId;
        this.phoneAbonent = phoneAbonent;
        this.talkCost = talkCost;
        this.cityName = cityName;
        this.minCount = minCount;
        this.talkDate = talkDate;
        this.talkTime = talkTime;
    }

    public Talking(String phoneAbonent, String cityName, int minCount, String talkDate, String talkTime, double talkCost) {
        this.phoneAbonent = phoneAbonent;
        this.talkCost = talkCost;
        this.cityName = cityName;
        this.minCount = minCount;
        this.talkDate = talkDate;
        this.talkTime = talkTime;
    }

    public Talking(int talkId) {
        this.talkId = talkId;
    }

    public int getTalkId() {
        return talkId;
    }

    public double getCostTalk() {
        return talkCost;
    }

    public int getMinCount() {
        return minCount;
    }

    public String getPhoneAbonent() {
        return phoneAbonent;
    }

    public String getCityName() {
        return cityName;
    }

    public String getTalkDate() {
        return talkDate;
    }

    public String getTalkTime() {
        return talkTime;
    }


}
