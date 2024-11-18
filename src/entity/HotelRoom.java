package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HotelRoom {
    @Id
    private int number;
    private String acStatus;
    private String bedType;
    private String description;
    private double price;

    public HotelRoom() {
    }

    public HotelRoom(int number, String acStatus, String bedType, String description, double price) {
        this.number = number;
        this.acStatus = acStatus;
        this.bedType = bedType;
        this.description = description;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(String acStatus) {
        this.acStatus = acStatus;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
