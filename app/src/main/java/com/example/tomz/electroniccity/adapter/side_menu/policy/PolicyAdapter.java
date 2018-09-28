package com.example.tomz.electroniccity.adapter.side_menu.policy;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.policy.DataPolicyResponse;
import com.example.tomz.electroniccity.databinding.PolicyItemBinding;
import com.example.tomz.electroniccity.page.side_menu.policy.PolicyItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.util.List;

public class PolicyAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<DataPolicyResponse> mPolicyModel;

    public PolicyAdapter(List<DataPolicyResponse> mPolicyModel) {
        this.mPolicyModel = mPolicyModel;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PolicyItemBinding mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.policy_item, parent, false);
        CustomTextViewLatoFont tvDesc = mBinding.textDesc;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            tvDesc.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
        return new BindingHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mPolicyModel.size() > 0) {
            return mPolicyModel.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private PolicyItemBinding binding;
        private PolicyItemViewModel promoItemViewModel;

        BindingHolder(PolicyItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            DataPolicyResponse dmi = mPolicyModel.get(position);
            promoItemViewModel = new PolicyItemViewModel(dmi);
            binding.setPolicy(promoItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataPolicyResponse> blogList) {
        mPolicyModel.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mPolicyModel.clear();
    }

}
