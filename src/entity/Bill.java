package entity;

import javax.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double restCharge;
    private double gymCharge;
    private double barCharge;
    private double spaCharge;
    private double roomCharge;
    private double serviceCharge;

    @OneToOne
    private Customer customer;

    public Bill() {
    }

    public Bill(double restCharge, double gymCharge, double barCharge, double spaCharge, double roomCharge, double serviceCharge) {
        this.restCharge = restCharge;
        this.gymCharge = gymCharge;
        this.barCharge = barCharge;
        this.spaCharge = spaCharge;
        this.roomCharge = roomCharge;
        this.serviceCharge = serviceCharge;
    }

    public Bill(long id, double restCharge, double gymCharge, double barCharge, double spaCharge, double roomCharge, double serviceCharge) {
        this.id = id;
        this.restCharge = restCharge;
        this.gymCharge = gymCharge;
        this.barCharge = barCharge;
        this.spaCharge = spaCharge;
        this.roomCharge = roomCharge;
        this.serviceCharge = serviceCharge;
    }

    public Bill(long id, double restCharge, double gymCharge, double barCharge, double spaCharge, double roomCharge,
                double serviceCharge, Customer customer) {
        this.id = id;
        this.restCharge = restCharge;
        this.gymCharge = gymCharge;
        this.barCharge = barCharge;
        this.spaCharge = spaCharge;
        this.roomCharge = roomCharge;
        this.serviceCharge = serviceCharge;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRestCharge() {
        return restCharge;
    }

    public void setRestCharge(double restCharge) {
        this.restCharge = restCharge;
    }

    public double getGymCharge() {
        return gymCharge;
    }

    public void setGymCharge(double gymCharge) {
        this.gymCharge = gymCharge;
    }

    public double getBarCharge() {
        return barCharge;
    }

    public void setBarCharge(double barCharge) {
        this.barCharge = barCharge;
    }

    public double getSpaCharge() {
        return spaCharge;
    }

    public void setSpaCharge(double spaCharge) {
        this.spaCharge = spaCharge;
    }

    public double getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(double roomCharge) {
        this.roomCharge = roomCharge;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
