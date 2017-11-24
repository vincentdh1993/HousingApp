package edu.brandeis.cs.housingapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        TextView photo=(TextView) view.findViewById(R.id.photo);
        TextView review=(TextView) view.findViewById(R.id.review);
        TextView adddress=(TextView) view.findViewById(R.id.address);
        TextView phone=(TextView) view.findViewById(R.id.photo);
        name.setText(user.getName());
        photo.setText(user.getImage());
        review.setText(user.getReview());
        adddress.setText("address");
        phone.setText("text");
        return view;
    }


}
