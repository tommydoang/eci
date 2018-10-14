package com.example.tomz.electroniccity.adapter.cart;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.db.shop.DBShopListResponse;
import com.example.tomz.electroniccity.databinding.CartShopListItemBinding;
import com.example.tomz.electroniccity.page.details.cart.CartItemViewModel;
import com.example.tomz.electroniccity.utils.CommonUtils;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.util.ArrayList;
import java.util.List;

public class CartShopListAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<DBShopListResponse> mDbShopModel;
    private CustomTextViewLatoFont mTvQtyItem, mTvHargaNormal, mTvHargaPromo;
    private int qtyItem = 0, hargaNormalTemp = 0, hargaPromoTemp = 0, layoutPosition;
    private String hargaNormal, hargaPromo;
    private ArrayList<String> listHargaNormal = new ArrayList<>();
    private ArrayList<String> listHargaPromo = new ArrayList<>();
    private Button mBtnIncr, mBtndecr;

    public CartShopListAdapter(List<DBShopListResponse> mDbShopListResponseList) {
        this.mDbShopModel = mDbShopListResponseList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartShopListItemBinding mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.cart_shop_list_item, parent, false);
        mTvQtyItem = mBinding.tvItemQty;
//        mTvHargaNormal = mBinding.tvHargaProduk;
//        mTvHargaPromo = mBinding.tvPromoProduk;
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
        mTvHargaNormal = holder.itemView.findViewById(R.id.tvHargaProduk);
//        mTvHargaPromo = binding.tvPromoProduk;
    }

    @Override
    public int getItemCount() {
        if (mDbShopModel.size() > 0) {
            return mDbShopModel.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder implements
            CartItemViewModel.CartItemViewModelNavigatorListener {

        private CartShopListItemBinding binding;
        private CartItemViewModel cartItemViewModel;
        private DBShopListResponse dmi;

        BindingHolder(CartShopListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            layoutPosition = getLayoutPosition();
        }

        @Override
        public void onBind(int position) {
            dmi = mDbShopModel.get(position);
            qtyItem = dmi.getQtyItem();
//            hargaNormal = dmi.getHargaNormalProduk();
//            hargaPromo = dmi.getHargaPromoProduk();
            listHargaNormal.add( dmi.getHargaNormalProduk());
            Log.d("LHN tes1", listHargaNormal.size()+"");
            Log.d("dbShopMDL tes1", mDbShopModel.size()+"");
            cartItemViewModel = new CartItemViewModel(dmi,this);
            binding.setCart(cartItemViewModel);
            binding.executePendingBindings();
        }

        @Override
        public void onIncrClick(String harga) {
            qtyItem += 1;
            Log.d("onBtnClick tes1", qtyItem+"");
            mTvQtyItem.setText(String.valueOf(qtyItem));
//            for (int idx = 0; idx < listHargaNormal.size(); idx++){
//                hargaNormalTemp = Integer.parseInt(listHargaNormal.get(getLayoutPosition()))*qtyItem;
            Log.d("onIncrClick tes1", harga);
            hargaNormalTemp = Integer.parseInt(harga)*qtyItem;
                mTvHargaNormal.setText(CommonUtils.setCustomCurrency(String.valueOf(hargaNormalTemp)));
//                break;
//            }
            notifyItemChanged(getLayoutPosition());
//            hargaNormalTemp = Integer.parseInt(hargaNormal)*qtyItem;
//            mTvHargaNormal.setText(CommonUtils.setCustomCurrency(String.valueOf(hargaNormalTemp)));
        }

        @Override
        public void onDecrClick(String harga) {
            if (qtyItem > 1) {
                qtyItem -= 1;
                Log.d("onBtnClick tes1", qtyItem + "");
                mTvQtyItem.setText(String.valueOf(qtyItem));
//                for (int idx = 0; idx < listHargaNormal.size(); idx++){
//                    hargaNormalTemp = Integer.parseInt(listHargaNormal.get(getLayoutPosition()))*qtyItem;
                Log.d("onDecrClick tes1", harga);
                hargaNormalTemp = Integer.parseInt(harga)*qtyItem;
                    mTvHargaNormal.setText(CommonUtils.setCustomCurrency(String.valueOf(hargaNormalTemp)));
//                }
                notifyItemChanged(getLayoutPosition());
//                hargaNormalTemp = Integer.parseInt(hargaNormal)*qtyItem;
//                mTvHargaNormal.setText(CommonUtils.setCustomCurrency(String.valueOf(hargaNormalTemp)));
            } else {
                //delete from db according item
            }
        }
    }

    public void addItems(List<DBShopListResponse> blogList) {
        mDbShopModel.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mDbShopModel.clear();
    }

    public int getHargaNormalTemp(){
        return hargaNormalTemp;
    }

    public int getHargaPromoTemp() {
        return hargaPromoTemp;
    }

    public int getQtyItem(){
        return qtyItem;
    }
}
