package com.example.sandhya.android.smartbeings_foodorder.utils;

import android.support.annotation.NonNull;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.Volley;
import com.example.sandhya.android.smartbeings_foodorder.application.AppApplication;
import com.example.sandhya.android.smartbeings_foodorder.requests.CustomStringRequest;
import com.google.gson.Gson;

public class VolleyManager {

    private static VolleyManager sInstance;

    private RequestQueue REQUEST_QUEUE;

    private VolleyManager() {

    }

    public static VolleyManager getInstance() {
        if (sInstance == null)
            sInstance = new VolleyManager();

        return sInstance;
    }

    private RequestQueue getRequestQueue() {
        if (REQUEST_QUEUE == null)
            REQUEST_QUEUE = Volley.newRequestQueue(AppApplication.getInstance());

        return REQUEST_QUEUE;
    }

    public void addToRequestQueue(CustomStringRequest customStringRequest, @NonNull String tag) {
        RetryPolicy retryPolicy = new DefaultRetryPolicy(8 * 1000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        addToRequestQueue(customStringRequest, tag, retryPolicy);
    }

    private void addToRequestQueue(CustomStringRequest customStringRequest, @NonNull String tag, RetryPolicy retryPolicy) {
        customStringRequest.setTag(tag);
        customStringRequest.setShouldCache(true);
        customStringRequest.setRetryPolicy(retryPolicy);
        getRequestQueue().add(customStringRequest);
    }

    public Object getResponseObject(String response, Class objectClass) {
        try {
            return new Gson().fromJson(response, objectClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getJsonString(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getResponseStringFromCache(String url) {
        try {
            Cache.Entry entry = getRequestQueue().getCache().get(url);
            if (entry != null)
                return new String(entry.data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearAllVolleyCache() {
        try {
            getRequestQueue().getCache().clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
