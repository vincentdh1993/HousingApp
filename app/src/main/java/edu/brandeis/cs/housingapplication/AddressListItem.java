package edu.brandeis.cs.housingapplication;

/**
 * Created by Kevin on 11/23/17.
 */

public class AddressListItem {
    private String addr;
    private double price;
    private double latitude;
    private double longitude;
    private double rating;

    public AddressListItem(String addr, Double rating){
        this.addr = addr;
        this.price = price;
        this.rating = rating;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getPrice() {
        return this.price;
    }

    public String getAddress() {
        return this.addr;
    }

    public double getLongitutde() {
        return this.longitude;
    }
    public double getLatittude() {
        return this.latitude;
    }

    public double getRating() {
        return this.getRating();
    }

}
