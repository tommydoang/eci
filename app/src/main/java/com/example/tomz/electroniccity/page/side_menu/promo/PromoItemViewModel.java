package com.example.tomz.electroniccity.page.side_menu.promo;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.data.model.api.promo.DataPromoResponse;

public class PromoItemViewModel {

    public final ObservableField<String> idBanner;
    public final ObservableField<String> nameReceiver;
    public final ObservableField<String> imageURL;

    public PromoItemViewModel(DataPromoResponse dataPromoResponse) {
        idBanner = new ObservableField<>(dataPromoResponse.getId_banner());
        nameReceiver = new ObservableField<>(dataPromoResponse.getName());
        imageURL = new ObservableField<>(dataPromoResponse.getImage_banner());
    }

    @BindingAdapter({"imgPromo"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url).into(view);
    }

    public View.OnClickListener onReadMoreClicked() {
        return view -> Toast.makeText(view.getContext(), nameReceiver.get(), Toast.LENGTH_SHORT).show();
    }

}
