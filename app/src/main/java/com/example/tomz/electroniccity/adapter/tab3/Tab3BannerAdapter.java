package com.example.tomz.electroniccity.adapter.tab3;

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
import com.example.tomz.electroniccity.data.model.api.products.tab2.DataBannerTab2Response;
import com.example.tomz.electroniccity.data.model.api.products.tab3.DataBannerTab3Response;

import java.util.List;

public class Tab3BannerAdapter extends PagerAdapter {

    private Context ctx;
    private ImageView ivBanner;
    private List<DataBannerTab3Response> bannerTab3ResponseList;

    public Tab3BannerAdapter(Context ctx, List<DataBannerTab3Response> bannerTab3ResponsesList) {
        this.ctx = ctx;
        this.bannerTab3ResponseList = bannerTab3ResponsesList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bannerTab3ResponseList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = null;
        if (inflater != null) {
            view = inflater.inflate(R.layout.tab3_banner_image, container, false);
            ivBanner = view.findViewById(R.id.image_display);
        }
        Glide.with(ctx).asBitmap().load(bannerTab3ResponseList.get(position).getImage_mobile())
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
