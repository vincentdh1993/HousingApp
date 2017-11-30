package edu.brandeis.cs.housingapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.brandeis.cs.housingapplication.R;
import edu.brandeis.cs.housingapplication.adapters.listitems.ReviewData;

/**
 * Created by eureyuri on 2017/11/28.
 */

public class ReviewAdapter extends BaseAdapter {
    private ArrayList<ReviewData> reviewDatas;

    public ReviewAdapter() {
        reviewDatas = new ArrayList<ReviewData>();
        reviewDatas.add(new ReviewData(1, "Eurey", "☆☆☆☆", "Hello"));
        reviewDatas.add(new ReviewData(2, "Kevin", "☆☆☆", "Hello2"));
        reviewDatas.add(new ReviewData(3, "Moses", "☆☆", "Hello3"));
        reviewDatas.add(new ReviewData(4, "Vincent", "☆", "Hello4"));
        reviewDatas.add(new ReviewData(5, "Daniel", "☆☆☆", "Hello5"));
    }

    @Override
    public int getCount() {
        return reviewDatas.size();
    }

    @Override
    public Object getItem(int index) {
        return reviewDatas.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    public void add(ReviewData review) {
        reviewDatas.add(review);
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
        ReviewData reviewData = reviewDatas.get(index);

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_entry, parent, false);
        }

        TextView name = (TextView)view.findViewById(R.id.review_name);
        name.setText(reviewData.getName());

        TextView rating = (TextView)view.findViewById(R.id.review_rate);
        rating.setText(reviewData.getRating());

        TextView comment = (TextView)view.findViewById(R.id.review_comment);
        comment.setText(reviewData.getComment());

        return view;
    }
}

