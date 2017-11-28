package edu.brandeis.cs.housingapplication;

/**
 * Created by Kevin on 11/23/17.
 */

public class UserListItem {
    private String name;
    private double review;
    private String image;
    private String address;
    private String phone;


    public UserListItem(String name) {
        this.name=name;
    }


    protected String getName() {
        return name;
    }
    protected  String getAddress() {
        return this.address;
    }
    protected  String getPhone() {
        return this.address;
    }


    protected double getReview() {
        return review;
    }
    protected String getImage() {
        return  image;
    }

    protected void setName(String name) {
        this.name=name;
    }
    protected void setImage(String image) {
        this.image=image;
    }
    protected  void setReview(double review) {
        this.review=review;
    }


}
