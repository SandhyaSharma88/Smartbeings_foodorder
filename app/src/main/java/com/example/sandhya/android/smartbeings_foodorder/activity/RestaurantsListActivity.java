package com.example.sandhya.android.smartbeings_foodorder.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.sandhya.android.smartbeings_foodorder.R;
import com.example.sandhya.android.smartbeings_foodorder.adapter.ResListAdapter;
import com.example.sandhya.android.smartbeings_foodorder.apirequests.ResListRequests;
import com.example.sandhya.android.smartbeings_foodorder.constants.RequestTags;
import com.example.sandhya.android.smartbeings_foodorder.objects.ListApiResponse;
import com.example.sandhya.android.smartbeings_foodorder.requests.AppRequestListener;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantsListActivity extends BaseActivity implements AppRequestListener {

    public static final String KEY_LOCATION = "KEY_LOCATION";
    public static final String KEY_LATITUDE = "KEY_LATITUDE";
    public static final String KEY_LONGITUDE = "KEY_LONGITUDE";

    @BindView(R.id.list_recycler_view)
    RecyclerView list_recycler_view;

    String location;
    Double lat;
    Double lon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantslist);

        ButterKnife.bind(this);

        try {
            if (getIntent().getStringExtra(KEY_LOCATION) != null) {
                location = getIntent().getStringExtra(KEY_LOCATION);
            } else {
                lat = getIntent().getDoubleExtra(KEY_LATITUDE, lat);
                lon = getIntent().getDoubleExtra(KEY_LATITUDE, lon);
            }
            loadData(location,lat,lon);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void loadData(String location, Double lat, Double lon) {
        ResListRequests.makeListPageRequest(this, this, location,lat,lon);
    }

    @Override
    public void onRequestStarted(String requestTag) {

    }

    @Override
    public void onRequestFailed(String requestTag, VolleyError error, boolean networkError) {

    }

    @Override
    public void onRequestCompleted(String requestTag, String response) {
        if (requestTag.equals(RequestTags.TAG_LIST_PAGE)) {
            handleListPageResponse(response);
        }
    }


    private void handleListPageResponse(String response) {

        ListApiResponse listAPIResponse = ListApiResponse.getObject(response);


        //always do null checks on API response

        if (listAPIResponse != null

                && listAPIResponse.getRestaurants() != null

                && !listAPIResponse.getRestaurants().isEmpty()) {


            final ResListAdapter resListAdapter = new ResListAdapter(listAPIResponse.getRestaurants());

            list_recycler_view.setLayoutManager(new LinearLayoutManager(this));

            list_recycler_view.setAdapter(resListAdapter);

        } else {

            Toast.makeText(this,"API response is either null or empty",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestHeaders(Map<String, String> headers, String requestTag) {

    }
}
