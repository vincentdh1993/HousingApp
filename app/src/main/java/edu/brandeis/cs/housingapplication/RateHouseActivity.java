package edu.brandeis.cs.housingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import edu.brandeis.cs.housingapplication.domainmodels.Apartment;
import edu.brandeis.cs.housingapplication.domainmodels.Rating;
import edu.brandeis.cs.housingapplication.domainmodels.User;
import edu.brandeis.cs.housingapplication.login.SessionService;
import edu.brandeis.cs.housingapplication.utils.NetworkUtils;

/**
 * Created by mbug on 11/27/2017.
 */

//This class is kind of a misnomer, because we use it to rate houses or users
public class RateHouseActivity extends AppCompatActivity{

    private RatingBar ratingBar;
    private EditText comment;
    private Button submit;
    private Button cancel;
    private Apartment apartment;
    private String userId;
    private boolean isUserRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_house);
        isUserRating = getIntent().getBooleanExtra("isUser", false);
        if (!isUserRating) {
            this.apartment = getApartment();
        }
        this.userId = getIntent().getStringExtra("USER_ID");

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
                Map<String, String> params = new HashMap<>();
                params.put("writerId", SessionService.CURRENT_USER_ID);
                if (isUserRating) {
                    new UploadRating().execute(NetworkUtils.createUrl(params, "users", userId, "ratings"));
                } else {
                    new UploadRating().execute(NetworkUtils.createUrl(params, "apartments", apartment.getApartmentID(),
                            "ratings"));
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
        setBottomNavigations();
    }

    public class UploadRating extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            ObjectMapper objectMapper = new ObjectMapper();
            URL url = urls[0];
            Log.d("URL IN DO RATING:", url.toString());
            String newRating = "";
            try {
                newRating = NetworkUtils.doHttpPost(url, objectMapper.writeValueAsString(RateHouseActivity.this.buildRating()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newRating;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            finish();
        }
    }

    private Rating buildRating() {
        String rating = comment.getText().toString();
        int starCount = ratingBar.getNumStars();
        return new Rating(rating, starCount);
    }

    private Apartment getApartment() {
        ObjectMapper mapper = new ObjectMapper();
        Intent intent = getIntent();
        String json = intent.getStringExtra(getString(R.string.aptExtra));
        Apartment apartment = new Apartment();
        try {
            apartment = mapper.readValue(json, Apartment.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apartment;
    }

    private void setBottomNavigations() {
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
