package org.carrental.entities;


public class Car {
    private int id;
    private String model;
    private String brand;
    private int year;
    private Long pricePerDay;
    private boolean available;

    // Constructor for fetching from DB
    public Car(int id, String model, String brand, int year, Long pricePerDay, boolean available) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    // Constructor for adding a new car
    public Car(String model, String brand, int year, Long pricePerDay, boolean available) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.available = available;
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

    public Long getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Long pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", pricePerDay=" + pricePerDay +
                ", available=" + available +
                '}';
    }
}
