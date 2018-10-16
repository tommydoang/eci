package com.example.tomz.electroniccity.page.details.cart;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.db.shop.DBShopListResponse;
//import com.example.tomz.electroniccity.databinding.CartShopListItemBinding;
import com.example.tomz.electroniccity.utils.CommonUtils;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

public class CartItemViewModel {

    public final ObservableField<String> imgProd;
    public final ObservableField<String> nameProd;
    public final ObservableField<String> skuProd;
    public final ObservableField<String> hargaNormalProd;
    public final ObservableField<String> hargaPromoProd;
    public final ObservableField<String> qtyItem;
    private final DBShopListResponse mDbShopListResponse;
    private final CartItemViewModelNavigatorListener mListener;

    public CartItemViewModel(DBShopListResponse dbShopListResponse,
                             CartItemViewModelNavigatorListener listener) {
        this.mListener = listener;
        this.mDbShopListResponse = dbShopListResponse;
        imgProd = new ObservableField<>(dbShopListResponse.getImageProduk());
        nameProd = new ObservableField<>(dbShopListResponse.getNameProduk());
        skuProd = new ObservableField<>(dbShopListResponse.getSkuProduk());
        hargaNormalProd = new ObservableField<>(CommonUtils
                .setCustomCurrency(dbShopListResponse.getHargaNormalProduk()));
        hargaPromoProd = new ObservableField<>(dbShopListResponse.getHargaPromoProduk());
        qtyItem = new ObservableField<>(String.valueOf(dbShopListResponse.getQtyItem()));
    }

    @BindingAdapter({"imgViewProd"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    public void onIncreaseQty(){
        mListener.onIncrClick(mDbShopListResponse.getQtyItem(),
                mDbShopListResponse.getHargaNormalProduk());
    }

    public void onDecreaseQty() {
        mListener.onDecrClick(mDbShopListResponse.getQtyItem(),
                mDbShopListResponse.getHargaNormalProduk());
    }

    public void onDeleteItem(){
        mListener.onDelClick(Integer.parseInt(mDbShopListResponse.getId_prod()));
    }

    public interface CartItemViewModelNavigatorListener {
        void onIncrClick(int qty, String harga);
        void onDecrClick(int qty, String harga);
        void onDelClick(int id_prod);
    }

}
