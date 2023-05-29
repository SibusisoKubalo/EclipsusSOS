package com.example.eclipsussos;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.eclipsussos.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.eclipsussos.databinding.ActivityEmergencyBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class Emergency_Activity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityEmergencyBinding binding;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    double lat,lng;
    public
    Button sosbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEmergencyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        sosbtn = findViewById(R.id.SOS_btn);

        fusedLocationProviderClient=
                LocationServices.getFusedLocationProviderClient(this.getApplicationContext());
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:08600 10111"));
                startActivity(intent);

            }
        });

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        getCurrentLocation();
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation(){
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions
                    (this ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_code);
            return;
        }
        LocationRequest locationRequest= LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(50000);
        LocationCallback locationCallback= new LocationCallback(){
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                Toast.makeText(getApplicationContext(), "location result is=" + locationResult,
                        Toast.LENGTH_SHORT).show();

                if(locationResult== null){
                    Toast.makeText(getApplicationContext(),"Current location is null"
                            ,Toast.LENGTH_LONG).show();
                    return;
                }
                for(Location location:locationResult.getLocations()){

                    if(location!=null){
                        Toast.makeText(getApplicationContext(), "Current location" +location.getLongitude()
                                , Toast.LENGTH_SHORT).show();


                    }
                }
            }
        };

        fusedLocationProviderClient.requestLocationUpdates
                (locationRequest, locationCallback, null);

        Task<Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    lat = location.getLatitude();
                    lng = location.getLongitude();

                    LatLng latLng = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(latLng).title("current Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                }
            }
        });


    }



    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (Request_code){
            case Request_code:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }
        }
    }


}