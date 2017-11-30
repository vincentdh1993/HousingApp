package edu.brandeis.cs.housingapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.housingapplication.adapters.ReviewAdapter;
import edu.brandeis.cs.housingapplication.adapters.ViewPagerAdapter;
import edu.brandeis.cs.housingapplication.domainmodels.Apartment;
import edu.brandeis.cs.housingapplication.domainmodels.Rating;
import edu.brandeis.cs.housingapplication.utils.NetworkUtils;

/**
 * Created by eureyuri on 2017/11/26.
 */

public class DisplayHouseActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private static final int APT_RATINGS = 1;
    private static final String APT_RATINGS_QUERY = "ratings_query";

    ViewPager viewPager;
    ViewPagerAdapter adapter;
    BottomNavigationView bottomNavigationView;
    ListView reviewList;

    //TODO: NEED TO MAKE SURE THAT RATE ACTIVITY ALSO SENDS BACK APARTMENT JSON
    private Apartment apartment; //since this is an activity to dispaly one apt, makes sense to have a member field
    private String aptJSON;

    private String[] images = {
            "https://static.pexels.com/photos/186077/pexels-photo-186077.jpeg",
            "https://static.pexels.com/photos/106399/pexels-photo-106399.jpeg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_house);
        this.apartment = getApartment();
        this.aptJSON = getIntent().getStringExtra(getString(R.string.aptExtra));
        Log.d("APARTMENT JSON:", aptJSON); //so ratings is coming back null, naturally

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.help_logo);

        extractAndSet();
        startSlideShow();

        Button rateButton = (Button)findViewById(R.id.rateButton);

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RateHouseActivity.class);
                i.putExtra(getString(R.string.aptExtra), aptJSON);
                i.putExtra("isUser", false);
                startActivity(i);
            }
        });

        Button contactButton = (Button)findViewById(R.id.contactButton);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Get number from db?
                String phoneNumber = "123-456-789";
                startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.fromParts("tel", phoneNumber, null)));
            }
        });

        reviewList = (ListView) findViewById(R.id.reviews_list);
        getAptRatings();
        setBottomNavigations();
    }

    private void getAptRatings() {
        URL url = NetworkUtils.createUrl("apartments", apartment.getApartmentID(), "ratings");
        Bundle queryBundle = new Bundle();
        queryBundle.putString(APT_RATINGS_QUERY, url.toString());
        LoaderManager loaderManager = getLoaderManager();
        Loader<String> ratingsLoader = loaderManager.getLoader(APT_RATINGS);
        if (ratingsLoader == null) {
            loaderManager.initLoader(APT_RATINGS, queryBundle, this);
        } else {
            loaderManager.restartLoader(APT_RATINGS, queryBundle, this);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return loadingAptRatings(this, args);
    }

    @SuppressLint("StaticFieldLeak")
    private AsyncTaskLoader<String> loadingAptRatings(Context context, final Bundle args) {
        return new AsyncTaskLoader<String>(context) {
            @Override
            public String loadInBackground() {
                String theURL = args.getString(APT_RATINGS_QUERY);
                try {
                    URL ratingsUrl = new URL(theURL);
                    String results = NetworkUtils.doHttpGet(ratingsUrl);
                    return  results;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null; //you're in the shit
            }

            @Override
            protected void onStartLoading() {
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d("DATA ONLOADFINISHED", data);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Rating>> mapType = new TypeReference<List<Rating>>() {};
        List<Rating> results = new ArrayList<>();
        Log.d("RESULTS ONLOADFIN", results.toString());
        try {
            results = mapper.readValue(data, mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView stars = (TextView) findViewById(R.id.houseRating);
        stars.setText(getStars(results));
        reviewList.setAdapter(new ReviewAdapter(results));
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        //do nothing
    }

    //Extracts the data from the incoming intent, deserializes and uses it
    //to populate some textviews
    private void extractAndSet() {
        TextView houseName = (TextView)findViewById(R.id.houseName);
        houseName.setText(apartment.getAddress());

        TextView rating = (TextView)findViewById(R.id.houseRating);
        rating.setText(getStars(apartment.getRatings()));

        TextView address = (TextView)findViewById(R.id.description_display_text);
        address.setText("Description: " + apartment.getDescription());
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
        if (apartment.getRatings() == null) {
            apartment.setRatings(new ArrayList<Rating>());
        }
        return apartment;
    }

    private String getStars(List<Rating> ratings) {
        String star = "☆";
        int totalStars = 0;
        for (Rating r : ratings) {
            totalStars += r.getStarCount();
        }
        if (totalStars == 0) {
            return "Not yet rated (No stars)";
        }
        double avgStars = Math.ceil((double)totalStars/ratings.size());
        String result = "";
        for (int i = 0; i < avgStars; i++) {
            result += star;
        }
        return result;
    }

    private void startSlideShow() {
        //Ok so this I don't really need to touch, because it is just dealing
        //with images and we're not fucking wit images rn
        viewPager = (ViewPager)findViewById(R.id.slideshowViewPager);
        adapter = new ViewPagerAdapter(DisplayHouseActivity.this, images);
        viewPager.setAdapter(adapter);

    }

    private void setBottomNavigations() {
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
