package com.example.tomz.electroniccity.page.bn_home_content.tab3;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.data.model.api.products.tab3.DataProductTab3Response;

import java.text.NumberFormat;
import java.util.Locale;

public class Tab3ItemViewModel {

    public final ObservableField<String> imageUrl;
    public final ObservableField<String> nameProd;
    public final ObservableField<String> realPrice;

    public Tab3ItemViewModel(DataProductTab3Response dataModelProd) {
        imageUrl = new ObservableField<>(dataModelProd.getImg_thumb());
        nameProd = new ObservableField<>(dataModelProd.getName_prod());
        realPrice = new ObservableField<>(setCustomCurrency(dataModelProd.getReal_price()));
    }

    @BindingAdapter({"ivProduct"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url).into(view);
    }

    public View.OnClickListener onReadMoreClicked() {
        return view -> Toast.makeText(view.getContext(), nameProd.get(), Toast.LENGTH_SHORT).show();
    }

    private String setCustomCurrency(String price){
        String formattedCurr;
        Locale localeID = new Locale("in", "ID");
        NumberFormat currencyFormat = NumberFormat.getInstance(localeID);
        formattedCurr = "Rp " + currencyFormat.format(Double.parseDouble(price));
        return formattedCurr;
    }
}
