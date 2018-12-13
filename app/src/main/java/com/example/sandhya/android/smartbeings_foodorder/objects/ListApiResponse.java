package com.example.sandhya.android.smartbeings_foodorder.objects;

import com.example.sandhya.android.smartbeings_foodorder.utils.VolleyManager;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListApiResponse {

    @SerializedName("restaurants")
    private List<Restaurants> restaurants = null;

    public static ListApiResponse getObject(String res) {
        return (ListApiResponse) VolleyManager.getInstance().getResponseObject(res, ListApiResponse.class);
    }

    public List<Restaurants> getRestaurants() {
        return restaurants;
    }

    public static class Restaurants{
        @SerializedName("restaurant")
        private Restaurant restaurant;

        public Restaurant getRestaurant() {
            return restaurant;
        }
    }

    public static class Restaurant {

        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        @SerializedName("featured_image")
        private String featuredImage;

        @SerializedName("user_rating")
        private UserRating userRating;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFeaturedImage() {
            return featuredImage;
        }

        public void setFeaturedImage(String featuredImage) {
            this.featuredImage = featuredImage;
        }

        public UserRating getUserRating() {
            return userRating;
        }

        public void setUserRating(UserRating userRating) {
            this.userRating = userRating;
        }
        public static class UserRating{
            @SerializedName("aggregate_rating")
            private String aggregateRating;

            public String getAggregateRating() {
                return aggregateRating;
            }

            public void setAggregateRating(String aggregateRating) {
                this.aggregateRating = aggregateRating;
            }
        }
    }
}
