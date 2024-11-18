package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    private long id;
    private String name;
    private int contact;
    private String checkIn;
    private String checkOut;

    @OneToMany(mappedBy = "customer")
    private Set<CustomerRoom> rooms = new HashSet<>();

    @OneToOne(mappedBy = "customer")
    private Bill bill;

    public Customer() {
    }

    public Customer(long id, String name, int contact, String checkIn, String checkOut) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Customer(long id, String name, int contact, String checkIn, String checkOut, Set<CustomerRoom> rooms, Bill bill) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.rooms = rooms;
        this.bill = bill;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Set<CustomerRoom> getRooms() {
        return rooms;
    }

    public void setRooms(Set<CustomerRoom> rooms) {
        this.rooms = rooms;
    }
}
