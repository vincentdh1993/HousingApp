package edu.brandeis.cs.housingapplication;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 11/23/17.
 */

public class UserListAdapter extends ListFragment {
    ArrayAdapter<UserListItem> adapter;
    List<UserListItem> users=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(adapter);
    }

    public View getView(int position, View view, ViewGroup parent) {
        return view;
    }

}
