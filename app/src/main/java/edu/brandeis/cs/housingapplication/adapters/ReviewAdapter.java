package edu.brandeis.cs.housingapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.brandeis.cs.housingapplication.R;
import edu.brandeis.cs.housingapplication.domainmodels.Rating;

/**
 * Created by eureyuri on 2017/11/28.
 */

public class ReviewAdapter extends BaseAdapter {
    private List<Rating> ratings;

    public ReviewAdapter(List<Rating> ratings) {
        this.ratings = ratings;
    }


    @Override
    public int getCount() {
        return ratings.size();
    }

    @Override
    public Object getItem(int index) {
        return ratings.get(index);
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    public void add(Rating review) {
        ratings.add(review);
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
        Rating reviewData = ratings.get(index);

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_entry, parent, false);
        }

        TextView name = (TextView)view.findViewById(R.id.review_name);
        name.setText(Integer.toString(reviewData.getRatingID()));

        TextView rating = (TextView)view.findViewById(R.id.review_rate);
        rating.setText(reviewData.getContent());


        RatingBar stars = (RatingBar) view.findViewById(R.id.ratingFeed);
        stars.setNumStars(reviewData.getStarCount());

        return view;
    }
}

