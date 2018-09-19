package com.example.tomz.electroniccity.adapter.tab7;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab6.DataBannerTab6Response;
import com.example.tomz.electroniccity.data.model.api.products.tab7.DataBannerTab7Response;

import java.util.List;

public class Tab7BannerAdapter extends PagerAdapter {

    private Context ctx;
    private ImageView ivBanner;
    private List<DataBannerTab7Response> bannerTab7ResponseList;

    public Tab7BannerAdapter(Context ctx, List<DataBannerTab7Response> bannerTab7ResponsesList) {
        this.ctx = ctx;
        this.bannerTab7ResponseList = bannerTab7ResponsesList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bannerTab7ResponseList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = null;
        if (inflater != null) {
            view = inflater.inflate(R.layout.tab7_banner_image, container, false);
            ivBanner = view.findViewById(R.id.image_display);
        }
        Glide.with(ctx).asBitmap().load(bannerTab7ResponseList.get(position).getImage_mobile())
                .apply(new RequestOptions().override(495, 224).transform(new FitCenter()))
                .into(ivBanner);
        container.addView(view);
        //noinspection ConstantConditions
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
