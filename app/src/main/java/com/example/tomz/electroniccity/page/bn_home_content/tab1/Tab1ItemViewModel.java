package com.example.tomz.electroniccity.page.bn_home_content.tab1;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;
import com.example.tomz.electroniccity.page.details.checkout.ProductDetail;
import com.example.tomz.electroniccity.utils.CommonUtils;

public class Tab1ItemViewModel {

    public final ObservableField<String> imageUrl;
    public final ObservableField<String> nameProd;
    public final ObservableField<String> realPrice;
    private DataProductTab1Response productTab1Response;

    public Tab1ItemViewModel(DataProductTab1Response dataModelProd) {
        this.productTab1Response = dataModelProd;
        imageUrl = new ObservableField<>(dataModelProd.getImg_thumb());
        nameProd = new ObservableField<>(dataModelProd.getName_prod());
        realPrice = new ObservableField<>(CommonUtils.setCustomCurrency(dataModelProd.getReal_price()));
    }

    @BindingAdapter({"imageViewBerak"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    public View.OnClickListener onReadMoreClicked() {
        return (View view) -> {
            Intent in = new Intent(view.getContext(), ProductDetail.class);
            in.putExtra("TAG_ID_PROD",productTab1Response.getId_prod());
            in.putExtra("TAG_IMG_PROD", productTab1Response.getImg_best());
            in.putExtra("TAG_NAMA_PROD", productTab1Response.getName_prod());
            in.putExtra("TAG_SKU_PROD", productTab1Response.getSku());
            in.putExtra("TAG_NORMAL_PRICE", productTab1Response.getReal_price());
            in.putExtra("TAG_SPC_PRICE", productTab1Response.getSpc_price());
            in.putExtra("TAG_PROD_DESC", productTab1Response.getProduct_description()); //deskripsi produk
            in.putExtra("TAG_LONG_DESC", productTab1Response.getLong_description()); //spesifikasi produk
            view.getContext().startActivity(in);
        };
    }

}
