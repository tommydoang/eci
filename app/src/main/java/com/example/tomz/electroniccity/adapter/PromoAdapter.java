package com.example.tomz.electroniccity.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.tab1.Tab1Adapter;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;
import com.example.tomz.electroniccity.data.model.api.promo.DataPromoResponse;
import com.example.tomz.electroniccity.databinding.AllPromoItemBinding;
import com.example.tomz.electroniccity.databinding.Tab1ItemBestSellerBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab1.Tab1ItemViewModel;
import com.example.tomz.electroniccity.page.side_menu.promo.PromoItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataPromoResponse> mPromoModel;

    public PromoAdapter(List<DataPromoResponse> mPromoModel) {
        this.mPromoModel = mPromoModel;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AllPromoItemBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.all_promo_item, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mPromoModel.size() > 0) {
            return mPromoModel.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private AllPromoItemBinding binding;
        private PromoItemViewModel promoItemViewModel;

        BindingHolder(AllPromoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataPromoResponse dmi = mPromoModel.get(position);
            promoItemViewModel = new PromoItemViewModel(dmi);
            binding.setPromo(promoItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataPromoResponse> blogList) {
        mPromoModel.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mPromoModel.clear();
    }

}
