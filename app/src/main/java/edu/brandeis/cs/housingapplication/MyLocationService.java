package edu.brandeis.cs.housingapplication;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
/**
 * Created by Kevin on 11/22/17.
 */

public class MyLocationService implements LocationListener {
    private static final String TAG = "COORDINATES: ";

    String coordinates;
    String latS;
    String longS;


    @Override
    public void onLocationChanged(Location location) {
        if (location!= null){
            this.coordinates = location.getLatitude() + "," + location.getLongitude();
            Log.e(TAG, "Latitude: " + location.getLatitude());
            Log.e(TAG, "Longitude: " + location.getLongitude());
        }
    }


    public String getCoordinates(){
        return coordinates;
    }


    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}
