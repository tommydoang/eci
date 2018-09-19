package com.example.tomz.electroniccity.adapter.tab5;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab5.DataProductTab5Response;
import com.example.tomz.electroniccity.databinding.Tab4ItemBestSellerBinding;
import com.example.tomz.electroniccity.databinding.Tab5ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab5.Tab5ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab5Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab5Response> mTab5Model;

    public Tab5Adapter(List<DataProductTab5Response> mTab5Model) {
        this.mTab5Model = mTab5Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab5ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab5_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab5Model.size() > 0) {
            return mTab5Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab5ItemBestSellerBinding binding;
        private Tab5ItemViewModel tab4ItemViewModel;

        BindingHolder(Tab5ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataProductTab5Response dmi = mTab5Model.get(position);
            tab4ItemViewModel = new Tab5ItemViewModel(dmi);
            binding.setTab5(tab4ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab5Response> blogList) {
        mTab5Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab5Model.clear();
    }

}
