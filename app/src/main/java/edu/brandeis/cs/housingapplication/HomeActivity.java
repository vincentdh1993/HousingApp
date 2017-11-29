package edu.brandeis.cs.housingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private boolean flagmale = false;
    private boolean flagfemale = false;

    RadioButton house;
    RadioButton landLord;
    EditText searching;
    Button search;
    Button post;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.help_logo);

        house = (RadioButton)findViewById(R.id.radioButton3);
        landLord = (RadioButton) findViewById(R.id.radioButton4);
        searching = (EditText) findViewById(R.id.editText4);
        search = (Button) findViewById(R.id.button3);
        post = (Button) findViewById(R.id.button4);


        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (house.isChecked()) {
                    if (!flagmale) {
                        house.setChecked(true);
                        landLord.setChecked(false);
                        flagmale = true;
                        flagfemale = false;
                    } else {
                        flagmale = false;
                        house.setChecked(false);
                        landLord.setChecked(false);
                    }
                }
            }
        });

        landLord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (landLord.isChecked()) {
                    if (!flagfemale) {
                        landLord.setChecked(true);
                        house.setChecked(false);
                        flagfemale = true;
                        flagmale = false;
                    } else {
                        flagfemale = false;
                        landLord.setChecked(false);
                        house.setChecked(false);
                    }
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = "";
                if(flagfemale == true){
                    temp = "Land Lord";
                    Toast.makeText(HomeActivity.this, temp + searching.getText().toString(), Toast.LENGTH_SHORT).show();
                    // Move to search results.
                } else {
                    temp = "House";
                    Toast.makeText(HomeActivity.this, temp + searching.getText().toString(), Toast.LENGTH_SHORT).show();
                    // Move to search results.
                }


            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PostHouseActivity.class));
            }
        });
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.search_bottom:
                                startActivity(new Intent(getBaseContext(), SearchFragmentTabs.class));
                                overridePendingTransition(0, 0);
                                break;

                            case R.id.account_bottom:
                                //   startActivity(new Intent(getBaseContext(), SearchFragmentTabs.class));
                                overridePendingTransition(0, 0);
                                break;
                            case R.id.home_bottom:
                                startActivity(new Intent(getBaseContext(), HomeActivity.class));
                                overridePendingTransition(0, 0);
                                break;
                        }
                        return true;
                    }});

    }


}
