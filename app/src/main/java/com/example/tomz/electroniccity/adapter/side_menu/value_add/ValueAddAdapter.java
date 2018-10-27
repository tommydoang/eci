package com.example.tomz.electroniccity.adapter.side_menu.value_add;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.valueadded.DataValueAddResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ValueAddAdapter extends BaseAdapter{

    private Context mCtx;
    private ArrayList<Bitmap> bitmapArrayList;
    private List<DataValueAddResponse> mValueModel;

    public ValueAddAdapter(Context ctx, List<DataValueAddResponse> mValueModel) {
        this.mCtx = ctx;
        this.mValueModel = mValueModel;
        bitmapArrayList = new ArrayList<>();
    }

    public ValueAddAdapter(Context ctx, ArrayList<Bitmap> arrBmp){
        this.mCtx = ctx;
        this.bitmapArrayList = arrBmp;
        mValueModel = new ArrayList<>();
    }

    @Override
    public int getCount() {
        if (mValueModel.size() > 0) {
            return mValueModel.size();
        } else {
            return bitmapArrayList.size();
        }
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            holder.imageView.setImageBitmap(getBitmapFromURL(mValueModel.get(position).getImage_value()));
        } else {
            holder.imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmapArrayList.get(position),
                    (int)(bitmapArrayList.get(position).getWidth()*2.4),
                    (int)(bitmapArrayList.get(position).getHeight()*2.4), true));
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return Bitmap.createScaledBitmap(myBitmap,
                    (int)(myBitmap.getWidth()*2.4), (int)(myBitmap.getHeight()*2.4), true);
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
