package com.example.test_app_basketball;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

public class GPS_Cordinates extends AppCompatActivity implements LocationListener {

    TextView textView;
    Double Latitude = 0.0;
    Double Longitude = 0.0;
    LocationManager locationManager;
    Intent GPS, Video, Registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_cordinates);
        Registration = new Intent(this, Registration.class);
        Video = new Intent(this, Video.class);
        GPS = new Intent(this, GPS_Cordinates.class);

        textView = findViewById(R.id.textView1);
        textView.setText("searching...");

        // Check for location permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);
        } else {
            getLocation();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();

        if (st.equals("Registration")) {
            startActivity(Registration);
        } else if (st.equals("Video")) {
            startActivity(Video);
        } else if (st.equals("GPS_SPOT")) {
            startActivity(GPS);
        } else if(st.equals("Sign_Places")){
             Intent intent = new Intent(this, Sign_Places.class);
             startActivity(intent);
        }else if(st.equals("Signs2")){
             Intent intent = new Intent(this, Signs2.class);
             startActivity(intent);
        }

        return super.onContextItemSelected(item);
    }
//getApplicationContext().
    public void getLocation() {
        try {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION" , "android.permission.ACCESS_COARSE_LOCATION"}, 1);
            }
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (locationManager != null) {
                // Request location updates from both GPS and Network providers
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3, this);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 3, this);

                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                        .addOnSuccessListener(location -> {
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();
                                textView.setText("Latitude: " + latitude + "\nLongitude: " + longitude);
                            }
                        });

            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Latitude = location.getLatitude();
        Longitude = location.getLongitude();
        // Update TextView with new coordinates
        textView.setText("Latitude: " + Latitude + "\nLongitude: " + Longitude);
        Toast.makeText(this, "Changed Location", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Deprecated method. You can leave it empty.
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        // Optionally, handle the provider being enabled.
        Toast.makeText(this, provider + " enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        // Optionally, handle the provider being disabled.
        Toast.makeText(this, provider + " disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                textView.setText("Permission Denied. Cannot access location.");
            }
        }
    }
}
