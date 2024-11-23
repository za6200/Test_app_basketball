package com.example.test_app_basketball;
import com.example.test_app_basketball.databinding.ActivityFindCourtsBinding;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Find_Courts extends FragmentActivity implements
        OnMapReadyCallback
         {

    private GoogleMap mMap;
    private ActivityFindCourtsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(Find_Courts.this, Registration.class);
        startActivity(intent);

        binding = ActivityFindCourtsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Error in loading map fragment", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mMap != null) {
            // Set the map type to normal
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            searchBasketBallCourts();
            LatLng israel = new LatLng(39.445, -91.634); // Coordinates for Israel
            mMap.addMarker(new MarkerOptions().position(israel).title("Marker in Israel"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(israel, 14f));
        } else {
            Toast.makeText(getApplicationContext(), "mMap is null", Toast.LENGTH_SHORT).show();
        }
    }
     @Override
         public boolean onOptionsItemSelected(@NonNull MenuItem item) {
             String st = item.getTitle().toString();

             if (st.equals("Registration")) {
                 Intent intent = new Intent(this, Registration.class);
                 startActivity(intent);
             } else if (st.equals("Video")) {
                 Intent intent = new Intent(this, Video.class);
                 startActivity(intent);
             } else if (st.equals("GPS_SPOT")) {
                 Intent intent = new Intent(this, GPS_Cordinates.class);
                 startActivity(intent);
             } else if(st.equals("Sign_Places")){
             /*Intent intent = new Intent(this, Sign_Places.class);
             startActivity(intent);*/
             }else if(st.equals("Signs2")){
             /*Intent intent = new Intent(this, Signs2.class);
             startActivity(intent);*/
             }

             return super.onContextItemSelected(item);
         }
     public void searchBasketBallCourts(){

     }
}