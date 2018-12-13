package com.example.sandhya.android.smartbeings_foodorder.apirequests;

import android.content.Context;

import com.example.sandhya.android.smartbeings_foodorder.constants.RequestTags;

import java.util.HashMap;

public class BaseApiRequest {

    static HashMap<String, String> getHeaders(Context context) {

        String AUTH_TOKEN_HEADER = "user-key";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(AUTH_TOKEN_HEADER, RequestTags.TAG_API_KEY);
        return headers;

    }

}
