package com.example.sandhya.android.smartbeings_foodorder.requests;

import com.android.volley.VolleyError;

import java.util.Map;

public interface AppRequestListener {

        void onRequestStarted(String requestTag);

        void onRequestFailed(String requestTag, VolleyError error, boolean networkError);

        void onRequestCompleted(String requestTag, String response);

        void onRequestHeaders(Map<String, String> headers, String requestTag);
}
