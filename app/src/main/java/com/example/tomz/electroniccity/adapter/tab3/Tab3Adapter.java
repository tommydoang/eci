package com.example.tomz.electroniccity.adapter.tab3;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab3.DataProductTab3Response;
import com.example.tomz.electroniccity.databinding.Tab3ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab3.Tab3ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab3Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab3Response> mTab3Model;

    public Tab3Adapter(List<DataProductTab3Response> mTab3Model) {
        this.mTab3Model = mTab3Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab3ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab3_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab3Model.size() > 0) {
            return mTab3Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab3ItemBestSellerBinding binding;
        private Tab3ItemViewModel tab3ItemViewModel;

        BindingHolder(Tab3ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataProductTab3Response dmi = mTab3Model.get(position);
            tab3ItemViewModel = new Tab3ItemViewModel(dmi);
            binding.setTab3(tab3ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab3Response> blogList) {
        mTab3Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab3Model.clear();
    }

}
