package edu.brandeis.cs.housingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;
/**
 * Created by Kevin on 11/22/17.
 */

public class AdvanedFilterSearch extends AppCompatActivity {
    Intent returnIntent;
    Button submit;
    EditText location;
    Spinner price;
    Spinner minPrice;
    Spinner rating;
    Spinner radius;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced_filter);
        setUpWidgets();
        setLocation();
        setPrice();
        setRating();
        setRadius();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });
    }

    public void setPriceExtra(String max, String min){
        int start;
        int end;
        if(max.equals("$$$$ (Default)")){
            Prefrences.priceInt = 0;
            end = 4;
        } else if (max.equals("$$$")){
            Prefrences.priceInt = 1;
            end = 3;
        } else if (max.equals("$$")) {
            Prefrences.priceInt = 2;
            end = 2;
        } else {
            Prefrences.priceInt = 3;
            end = 1;
        }

        if(min.equals("$ (Default)")){
            Prefrences.minPriceInt = 0;
            start = 1;
        } else if (min.equals("$$")){
            Prefrences.minPriceInt = 1;
            start = 2;
        } else if (min.equals("$$$")){
            Prefrences.minPriceInt = 2;
            start = 3;
        } else {
            Prefrences.minPriceInt = 3;
            start = 4;
        }

        String priceString = "";
        for(int i = start; i <= end; i++){
            if(i == start){
                priceString = "" + i;
            } else {
                priceString = priceString + "," + i;
            }
        }
        Prefrences.thePrice = priceString;
        returnIntent.putExtra("price",priceString);
    }

    public void setUpWidgets() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        //setup other widgets and intent
        returnIntent = new Intent();
        returnIntent.putExtra("location","current");
        returnIntent.putExtra("price","1,2,3,4");
        returnIntent.putExtra("rating","1");
        returnIntent.putExtra("radius","16093");
        submit = (Button) findViewById(R.id.submit_search);
        location = (EditText) findViewById(R.id.location_text);
        price = (Spinner) findViewById(R.id.price_spinner);
        minPrice = (Spinner) findViewById(R.id.min_price_spinner);
        rating = (Spinner) findViewById(R.id.rating_spinner);
        radius = (Spinner) findViewById(R.id.radius_spinner);
    }

    public void setLocation() {
        location.setText(Prefrences.location);
        returnIntent.putExtra("location",Prefrences.location);
        location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String loc = location.getText().toString();

                if(loc.equals("")){
                    Prefrences.theLocation = "current";
                    returnIntent.putExtra("location","current");
                } else {
                    Prefrences.theLocation = loc;
                    returnIntent.putExtra("location", loc);
                }

                Prefrences.location = loc;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void setPrice() {
        //setup price spinner
        ArrayAdapter<CharSequence> priceAdapter = ArrayAdapter.createFromResource(this,
                R.array.priceArray , android.R.layout.simple_spinner_item);
        price.setAdapter(priceAdapter);
        price.setSelection(Prefrences.priceInt);
        price.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String maxPrice = price.getSelectedItem().toString();
                String minPriceString = minPrice.getSelectedItem().toString();
                setPriceExtra(maxPrice,minPriceString);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }

    public void setRating() {




        //setup rating spinner
        ArrayAdapter<CharSequence> ratingAdapter = ArrayAdapter.createFromResource(this,
                R.array.ratingArray , android.R.layout.simple_spinner_item);
        rating.setAdapter(ratingAdapter);
        rating.setSelection(Prefrences.ratingInt);
        rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String text = rating.getSelectedItem().toString();
                if(text.equals("1 Star (Default)")){
                    Log.d("rating","1 star default");
                    Prefrences.ratingInt = 0;
                    Prefrences.theRating = "1";
                    returnIntent.putExtra("rating","1");
                } else if(text.equals("2 Stars")){
                    Prefrences.ratingInt = 1;
                    Prefrences.theRating = "2";
                    returnIntent.putExtra("rating","2");
                } else if(text.equals("3 Stars")){
                    Prefrences.ratingInt = 2;
                    Prefrences.theRating = "3";
                    returnIntent.putExtra("rating","3");
                } else if(text.equals("4 Stars")){
                    Prefrences.ratingInt = 3;
                    Prefrences.theRating = "4";
                    returnIntent.putExtra("rating","4");
                } else if(text.equals("5 Stars")){
                    Prefrences.ratingInt = 4;
                    Prefrences.theRating = "5";
                    returnIntent.putExtra("rating","5");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });

    }

    public void setRadius() {


        //setup radius spinner
        ArrayAdapter<CharSequence> radiusAdapter = ArrayAdapter.createFromResource(this,
                R.array.radiusArray , android.R.layout.simple_spinner_item);
        radius.setAdapter(radiusAdapter);
        radius.setSelection(Prefrences.radiusInt);
        radius.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String text = radius.getSelectedItem().toString();
                //1609--1, 8046--5, 16093--10, 24140--15, 32187--20
                if(text.equals("10 Miles")){
                    Prefrences.radiusInt = 0;
                    Prefrences.theRadius = "16093";
                    returnIntent.putExtra("radius","16093");
                } else if(text.equals("5 Miles (Default)")){
                    Prefrences.radiusInt = 1;
                    Prefrences.theRadius = "8046";
                    returnIntent.putExtra("radius","8046");
                } else if(text.equals("2 Miles")){
                    Prefrences.radiusInt = 2;
                    Prefrences.theRadius = "3218";
                    returnIntent.putExtra("radius","3218");
                } else if(text.equals("1 Mile")){
                    Prefrences.radiusInt = 3;
                    Prefrences.theRadius = "1609";
                    returnIntent.putExtra("radius","1609");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }
}


