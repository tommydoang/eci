package com.example.tomz.electroniccity.adapter.home_tab.tab2;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.products.tab2.DataProductTab2Response;
import com.example.tomz.electroniccity.databinding.Tab2ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab2.Tab2ItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class Tab2Adapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataProductTab2Response> mTab2Model;

    public Tab2Adapter(List<DataProductTab2Response> mTab2Model) {
        this.mTab2Model = mTab2Model;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Tab2ItemBestSellerBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.tab2_item_best_seller, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mTab2Model.size() > 0) {
            return mTab2Model.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private Tab2ItemBestSellerBinding binding;
        private Tab2ItemViewModel tab2ItemViewModel;

        BindingHolder(Tab2ItemBestSellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataProductTab2Response dmi = mTab2Model.get(position);
            tab2ItemViewModel = new Tab2ItemViewModel(dmi);
            binding.setTab2(tab2ItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataProductTab2Response> blogList) {
        mTab2Model.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTab2Model.clear();
    }

}
