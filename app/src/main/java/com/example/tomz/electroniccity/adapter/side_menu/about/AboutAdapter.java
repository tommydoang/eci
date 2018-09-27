package com.example.tomz.electroniccity.adapter.side_menu.about;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.api.about.DataAboutUsResponse;
import com.example.tomz.electroniccity.databinding.AboutUsItemBinding;
import com.example.tomz.electroniccity.page.side_menu.about.AboutUsItemViewModel;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.util.List;

public class AboutAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private CustomTextViewLatoFont tvDesc;
    private List<DataAboutUsResponse> mAboutUsModel;

    public AboutAdapter(List<DataAboutUsResponse> mAboutUsModel) {
        this.mAboutUsModel = mAboutUsModel;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AboutUsItemBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.about_us_item, parent, false);
        tvDesc = mBinding.textDescriptionAboutEci;
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
        if (mAboutUsModel.size() > 0) {
            return mAboutUsModel.size();
        } else {
            return 0;
        }
    }

    public class BindingHolder extends BaseViewHolder {

        private AboutUsItemBinding binding;
        private AboutUsItemViewModel aboutItemViewModel;

        BindingHolder(AboutUsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onBind(int position) {
            if (mAboutUsModel.get(position).getDescription().equals("")){
                tvDesc.setVisibility(View.GONE);
            }
            DataAboutUsResponse dmi = mAboutUsModel.get(position);
            aboutItemViewModel = new AboutUsItemViewModel(dmi);
            binding.setAbout(aboutItemViewModel);
            binding.executePendingBindings();
        }
    }

    public void addItems(List<DataAboutUsResponse> blogList) {
        mAboutUsModel.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mAboutUsModel.clear();
    }

}
