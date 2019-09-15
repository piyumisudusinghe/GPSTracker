package com.piyumi.locationtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class  MainActivity extends AppCompatActivity {

    Button btnGetLocation;

    Button btnStop;

    public static boolean isPressedStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetLocation = (Button)findViewById(R.id.btnGetLocation);
        btnStop = (Button)findViewById(R.id.btnStop);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        },123);

        btnGetLocation.setOnClickListener( new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                isPressedStop = false;
                GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
                Location location = gpsTracker.getLocation();
                if(location != null){
                    Toast.makeText(getApplicationContext(),"Start getting location updates", Toast.LENGTH_LONG).show();
                    double lat = location.getLatitude();
                    double lon = location.getLongitude();
                    Toast.makeText(getApplicationContext(),"LAT : " + lat + "\n LON : " + lon, Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(getApplicationContext(),"Location null piyumi", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPressedStop = true;
                Toast.makeText(getApplicationContext(),"Stopped getting location updates", Toast.LENGTH_LONG).show();

            }
        });
    }
}
