package edu.brandeis.cs.housingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by mbug on 11/27/2017.
 */

public class RateHouseActivity extends AppCompatActivity{

    private RatingBar ratingBar;
    private EditText comment;
    private Button submit;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_house);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.help_logo);

        // Initialize things
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        comment = (EditText)findViewById(R.id.commentEdit);
        submit = (Button)findViewById(R.id.submitRating);
        cancel = (Button)findViewById(R.id.cancelRating);

        // setup on click listener
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast

                String rating = String.valueOf(ratingBar.getRating());
                String resultComment = comment.getText().toString();
                Intent result = new Intent();
                result.putExtra("rating",rating);
                result.putExtra("comment",resultComment);
                setResult(Activity.RESULT_OK,result);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get values and then displayed in a toast

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
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
