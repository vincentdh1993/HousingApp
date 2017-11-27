package edu.brandeis.cs.housingapplication;

/**
 * Created by eureyuri on 2017/11/27.
 */

public class ReviewEntryData {
    private long id;
    private String name;
    private String rate;
    private String comment;

    public ReviewEntryData(long id, String name, String rate,  String comment) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRate() {
        return rate;
    }

    public String getComment() {
        return comment;
    }
}
