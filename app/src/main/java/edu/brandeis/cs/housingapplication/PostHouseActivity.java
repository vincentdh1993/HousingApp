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

/**
 * Created by eureyuri on 2017/11/20.
 */

public class PostHouseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_house);

        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText addressText = (EditText)findViewById(R.id.address_text);
                String address = addressText.getText().toString();

                EditText descriptionText = (EditText)findViewById(R.id.description_text);
                String description = descriptionText.getText().toString();

                EditText priceText = (EditText)findViewById(R.id.price_text);
                double price = Double.parseDouble(priceText.getText().toString());

//                db.addHouse(address, description, price);
                
                finish();
            }
        });

        Button cancelButton = (Button)findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
