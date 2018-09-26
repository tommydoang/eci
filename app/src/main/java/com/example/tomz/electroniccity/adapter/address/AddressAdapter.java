package com.example.tomz.electroniccity.adapter.address;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.databinding.AddressItemBinding;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.AddressItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataAddressResponse> mAddressModel;

    public AddressAdapter(List<DataAddressResponse> mAddressModel) {
        this.mAddressModel = mAddressModel;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AddressItemBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.address_item, parent, false);
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

        private AddressItemBinding binding;
        private AddressItemViewModel addressItemViewModel;

        BindingHolder(AddressItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataAddressResponse dmi = mAddressModel.get(position);
            addressItemViewModel = new AddressItemViewModel(dmi);
            binding.setAddress(addressItemViewModel);
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
