package edu.brandeis.cs.housingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    }
}
