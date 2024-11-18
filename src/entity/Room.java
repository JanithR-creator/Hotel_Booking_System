package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Room {
    @Id
    private int roomNum;
    private boolean acStatus;
    private String bedType;

    @OneToOne
    private Customer customer;

    public Room() {
    }

    public Room(int roomNum, boolean acStatus, String bedType) {
        this.roomNum = roomNum;
        this.acStatus = acStatus;
        this.bedType = bedType;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public boolean isAcStatus() {
        return acStatus;
    }

    public void setAcStatus(boolean acStatus) {
        this.acStatus = acStatus;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
