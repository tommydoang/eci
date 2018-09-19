package com.example.tomz.electroniccity.adapter.tab6;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab5.DataProductTab5Response;
import com.example.tomz.electroniccity.data.model.api.products.tab6.DataProductTab6Response;
import com.example.tomz.electroniccity.databinding.Tab5ItemBestSellerBinding;
import com.example.tomz.electroniccity.databinding.Tab6ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab5.Tab5ItemViewModel;
import com.example.tomz.electroniccity.page.bn_home_content.tab6.Tab6ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab6Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab6Response> mTab6Model;

    public Tab6Adapter(List<DataProductTab6Response> mTab6Model) {
        this.mTab6Model = mTab6Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab6ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab6_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab6Model.size() > 0) {
            return mTab6Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab6ItemBestSellerBinding binding;
        private Tab6ItemViewModel tab6ItemViewModel;

        BindingHolder(Tab6ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataProductTab6Response dmi = mTab6Model.get(position);
            tab6ItemViewModel = new Tab6ItemViewModel(dmi);
            binding.setTab6(tab6ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab6Response> blogList) {
        mTab6Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab6Model.clear();
    }

}
