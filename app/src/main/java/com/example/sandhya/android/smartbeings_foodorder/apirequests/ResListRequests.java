package com.example.sandhya.android.smartbeings_foodorder.apirequests;

import android.content.Context;

import com.android.volley.Request;
import com.example.sandhya.android.smartbeings_foodorder.constants.RequestTags;
import com.example.sandhya.android.smartbeings_foodorder.requests.AppRequestListener;
import com.example.sandhya.android.smartbeings_foodorder.requests.CustomStringRequest;
import com.example.sandhya.android.smartbeings_foodorder.urls.AppUrls;
import com.example.sandhya.android.smartbeings_foodorder.utils.VolleyManager;

public class ResListRequests extends BaseApiRequest {

    public static void makeListPageRequest(Context context, AppRequestListener appRequestListener, String location, Double lat, Double lon) {
        String url;
        if (location != null) {
            url = AppUrls.BASE_URL + "" + "search?entity_type=city&q=" + location;
        } else {
            url = AppUrls.BASE_URL + "" + "search?lat=" + lat + "&lon=" + lon;
        }
        try {

            CustomStringRequest request = new CustomStringRequest(Request.Method.GET, url, RequestTags.TAG_LIST_PAGE, null, getHeaders(context), appRequestListener);
            VolleyManager.getInstance().addToRequestQueue(request, RequestTags.TAG_LIST_PAGE);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}

