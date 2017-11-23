package edu.brandeis.cs.housingapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by eureyuri on 2017/11/20.
 */

public class PostHouseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
