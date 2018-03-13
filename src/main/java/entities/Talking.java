package entities;

public class Talking extends Entity{
    private int talkId;
    private int abonentId;
    private Double talkCost;
    private int cityId;
    private int minCount;
    private String talkDate;
    private String talkTime;
    private String phoneAbonent = null;
    private String cityName = null;


    public Talking(int talkId, int abonentId, Double talkCost, int cityId, int minCount, String dateTalk, String talkTime) {
        this.talkId = talkId;
        this.abonentId = abonentId;
        this.talkCost = talkCost;
        this.cityId = cityId;
        this.minCount = minCount;
        this.talkDate = dateTalk;
        this.talkTime = talkTime;
    }

    public Talking(String phoneAbonent, Double talkCost, String cityName, int minCount, String talkDate, String talkTime) {
        this.phoneAbonent = phoneAbonent;
        this.talkCost = talkCost;
        this.cityName = cityName;
        this.minCount = minCount;
        this.talkDate = talkDate;
        this.talkTime = talkTime;
    }

    public int getTalkId() {
        return talkId;
    }

    public int getAbonentId() {
        return abonentId;
    }

    public Double getCostTalk() {
        return talkCost;
    }

    public int getCityId() {
        return cityId;
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

    public void setTalkId(int talkId) {
        this.talkId = talkId;
    }

    public void setAbonentId(int abonentId) {
        this.abonentId = abonentId;
    }

    public void setCostTalk(Double talkCost) {
        this.talkCost = talkCost;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public void setTalkDate(String talkDate) {
        this.talkDate = talkDate;
    }

    public void setTalkTime(String talkTime) {
        this.talkTime = talkTime;
    }
}
