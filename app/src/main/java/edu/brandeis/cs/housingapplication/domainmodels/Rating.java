package edu.brandeis.cs.housingapplication.domainmodels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating {
    private int ratingID;
    private String content;
    private String[] comments;
    private int starCount;

    public Rating() {
        //for Jackson
    }

    public Rating(int id, String content, String[] comment) {
        this.ratingID = id;
        this.content = content;
        this.comments = comments;
    }

    public Rating(int i, String s1, String s2, String s3) {}


    public void setContent(String content) {
        this.content = content;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public int getRatingID() {
        return ratingID;
    }

    public String getContent() {
        return content;
    }

    public String[] getComments() {
        return comments;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    @Override
    public String toString() {
        return "RatingID: " + ratingID + "\nStars: " + starCount +
                "\nContent: " + content;
    }
}
