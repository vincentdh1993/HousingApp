package edu.brandeis.cs.housingapplication.adapters.listitems;

/**
 * Created by eureyuri on 2017/11/28.
 */

public class ReviewData {
    private long id;
    private String name;
    private String rating;
    private String comment;

    public ReviewData(long id, String name, String rating, String comment) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(String description) {
        this.rating = rating;
    }

    public void setComment(String notes) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
