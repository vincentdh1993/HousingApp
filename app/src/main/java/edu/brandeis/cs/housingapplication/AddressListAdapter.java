package edu.brandeis.cs.housingapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kevin on 11/23/17.
 */

public class AddressListAdapter extends ArrayAdapter {
    private Context context;
    private boolean useList = true;

    public AddressListAdapter(Context context, List items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.context = context;
    }


    private class ViewHolder{
        TextView titleText;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        AddressListItem item = (AddressListItem)getItem(position);
        View viewToUse = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.address_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView)viewToUse.findViewById(R.id.title_bar);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        return viewToUse;
    }
}


