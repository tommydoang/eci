package com.example.tomz.electroniccity.adapter.home_tab.tab9;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab9.DataProductTab9Response;
import com.example.tomz.electroniccity.databinding.Tab9ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab9.Tab9ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab9Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab9Response> mTab9Model;

    public Tab9Adapter(List<DataProductTab9Response> mTab9Model) {
        this.mTab9Model = mTab9Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab9ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab9_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab9Model.size() > 0) {
            return mTab9Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab9ItemBestSellerBinding binding;
        private Tab9ItemViewModel tab9ItemViewModel;

        BindingHolder(Tab9ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataProductTab9Response dmi = mTab9Model.get(position);
            tab9ItemViewModel = new Tab9ItemViewModel(dmi);
            binding.setTab9(tab9ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab9Response> blogList) {
        mTab9Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab9Model.clear();
    }

}
