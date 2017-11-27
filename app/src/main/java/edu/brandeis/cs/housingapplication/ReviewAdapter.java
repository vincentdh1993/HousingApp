//package edu.brandeis.cs.housingapplication;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by eureyuri on 2017/11/27.
// */
//
//public class ReviewAdapter extends BaseAdapter {
//    private ArrayList<ReviewEntryData> reviewEntryDatas;
//    private Context appContext;
//
//    public ReviewAdapter(DBOpenHelper dbOpenHelper, Context context) {
//        reviewEntryDatas = new ArrayList<ReviewEntryData>();
//        appContext = context;
//
//        for (ReviewEntryData data : dbOpenHelper.getReviews()) {
//            reviewEntryDatas.add(data);
//        }
//    }
//
//    @Override
//    public int getCount() {
//        return reviewEntryDatas.size();
//    }
//
//    @Override
//    public Object getItem(int index) {
//        return reviewEntryDatas.get(index);
//    }
//
//    @Override
//    public long getItemId(int index) {
//        return index;
//    }
//
//    public void add(ReviewEntryData review) {
//        reviewEntryDatas.add(review);
//    }
//
//    public void delete(ReviewEntryData review) {
//        reviewEntryDatas.remove(review);
//    }
//
//    @Override
//    public View getView(int index, View view, final ViewGroup parent) {
//        final ReviewEntryData reviewData = reviewEntryDatas.get(index);
//
//        if (view == null) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_entry, parent, false);
//        }
//
//        TextView name = (TextView)view.findViewById(R.id.review_name);
//        name.setText(reviewData.getName());
//
//        TextView rate = (TextView)view.findViewById(R.id.review_rate);
//        rate.setText(reviewData.getRate());
//
//        TextView comment = (TextView)view.findViewById(R.id.review_comment);
//        comment.setText(reviewData.getComment());
//
//        return view;
//    }
//
//
//}