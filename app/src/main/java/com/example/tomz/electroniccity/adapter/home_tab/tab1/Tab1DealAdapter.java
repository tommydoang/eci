package com.example.tomz.electroniccity.adapter.home_tab.tab1;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataDealTab1Response;
import com.example.tomz.electroniccity.databinding.Tab1ItemBestDealBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab1.Tab1ItemDealViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab1DealAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataDealTab1Response> mTab1DealModel;

    public Tab1DealAdapter(List<DataDealTab1Response> mTab1DealModel) {
        this.mTab1DealModel = mTab1DealModel;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab1ItemBestDealBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab1_item_best_deal, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab1DealModel.size() > 0) {
            return mTab1DealModel.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab1ItemBestDealBinding binding;
        private Tab1ItemDealViewModel tab1ItemDealViewModel;

        BindingHolder(Tab1ItemBestDealBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataDealTab1Response dmi = mTab1DealModel.get(position);
            tab1ItemDealViewModel = new Tab1ItemDealViewModel(dmi);
            binding.setTab1deal(tab1ItemDealViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataDealTab1Response> blogList) {
        mTab1DealModel.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab1DealModel.clear();
    }

}
