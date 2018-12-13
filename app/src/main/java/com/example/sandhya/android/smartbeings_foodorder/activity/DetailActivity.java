package com.example.sandhya.android.smartbeings_foodorder.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.volley.VolleyError;
import com.example.sandhya.android.smartbeings_foodorder.R;
import com.example.sandhya.android.smartbeings_foodorder.requests.AppRequestListener;

import java.util.Map;

public class DetailActivity extends BaseActivity implements AppRequestListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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
