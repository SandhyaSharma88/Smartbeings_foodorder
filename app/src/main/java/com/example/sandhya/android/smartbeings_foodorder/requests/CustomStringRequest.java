package com.example.sandhya.android.smartbeings_foodorder.requests;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomStringRequest extends StringRequest {

    private final String url;
    private final String tag;
    private final String postPayload;
    private final HashMap<String, String> headers;

    private final AppRequestListener appRequestListener;

    private final long requestStartTime;


    public CustomStringRequest(int method,
                               String url,
                               String tag,
                               String postPayload,
                               HashMap<String, String> headers,
                               AppRequestListener appRequestListener)


    {
        super(method, url, null, null);


        this.url = url;
        this.tag = tag;
        this.postPayload = postPayload;
        this.headers = headers;
        this.appRequestListener = appRequestListener;

        //  DebugUtils.logD("REQUEST_START", tag + " " + url);
        requestStartTime = System.currentTimeMillis();

        if (appRequestListener != null) {
            appRequestListener.onRequestStarted(tag);
        }
    }

    @Override
    protected void deliverResponse(String response) {
        long difference = System.currentTimeMillis() - requestStartTime;
//            DebugUtils.logI("REQUEST_COMPLETE", tag + " " + "Request Complete in " + difference + " ms. URL = " + url);
//            DebugUtils.logV("REQUEST_RESPONSE", tag + " " + response);

        if (appRequestListener != null)
            appRequestListener.onRequestCompleted(tag, response);
    }

    @Override
    public void deliverError(VolleyError error) {

        String responseCode = "MY_DEFAULT_STRING (may be Network Error)";
        if (error != null && error.networkResponse != null)
            responseCode = error.networkResponse.statusCode + "";

//            long difference = System.currentTimeMillis() - requestStartTime;
//            DebugUtils.logE("REQUEST_FAIL", "Request Failed in " + difference + " ms. URL = " + url + " Code : " + responseCode + " Tag: " + tag);
//
//            if (error != null && error.networkResponse != null && error.networkResponse.data != null) {
//                String string = new String(error.networkResponse.data);
//                DebugUtils.logE("HOG_ERROR", string);
//            }


        boolean networkError = false;
        if (error == null || error.networkResponse == null) {
            networkError = true;
        } else if (error instanceof NoConnectionError) {
            networkError = true;
        } else if (error instanceof NetworkError) {
            networkError = true;
        } else if (error instanceof TimeoutError) {
            networkError = true;
        }

        if (appRequestListener != null)
            appRequestListener.onRequestFailed(tag, error, networkError);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            Map<String, String> headers = response.headers;

            if (appRequestListener != null)
                appRequestListener.onRequestHeaders(headers, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String utf8String;
        try {
            utf8String = new String(response.data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            utf8String = new String(response.data);
        }
        return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public Map<String, String> getHeaders() {
        return headers != null ? headers : Collections.<String, String>emptyMap();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (postPayload == null) {
            return super.getBody();
        } else {
            try {
                return postPayload.getBytes("UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                return super.getBody();
            }
        }
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

}

