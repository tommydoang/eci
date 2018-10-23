package com.example.tomz.electroniccity.adapter.home_tab.tab1;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;
import com.example.tomz.electroniccity.databinding.Tab1ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab1.Tab1ItemViewModel;
import com.example.tomz.electroniccity.utils.CommonUtils;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.util.List;

public class Tab1Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab1Response> mTab1Model;

    public Tab1Adapter(List<DataProductTab1Response> mTab1Model) {
        this.mTab1Model = mTab1Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab1ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab1_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab1Model.size() > 0) {
            return mTab1Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab1ItemBestSellerBinding binding;
        private Tab1ItemViewModel tab1ItemViewModel;
        private CustomTextViewLatoFont tvNormalPrice, tvSpcPrice, tvDiscount;

        BindingHolder(Tab1ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            tvSpcPrice = binding.tvSpcPrice;
            tvNormalPrice = binding.tvNormalPrice;
            tvDiscount = binding.tvDiscount;
            if (!mTab1Model.get(position).getSpc_price().isEmpty()){
                tvDiscount.setTextColor(Color.RED);
                tvNormalPrice.setTextColor(Color.RED);
                tvNormalPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
                tvDiscount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
                tvDiscount.setText(String.valueOf((int)CommonUtils.countToDisc(mTab1Model.get(position).getReal_price(),
                        mTab1Model.get(position).getSpc_price())+"%"));
                tvNormalPrice.setPaintFlags(tvNormalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                tvNormalPrice.setTextColor(Color.GRAY);
                tvSpcPrice.setVisibility(View.GONE);
            }

            DataProductTab1Response dmi = mTab1Model.get(position);
            tab1ItemViewModel = new Tab1ItemViewModel(dmi);
            binding.setTab1(tab1ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab1Response> blogList) {
        mTab1Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab1Model.clear();
    }


}
