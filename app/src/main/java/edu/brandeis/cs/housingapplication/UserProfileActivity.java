package edu.brandeis.cs.housingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import edu.brandeis.cs.housingapplication.adapters.ReviewAdapter;


public class UserProfileActivity extends AppCompatActivity {
    private ListView myRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        myRatings = (ListView)findViewById(R.id.list_reviewsWritten);
        myRatings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });
        myRatings.setAdapter(new ReviewAdapter());
        setListViewHeightBasedOnChildren(myRatings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Search:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    private void setListViewHeightBasedOnChildren(ListView myRatings) {
        ListAdapter adapter = myRatings.getAdapter();
        if (adapter == null) {
            return;
        }
        int width = View.MeasureSpec.makeMeasureSpec(myRatings.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View v = null;
        for (int i = 0; i < adapter.getCount(); i++) {
            v = adapter.getView(i, v, myRatings);
            if (i == 0) {
                v.setLayoutParams(new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            v.measure(width, View.MeasureSpec.UNSPECIFIED);
            totalHeight += v.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = myRatings.getLayoutParams();
        params.height = totalHeight + (myRatings.getDividerHeight() * (adapter.getCount() - 1));
        myRatings.setLayoutParams(params);
        Log.d("TOTAL HEIGHT", Integer.toString(totalHeight));
        Log.d("PARAMS", Integer.toString(params.height));

    }
}
