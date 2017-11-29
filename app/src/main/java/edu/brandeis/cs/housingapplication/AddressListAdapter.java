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

public class AddressListAdapter extends ArrayAdapter<AddressListItem> {
    private Context context;
    private boolean useList = true;
    ArrayAdapter<UserListItem> adapter;
    List<UserListItem> users=new ArrayList<>();

    public AddressListAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.users=users;
    }




    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = LayoutInflater.from(getContext());
        AddressListItem addr = getItem(position);
        View view = vi.inflate(R.layout.address_item, null);
        TextView name=(TextView) view.findViewById(R.id.address);
        TextView price=(TextView) view.findViewById(R.id.price);
        RatingBar review=(RatingBar) view.findViewById(R.id.addr_rate);
        ImageView img=(ImageView)view.findViewById(R.id.house_img);
        name.setText(addr.getAddress());
        price.setText(addr.getPrice()+"");
        review.setRating((float)5); //get review from db

        review.setRating(3);
        img.setImageResource(R.drawable.home);
        return view;

    }


}


