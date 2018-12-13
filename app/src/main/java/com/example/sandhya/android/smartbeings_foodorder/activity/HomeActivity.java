package com.example.sandhya.android.smartbeings_foodorder.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.sandhya.android.smartbeings_foodorder.R;
import com.example.sandhya.android.smartbeings_foodorder.requests.AppRequestListener;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity implements LocationListener, AppRequestListener {

    @BindView(R.id.user_location)
    EditText user_location;

    @BindView(R.id.location_search)
    Button location_search;

    @BindView(R.id.location_auto)
    Button location_auto;

    LocationManager locationManager;

    Double latitude;
    Double longitude;

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 125;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        location_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRestaurantsListActivity(user_location.getText().toString(),28.457523,77.026344);
            }
        });

        location_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                  Toast.makeText(HomeActivity.this, " You already have the permission", Toast.LENGTH_SHORT).show();
                }
                else{
                    requestStoragePermission();
                }
                getLocation();
            }
        });
    }

    private void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            new AlertDialog.Builder(this).setTitle("Permission Needed").setMessage("This permission is needed to do this").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(HomeActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(HomeActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        openRestaurantsListActivity(user_location.getText().toString(), latitude, longitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(HomeActivity.this, "Please enable location services", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestStarted(String requestTag) {

    }

    @Override
    public void onRequestFailed(String requestTag, VolleyError error, boolean networkError) {

    }

    @Override
    public void onRequestCompleted(String requestTag, String response) {

    }

    @Override
    public void onRequestHeaders(Map<String, String> headers, String requestTag) {

    }

}

