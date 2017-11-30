package edu.brandeis.cs.housingapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

import edu.brandeis.cs.housingapplication.adapters.UserListAdapter;
import edu.brandeis.cs.housingapplication.adapters.listitems.UserListItem;

/**
 * Created by Kevin on 11/19/17.
 */

public class UserListFragment extends Fragment {
    ListView listView;
    View rootView;
    ArrayList <UserListItem>stringList= new ArrayList();
    ArrayList <UserListItem>copyData= new ArrayList();
    UserListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup v, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.user_list_fragment, v, false);
        listView = (ListView) rootView.findViewById(R.id.user_results);
        populateList();
        searchData();
        return rootView;

    }

    private void populateList() {
        for(int i=0; i<5; i++) {
            stringList.add(new UserListItem("name "+i));
        }
        copyData.addAll(stringList);
        adapter=new UserListAdapter(getContext(), stringList);

        listView.setAdapter(adapter);
    }

    private void searchData() {
        final EditText et = (EditText) rootView.findViewById(R.id.search_bar);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = et.getText().toString().toLowerCase(Locale.getDefault());
                filter(text);
            }
        });
    }

    public void filter(String charText) {
        Log.e("filter", "filtering");
        charText = charText.toLowerCase(Locale.getDefault());
        stringList.clear();
        if (charText.length() == 0) {
            stringList.addAll(copyData);
        } else {
            for (UserListItem wp : copyData) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    stringList .add(wp);

                }
            }
            adapter=new UserListAdapter(getContext(), stringList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


}
