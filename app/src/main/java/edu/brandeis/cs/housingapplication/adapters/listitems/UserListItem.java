package edu.brandeis.cs.housingapplication.adapters.listitems;

/**
 * Created by Kevin on 11/23/17.
 */

public class UserListItem {
    private String name;
    private double review;
    private String image;
    private String address;
    private String phone;
    private String img;



    public UserListItem(String name) {
        this.name=name;
    }


    public String getName() {
        return name;
    }
    public   String getAddress() {
        return this.address;
    }
    public   String getPhone() {
        return this.address;
    }
    public String getImgURL() {
        return this.img;
    }
    public void setImg(String img) {
        this.img=img;
    }

    public double getReview() {
        return review;
    }
    public String getImage() {
        return  image;
    }

    public void setName(String name) {
        this.name=name;
    }
    public void setImage(String image) {
        this.image=image;
    }
    public   void setReview(double review) {
        this.review=review;
    }


}
