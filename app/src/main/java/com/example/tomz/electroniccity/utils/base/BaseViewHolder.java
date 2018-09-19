package com.example.tomz.electroniccity.utils.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tomz on 01/17/18.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);
}
