package com.example.tomz.electroniccity.adapter.tab7;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab6.DataProductTab6Response;
import com.example.tomz.electroniccity.data.model.api.products.tab7.DataProductTab7Response;
import com.example.tomz.electroniccity.databinding.Tab6ItemBestSellerBinding;
import com.example.tomz.electroniccity.databinding.Tab7ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab6.Tab6ItemViewModel;
import com.example.tomz.electroniccity.page.bn_home_content.tab7.Tab7ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab7Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab7Response> mTab7Model;

    public Tab7Adapter(List<DataProductTab7Response> mTab7Model) {
        this.mTab7Model = mTab7Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab7ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab7_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab7Model.size() > 0) {
            return mTab7Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab7ItemBestSellerBinding binding;
        private Tab7ItemViewModel tab7ItemViewModel;

        BindingHolder(Tab7ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataProductTab7Response dmi = mTab7Model.get(position);
            tab7ItemViewModel = new Tab7ItemViewModel(dmi);
            binding.setTab7(tab7ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab7Response> blogList) {
        mTab7Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab7Model.clear();
    }

}
