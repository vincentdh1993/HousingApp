package edu.brandeis.cs.housingapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.brandeis.cs.housingapplication.adapters.ReviewAdapter;
import edu.brandeis.cs.housingapplication.domainmodels.Rating;
import edu.brandeis.cs.housingapplication.login.SessionService;
import edu.brandeis.cs.housingapplication.utils.NetworkUtils;


public class UserProfileActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private static final int RATINGS_RESULTS_LOADER = 1;
    private static final String SEARCH_QUERY = "query";

    private ListView myRatings;
    private SessionService sessionService;
    private List<Rating> ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        this.sessionService = new SessionService(this);
        myRatings = (ListView)findViewById(R.id.list_reviewsWritten);
        myRatings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });
        getRatings();
    }

    private void getRatings() {
        URL url = NetworkUtils.createUrl("users", SessionService.CURRENT_USER_ID, "ratingsWritten");
        Log.d("GETRATINGS URL", url.toString());
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY, url.toString());
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> ratingsLoader = loaderManager.getLoader(RATINGS_RESULTS_LOADER);
        if (ratingsLoader == null) {
            //this is what kicks off the loading process
            loaderManager.initLoader(RATINGS_RESULTS_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(RATINGS_RESULTS_LOADER, queryBundle, this);
        }

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            public String loadInBackground() {
                String getRatingString = args.getString(SEARCH_QUERY);
                try {
                    URL getRatings = new URL(getRatingString);
                    String results = NetworkUtils.doHttpGet(getRatings);
                    return results;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onStartLoading() {
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Rating>> mapType = new TypeReference<List<Rating>>() {};
        List<Rating> results = new ArrayList<>();
        try {
            results = mapper.readValue(data, mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.ratings = results;
        this.myRatings.setAdapter(new ReviewAdapter(ratings));
        setListViewHeightBasedOnChildren(myRatings);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        //do nothing
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

            case R.id.menu_logout:
                this.sessionService.logout();
                startActivity(new Intent(this, LoginActivity.class));
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
