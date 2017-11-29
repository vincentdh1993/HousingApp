package edu.brandeis.cs.housingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import edu.brandeis.cs.housingapplication.login.SessionService;

//for location searching will change later
public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    BottomNavigationView bottomNavigationView;


    private SessionService sessionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.help_logo);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
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

//        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
//        }
//        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (checkLocationPermission()) {
//            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, this);
//        }

        // Intent intent = new Intent(this, UserListFragment.class);
        // this.startActivity(intent);
        //        Button for starting the PostHouseActivity
        //        startActivity(new Intent(this, PostHouseActivity.class));

//        startActivity(new Intent(this, SearchFragmentTabs.class));

//        startActivity(new Intent(this, DisplayHouseActivity.class));


        sessionService = new SessionService(this);
        if (sessionService.isSomeoneLoggedIn()) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

    }





}
