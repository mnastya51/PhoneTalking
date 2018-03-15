package entities;

public class Abonent extends Entity {
    private int id;
    private String phone;
    private String fio;
    private String address;
    private Boolean facility;

    public Abonent(int id) {
        this.id = id;
    }

    public Abonent(int id, String phone, String fio, String address, Boolean facility) {
        this.id = id;
        this.phone = phone;
        this.fio = fio;
        this.address = address;
        this.facility = facility;
    }

    public Abonent(String phone, String fio, String address, Boolean facility) {
        this.phone = phone;
        this.fio = fio;
        this.address = address;
        this.facility = facility;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFacility(Boolean facility) {
        this.facility = facility;
    }

    public int getId() {

        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getFio() {
        return fio;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getFacility() {
        return facility;
    }
}
