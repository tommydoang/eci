package com.example.tomz.electroniccity.adapter.home_tab.tab1;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;
import com.example.tomz.electroniccity.databinding.Tab1ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab1.Tab1ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

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

        BindingHolder(Tab1ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
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
