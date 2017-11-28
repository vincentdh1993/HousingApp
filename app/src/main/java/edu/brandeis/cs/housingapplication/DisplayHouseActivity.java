package edu.brandeis.cs.housingapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by eureyuri on 2017/11/26.
 */

public class DisplayHouseActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;

    private String[] images = {
            "https://static.pexels.com/photos/186077/pexels-photo-186077.jpeg",
            "https://static.pexels.com/photos/106399/pexels-photo-106399.jpeg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_house);

        extractAndSet();
        startSlideShow();


        Button rateButton = (Button)findViewById(R.id.rateButton);

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RateHouseActivity.class);
                startActivityForResult(i, 1);
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

        ListView reviewList = (ListView)findViewById(R.id.reviews_list);
        reviewList.setAdapter(new ReviewAdapter());
//        reviewList.setAdapter(new ReviewAdapter(DB, getApplicationContext()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK){
                String rating = data.getStringExtra("rating");
                String comment = data.getStringExtra("comment");
                ListView listView = (ListView)findViewById(R.id.reviews_list);
                ReviewAdapter adapter = (ReviewAdapter)listView.getAdapter();
                adapter.add(new ReviewData(100, "New review", rating, comment));
                adapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code for a canceled rating
            }
        }
    }

//    TODO: extract info from db and set
    private void extractAndSet() {
//        Get house name from db
        TextView houseName = (TextView)findViewById(R.id.houseName);
        houseName.setText("New house name");

//        Get rating from db
        TextView rating = (TextView)findViewById(R.id.houseRating);
        rating.setText("☆☆☆");

//        Get address from db
        TextView address = (TextView)findViewById(R.id.address_display_text);
        address.setText("1234");

    }

    private void startSlideShow() {
        //        Get pictures from db
        viewPager = (ViewPager)findViewById(R.id.slideshowViewPager);
        adapter = new ViewPagerAdapter(DisplayHouseActivity.this, images);
        viewPager.setAdapter(adapter);

    }
}
