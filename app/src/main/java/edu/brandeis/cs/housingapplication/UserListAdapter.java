package edu.brandeis.cs.housingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 11/23/17.
 */

public class UserListAdapter extends ArrayAdapter <UserListItem> {
    ArrayAdapter<UserListItem> adapter;
    List<UserListItem> users=new ArrayList<>();

    public UserListAdapter(Context context, ArrayList<UserListItem> data) {
        super(context,android.R.layout.simple_list_item_1, data);
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater vi = LayoutInflater.from(getContext());
        UserListItem user = getItem(position);
        view = vi.inflate(R.layout.user_item, null);
        UserListItem p = getItem(position);
        TextView name=(TextView) view.findViewById(R.id.name);
        TextView phone=(TextView) view.findViewById(R.id.phone);
        RatingBar review=(RatingBar) view.findViewById(R.id.ratingBar);
        TextView adddress=(TextView) view.findViewById(R.id.address);
        ImageView img=(ImageView)view.findViewById(R.id.user_profile_img);
        name.setText(user.getName());
        review.setRating((float) 3.5); //get review from db
        adddress.setText("address");
        phone.setText("text");
        img.setImageResource(R.drawable.user);
        return view;
    }


}
