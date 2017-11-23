package edu.brandeis.cs.housingapplication;

/**
 * Created by Kevin on 11/23/17.
 */

public class UserListItem {
    private String name;
    private String review;
    private String image;

    public UserListItem(String name) {
        this.name=name;
        this.review="***";
        this.image="LOL";
    }


    protected String getName() {
        return name;
    }


    protected String getReview() {
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
    protected  void setReview(String review) {
        this.review=review;
    }


}
