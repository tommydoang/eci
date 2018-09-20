package com.example.tomz.electroniccity.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.api.membership.DataHistoryOrderResponse;
import com.example.tomz.electroniccity.databinding.AddressItemBinding;
import com.example.tomz.electroniccity.databinding.HistoryOrderItemBinding;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.AddressItemViewModel;
import com.example.tomz.electroniccity.page.bn_tab_home.account.history_order.HistoryOrderItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataHistoryOrderResponse> mHistoryOrderModel;

    public HistoryOrderAdapter(List<DataHistoryOrderResponse> mHistoryOrderModel) {
        this.mHistoryOrderModel = mHistoryOrderModel;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryOrderItemBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.history_order_item, parent, false);
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mHistoryOrderModel.size() > 0) {
            return mHistoryOrderModel.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private HistoryOrderItemBinding binding;
        private HistoryOrderItemViewModel historyOrderItemViewModel;

        BindingHolder(HistoryOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataHistoryOrderResponse dmi = mHistoryOrderModel.get(position);
            historyOrderItemViewModel = new HistoryOrderItemViewModel(dmi);
            binding.setOrder(historyOrderItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataHistoryOrderResponse> blogList) {
        mHistoryOrderModel.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mHistoryOrderModel.clear();
    }

}
