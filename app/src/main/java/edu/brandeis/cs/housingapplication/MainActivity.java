package edu.brandeis.cs.housingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button for starting the PostHouseActivity
//        startActivity(new Intent(this, PostHouseActivity.class));
    }

}
