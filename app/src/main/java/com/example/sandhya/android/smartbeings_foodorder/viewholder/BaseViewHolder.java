package com.example.sandhya.android.smartbeings_foodorder.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public void setViewHolderVisibility(boolean isVisible) {
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) itemView.getLayoutParams();
        if (isVisible) {
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;
            itemView.setVisibility(View.VISIBLE);
        } else {
            itemView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        }
        itemView.setLayoutParams(param);
    }
}
