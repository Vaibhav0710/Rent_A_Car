package org.carrental.entities;

public class Car {
    private int id;
    private String model;
    private String brand;
    private int year;
    private double rentalPrice;
    private boolean isAvailable;

    // Constructor for fetching from DB
    public Car(int id, String model, String brand, int year, double rentalPrice, boolean isAvailable) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.isAvailable = isAvailable;
    }

    // Constructor for adding a new car
    public Car(String model, String brand, int year, double rentalPrice, boolean isAvailable) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", rentalPrice=" + rentalPrice +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
