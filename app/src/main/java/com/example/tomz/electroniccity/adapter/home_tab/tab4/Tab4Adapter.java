package com.example.tomz.electroniccity.adapter.home_tab.tab4;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab4.DataProductTab4Response;
import com.example.tomz.electroniccity.databinding.Tab4ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab4.Tab4ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab4Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab4Response> mTab4Model;

    public Tab4Adapter(List<DataProductTab4Response> mTab4Model) {
        this.mTab4Model = mTab4Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab4ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab4_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab4Model.size() > 0) {
            return mTab4Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab4ItemBestSellerBinding binding;
        private Tab4ItemViewModel tab4ItemViewModel;

        BindingHolder(Tab4ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataProductTab4Response dmi = mTab4Model.get(position);
            tab4ItemViewModel = new Tab4ItemViewModel(dmi);
            binding.setTab4(tab4ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab4Response> blogList) {
        mTab4Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab4Model.clear();
    }

}
