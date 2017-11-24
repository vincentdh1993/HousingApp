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

public class AddressListAdapter extends ArrayAdapter<AddressListItem> {
    private Context context;
    private boolean useList = true;
    ArrayAdapter<UserListItem> adapter;
    List<UserListItem> users=new ArrayList<>();

    public AddressListAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
    }




    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = LayoutInflater.from(getContext());
        AddressListItem addr = getItem(position);
        View view = vi.inflate(R.layout.address_item, null);
        TextView name=(TextView) view.findViewById(R.id.address);
        TextView price=(TextView) view.findViewById(R.id.price);
        TextView review=(TextView) view.findViewById(R.id.review);
        TextView longitude=(TextView) view.findViewById(R.id.longtitude);
        TextView lattitude=(TextView) view.findViewById(R.id.latitude);
        name.setText(addr.getAddress());
        price.setText(addr.getPrice()+"");
        review.setText(5+"");
        longitude.setText("LONG");
        lattitude.setText("LAT");
        return view;

    }


}


