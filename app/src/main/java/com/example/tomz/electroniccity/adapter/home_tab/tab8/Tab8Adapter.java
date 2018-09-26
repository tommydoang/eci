package com.example.tomz.electroniccity.adapter.home_tab.tab8;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab8.DataProductTab8Response;
import com.example.tomz.electroniccity.databinding.Tab8ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab8.Tab8ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab8Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab8Response> mTab8Model;

    public Tab8Adapter(List<DataProductTab8Response> mTab8Model) {
        this.mTab8Model = mTab8Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab8ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab8_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab8Model.size() > 0) {
            return mTab8Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab8ItemBestSellerBinding binding;
        private Tab8ItemViewModel tab8ItemViewModel;

        BindingHolder(Tab8ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataProductTab8Response dmi = mTab8Model.get(position);
            tab8ItemViewModel = new Tab8ItemViewModel(dmi);
            binding.setTab8(tab8ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab8Response> blogList) {
        mTab8Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab8Model.clear();
    }

}
