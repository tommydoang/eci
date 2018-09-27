package com.example.tomz.electroniccity.page.bn_home_content.tab6;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.home_tab.tab6.Tab6Adapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab6.Tab6AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab6.Tab6BannerAdapter;
import com.example.tomz.electroniccity.data.model.api.products.tab6.DataProductTab6Response;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab6AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.Tab6LayoutBinding;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab6 extends BaseFragment<Tab6LayoutBinding, Tab6ViewModel> implements
        Tab6Navigator, AdapterView.OnItemClickListener {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Tab6Adapter mTab6Adapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab6ViewModel mTab6ViewModel;
    private Tab6LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private boolean stopSliding = false;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    private boolean isFragmentLoaded = false;
    private ImageView mIvBannerFailed;
    public OnFragTab6InteractionListener mParentListener;

    public interface OnFragTab6InteractionListener {
        void onFragmentInteractionTab6(View view, int position,
                                       ArrayList<Tab6AllSubCategoryModel> subCategoryModels);
    }

    @Override
    public Tab6ViewModel getViewModel() {
        mTab6ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab6ViewModel.class);
        return mTab6ViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.tab6;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab6_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab6ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab6_banner"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
    }


    @Override
    public void handleErrorProduct(Throwable throwable) {

    }

    @Override
    public void handleErrorBanner(Throwable throwable) {

    }

    @Override
    public void onSuccessBanner(String status) {
        if (status.equals("SUCCSESS") || status.equals("SUCCESS")){
            customSendBroadcast("get_banner_done");
        }
    }

    @Override
    public void onFailedBanner() {
        customSendBroadcast("get_banner_failed");
    }

    @Override
    public void updateList(List<DataProductTab6Response> dataProductTab6ResponseList) {
        mTab6Adapter.addItems(dataProductTab6ResponseList);
    }

    private void customSendBroadcast(String str){
        Intent intent = new Intent("tab6_banner");
        intent.putExtra("tab6_banner", str);
        LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(intent);
    }


    private void subscribeData(){
        mTab6ViewModel.getImageLiveDataList().observe(this,
                dataProductTab6Responses -> mTab6ViewModel.setDataToList(dataProductTab6Responses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSellerTab6.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSellerTab6.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSellerTab6.setAdapter(mTab6Adapter);
        subscribeData();
    }

    private void setupBannerSlider(){
        mBannerPager = mBinding.bannerViewPager;
        mPageIndicator = mBinding.indicator;
        mIvBannerFailed = mBinding.ivBannerFailed;
        subscribeDataBanner();
        setupPagerTouchListener();
    }

    private void subscribeDataBanner() {
        mTab6ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab6BannerAdapter(getBaseActivity(),
//                    mTab6ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab6ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab6ViewModel.getAllBannerList().size());
//            handler.postDelayed(animateViewPager,ANIM_VIEWPAGER_DELAY);
//        },9000);
    }

    private void runnable(final int size) {
        handler = new Handler();
        animateViewPager = () -> {
            if (!stopSliding) {
                if (mBannerPager.getCurrentItem() == size - 1) {
                    mBannerPager.setCurrentItem(0);
                } else {
                    mBannerPager.setCurrentItem(
                            mBannerPager.getCurrentItem() + 1, true);
                }
                handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY);
            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mParentListener.onFragmentInteractionTab6(view, i, getAllSubCategory());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isFragmentLoaded){
            isFragmentLoaded = true;
            setupBannerSlider();
            new Handler().postDelayed(this::setupGridView, 1200);
            new Handler().postDelayed(this::setupRVTopSeller, 1200);
        }
    }

    private void setupGridView(){
        ExpandableHeightGridView mExpandableHeightGridView = mBinding.gvSubCategory;
        Tab6AllSubCategoryAdapter mTab6AllSubCategoryAdapter =
                new Tab6AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab6AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    private ArrayList<Tab6AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab6AllSubCategoryModel> models = new ArrayList<>();

        Tab6AllSubCategoryModel categoryModel = new Tab6AllSubCategoryModel();
        categoryModel.setImage(R.drawable.laptop_pc);
        categoryModel.setName(getString(R.string.sub_cat_pclaptop_laptop));
        models.add(categoryModel);

        categoryModel = new Tab6AllSubCategoryModel();
        categoryModel.setImage(R.drawable.peripherals);
        categoryModel.setName(getString(R.string.sub_cat_pclaptop_router));
        models.add(categoryModel);

        categoryModel = new Tab6AllSubCategoryModel();
        categoryModel.setImage(R.drawable.office);
        categoryModel.setName(getString(R.string.sub_cat_pclaptop_printer));
        models.add(categoryModel);

        categoryModel = new Tab6AllSubCategoryModel();
        categoryModel.setImage(R.drawable.aksesoris_pclaptop);
        categoryModel.setName(getString(R.string.sub_cat_pclaptop_aksesoris));
        models.add(categoryModel);

        return models;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupPagerTouchListener(){
        mBannerPager.setOnTouchListener((view, motionEvent) -> {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_UP:
                    // calls when touch release on ViewPager
                    if (mTab6ViewModel.getAllBannerList() != null &&
                            mTab6ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab6ViewModel.getAllBannerList().size());
                        handler.postDelayed(animateViewPager, ANIM_VIEWPAGER_DELAY_USER_VIEW);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    // calls when ViewPager touch
                    if (handler != null && !stopSliding) {
                        stopSliding = true;
                        handler.removeCallbacks(animateViewPager);
                    }
                    break;
            }
            return false;
        });
    }

    private BroadcastReceiver BReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("action tes1", action);
            if (action != null){
                if (action.equals("tab6_banner")){
                    Log.d("tab6_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab6_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab6BannerAdapter(getBaseActivity(),
                                    mTab6ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab6ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab6ViewModel.getAllBannerList().size());
                            handler.postDelayed(animateViewPager,ANIM_VIEWPAGER_DELAY);
                            break;
                        case "get_banner_failed":
                            mIvBannerFailed.setBackgroundResource(R.drawable.img_banner_failed);
                            break;
                    }
                }
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragTab6InteractionListener){
            mParentListener = (OnFragTab6InteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab5InteractionListener");
        }
    }


}
