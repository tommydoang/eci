package com.example.tomz.electroniccity.adapter.cart;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.databinding.AddressItemBinding;
import com.example.tomz.electroniccity.databinding.CartAddressShopItemBinding;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.AddressItemViewModel;
import com.example.tomz.electroniccity.page.details.cart.CartAddressItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class CartAddressAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataAddressResponse> mAddressModel;

    public CartAddressAdapter(List<DataAddressResponse> mAddressModel){
        this.mAddressModel = mAddressModel;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartAddressShopItemBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cart_address_shop_item, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mAddressModel.size() > 0) {
            return mAddressModel.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private CartAddressShopItemBinding binding;
        private CartAddressItemViewModel addressItemViewModel;

        BindingHolder(CartAddressShopItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataAddressResponse dmi = mAddressModel.get(position);
            addressItemViewModel = new CartAddressItemViewModel(dmi);
            binding.setCart(addressItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataAddressResponse> blogList) {
        mAddressModel.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mAddressModel.clear();
    }

}
