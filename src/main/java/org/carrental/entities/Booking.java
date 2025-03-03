package org.carrental.entities;

import java.util.Date;

public class Booking {
    private int bookingId;
    private int customerId;
    private int carId;
    private Date rentalDate;
    private Date returnDate;
    private double totalCost;

    public Booking() {
    }

    public Booking(int bookingId, int customerId, int carId, Date rentalDate, Date returnDate, double totalCost) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.carId = carId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.totalCost = totalCost;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", carId=" + carId +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", totalCost=" + totalCost +
                '}';
    }
}
