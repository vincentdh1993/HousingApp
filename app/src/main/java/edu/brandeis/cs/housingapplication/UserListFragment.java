package edu.brandeis.cs.housingapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 11/19/17.
 */

public class UserListFragment extends Activity {
    List<UserListItem> list = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    TextView name, review,photo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        EditText inputSearch = (EditText) findViewById(R.id.inputSearch);

        ListView mListView = (ListView) findViewById(R.id.user_results);
        UserListItem[] listItems = new UserListItem[5];
        for(int i = 0; i < 5; i++){
            listItems[i] = new UserListItem("i"+5);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }

}
