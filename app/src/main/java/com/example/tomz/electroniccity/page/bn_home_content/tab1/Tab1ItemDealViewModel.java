package com.example.tomz.electroniccity.page.bn_home_content.tab1;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataDealTab1Response;

public class Tab1ItemDealViewModel {

    public final ObservableField<String> imageDealUrl;
    public final ObservableField<String> nameDeal;

    public Tab1ItemDealViewModel(DataDealTab1Response dataDealTab1Response) {
        imageDealUrl = new ObservableField<>(dataDealTab1Response.getImage_banner());
        nameDeal = new ObservableField<>(dataDealTab1Response.getName());
    }

    @BindingAdapter({"imageViewDeal"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url).into(view);
    }

    public View.OnClickListener onReadMoreClicked() {
        return view -> Toast.makeText(view.getContext(), nameDeal.get(), Toast.LENGTH_SHORT).show();
    }


}
