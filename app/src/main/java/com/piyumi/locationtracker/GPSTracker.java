package com.piyumi.locationtracker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class GPSTracker implements LocationListener {
    Context context;

    public GPSTracker(Context c){
        this.context = c;

    }



    public Location getLocation(){

        //check user is given permission or not
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            Toast.makeText(context,"Permission not granted",Toast.LENGTH_SHORT).show();
            return null;
        }

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //check GPS is enabled or not
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if(isGPSEnabled){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000, 1,this);

            if(locationManager != null){
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                return location;
            }

        }else{
            Toast.makeText( context,"Please enable GPS",Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        if(!MainActivity.isPressedStop){
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            Toast.makeText(context,"LAT : " + lat + "\n LON : " + lon, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
