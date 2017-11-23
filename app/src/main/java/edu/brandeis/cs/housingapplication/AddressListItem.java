package edu.brandeis.cs.housingapplication;

/**
 * Created by Kevin on 11/23/17.
 */

public class AddressListItem {
    private String businessId;
    private String businessName;
    private String imageURL;
    private String price;
    private Double latitude;
    private Double longitude;
    private Double rating;
    private Boolean open;

    public AddressListItem(String name, String imageURL, String price, String id, Double rating, String open, Double longitude, Double latitude){
        this.businessName = name;
        this.imageURL = imageURL;
        this.price = price;
        this.businessId = id;
        this.rating = rating;
        this.longitude = longitude;
        this.latitude = latitude;

        if(open.equals("true")){
            this.open = true;
        } else {
            this.open = false;
        }
    }
}
