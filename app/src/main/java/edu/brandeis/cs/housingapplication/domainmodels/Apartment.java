package edu.brandeis.cs.housingapplication.domainmodels;

//Domain maodel for packaging data and sending it to the backend
public class Apartment {
    private String address;
    private String description;
    private int squareFeet;
    private double price;
    private int roomCount;

    public Apartment(String address, String description, int squareFeet, double price, int roomCount) {
        this.address = address;
        this.description = description;
        this.squareFeet = squareFeet;
        this.price = price;
        this.roomCount = roomCount;
    }

    public Apartment() {
        //for Jackson
    }

    public double getPrice() {
        return price;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }
}
