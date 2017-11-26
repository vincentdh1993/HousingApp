package edu.brandeis.cs.housingapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.marvinlabs.widget.slideshow.SlideShowAdapter;
import com.marvinlabs.widget.slideshow.SlideShowView;
import com.marvinlabs.widget.slideshow.adapter.ResourceBitmapAdapter;

import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by eureyuri on 2017/11/26.
 */

public class DisplayHouseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_house);

        Log.w("DisplayHouse", "Before set");

        extractAndSet();
        startSlideShow();

        Log.w("DisplayHouse", "After set");

        Button rateButton = (Button)findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent());
            }
        });

        Button contactButton = (Button)findViewById(R.id.contactButton);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "123-456-789";
                startActivity(new Intent(Intent.ACTION_DIAL,
                        Uri.fromParts("tel", phoneNumber, null)));
            }
        });
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
        SlideShowView slideShowView = (SlideShowView)findViewById(R.id.slideshow);
        slideShowView.setAdapter(createAdapter());

        slideShowView.play();

    }

    private SlideShowAdapter createAdapter() {
        int[] slideResource = new int[]{R.raw.slide1, R.raw.slide2};
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inSampleSize = 2;
        ResourceBitmapAdapter adapter = new ResourceBitmapAdapter(this, slideResource, opt);

        return adapter;
    }
}
