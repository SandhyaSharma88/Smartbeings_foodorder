package com.example.sandhya.android.smartbeings_foodorder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public void openRestaurantsListActivity(String location, Double latitude, Double longitude){
        Intent i = new Intent(BaseActivity.this, RestaurantsListActivity.class);
        i.putExtra(RestaurantsListActivity.KEY_LOCATION, location);
        i.putExtra(RestaurantsListActivity.KEY_LATITUDE, latitude);
        i.putExtra(RestaurantsListActivity.KEY_LONGITUDE, longitude);
        startActivity(i);
    }

    public void openDetailActivity(){
        Intent i = new Intent(BaseActivity.this, DetailActivity.class);
        startActivity(i);
    }
}
