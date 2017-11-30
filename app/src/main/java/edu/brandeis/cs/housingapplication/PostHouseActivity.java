package edu.brandeis.cs.housingapplication;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import edu.brandeis.cs.housingapplication.domainmodels.Apartment;
import edu.brandeis.cs.housingapplication.login.SessionService;
import edu.brandeis.cs.housingapplication.utils.NetworkUtils;

/**
 * Created by eureyuri on 2017/11/20.
 */

public class PostHouseActivity extends AppCompatActivity {
    private EditText description;
    private EditText address;
    private EditText maxPrice;
    private EditText squareFeet;
    private EditText numRooms;
    private Button submitButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_house);
        getReferences();
        submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EditText addressText = (EditText)findViewById(R.id.address_text);
//                String address = addressText.getText().toString();
//
//                EditText descriptionText = (EditText)findViewById(R.id.description_text);
//                String description = descriptionText.getText().toString();
//
//                EditText priceText = (EditText)findViewById(R.id.price_text);
//                double price = Double.parseDouble(priceText.getText().toString());
                Map<String, String> params = new HashMap<>();
                params.put("landlordId", SessionService.CURRENT_USER_ID);
                new CreateApartmentTask().execute(NetworkUtils.createUrl("apartments", params));
            }
        });

        cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setTitle("Add an apartment");
        setBottomNavigations();
    }

    public class CreateApartmentTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            ObjectMapper mapper = new ObjectMapper();
            URL url = urls[0];
            Log.d("URL IN ADD APT", url.toString());
            String newApartment = "";
            try {
                newApartment = NetworkUtils.doHttpPost(url, mapper.writeValueAsString(PostHouseActivity.this.buildApartment()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newApartment;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("RESPONSE ADD APT: ", s);
            Intent intent = new Intent(PostHouseActivity.this, DisplayHouseActivity.class);
            intent.putExtra(getString(R.string.aptExtra), s);
            startActivity(intent);
        }
    }

    private Apartment buildApartment() {
        String desc = description.getText().toString();
        String addr = address.getText().toString();
        double price = Double.parseDouble(maxPrice.getText().toString());
        int squareFootage = Integer.parseInt(squareFeet.getText().toString());
        int rooms = Integer.parseInt(numRooms.getText().toString());
        return new Apartment(addr, desc, squareFootage, price, rooms);
    }

    private void getReferences() {
        description = (EditText) findViewById(R.id.description_text);
        address = (EditText) findViewById(R.id.address_text);
        maxPrice = (EditText) findViewById(R.id.price_text);
        squareFeet = (EditText) findViewById(R.id.squareFeet_text);
        numRooms = (EditText) findViewById(R.id.roomCount_text);
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
                    }
                });
    }
}
