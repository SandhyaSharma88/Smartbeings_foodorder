package com.example.sandhya.android.smartbeings_foodorder.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sandhya.android.smartbeings_foodorder.R;
import com.example.sandhya.android.smartbeings_foodorder.activity.BaseActivity;
import com.example.sandhya.android.smartbeings_foodorder.objects.ListApiResponse;
import com.example.sandhya.android.smartbeings_foodorder.viewholder.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ListApiResponse.Restaurant> restaurantsList;

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    public ResListAdapter(List<ListApiResponse.Restaurant> restaurantsList) {
        this.restaurantsList = restaurantsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RestaurantsListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.restaurants_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        RestaurantsListViewHolder restaurantsListViewHolder = (RestaurantsListViewHolder) viewHolder;
        restaurantsListViewHolder.setData(restaurantsList.get(i));
    }

    @Override
    public int getItemCount() {
        if (restaurantsList == null)
            return 0;
        else
            return restaurantsList.size();
    }


    public static class RestaurantsListViewHolder extends BaseViewHolder {
        @BindView(R.id.res_photo)
        ImageView res_photo;

        @BindView(R.id.res_name)
        TextView res_name;

        @BindView(R.id.res_rating)
        TextView res_rating;

        private ListApiResponse.Restaurant restaurantData;

        public RestaurantsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onItemClick(){
            if (itemView.getContext() instanceof BaseActivity)
                ((BaseActivity) itemView.getContext()).openDetailActivity();
        }

        private void setData(ListApiResponse.Restaurant restaurantArg) {
            this.restaurantData = restaurantArg;

            Glide.with(res_photo.getContext())
                    .load(restaurantData.getFeaturedImage())
                    .apply(new RequestOptions().fitCenter())
                    .into(res_photo);

            res_name.setText(restaurantData.getName());
            res_rating.setText(restaurantData.getUserRating().getAggregateRating());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
     }
    }
