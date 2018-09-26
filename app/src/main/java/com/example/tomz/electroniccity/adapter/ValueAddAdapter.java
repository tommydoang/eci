package com.example.tomz.electroniccity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.valueadded.DataValueAddResponse;

import java.util.List;

public class ValueAddAdapter extends BaseAdapter{

    private Context mCtx;
    private List<DataValueAddResponse> mValueModel;

    public ValueAddAdapter(Context ctx, List<DataValueAddResponse> mValueModel) {
        this.mCtx = ctx;
        this.mValueModel = mValueModel;
    }

    @Override
    public int getCount() {
        return mValueModel.size();
    }

    @Override
    public Object getItem(int i) {
        return mValueModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null){
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.value_added_item_layout, parent, false);
            holder.imageView = convertView.findViewById(R.id.ivAddedValue);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mCtx).asBitmap().load(mValueModel.get(position).getImage_value()).into(holder.imageView);
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
    }

}
