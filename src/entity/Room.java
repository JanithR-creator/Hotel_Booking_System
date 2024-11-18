package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Room {
    @Id
    private long roomNum;
    private boolean acStatus;
    private String bedType;

    @ManyToOne
    private Customer customer;

    public Room() {
    }

    public Room(long roomNum, boolean acStatus, String bedType, Customer customer) {
        this.roomNum = roomNum;
        this.acStatus = acStatus;
        this.bedType = bedType;
        this.customer = customer;
    }

    public long getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(long roomNum) {
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
