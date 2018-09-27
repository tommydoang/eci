package com.example.tomz.electroniccity.page.bn_home_content.tab7;

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
import com.example.tomz.electroniccity.adapter.home_tab.tab7.Tab7Adapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab7.Tab7AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab7.Tab7BannerAdapter;
import com.example.tomz.electroniccity.data.model.api.products.tab7.DataProductTab7Response;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab7AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.Tab7LayoutBinding;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab7 extends BaseFragment<Tab7LayoutBinding, Tab7ViewModel> implements
        Tab7Navigator, AdapterView.OnItemClickListener {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Tab7Adapter mTab7Adapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab7ViewModel mTab7ViewModel;
    private Tab7LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private boolean stopSliding = false;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    private boolean isFragmentLoaded = false;
    private ImageView mIvBannerFailed;
    public OnFragTab7InteractionListener mParentListener;

    public interface OnFragTab7InteractionListener {
        void onFragmentInteractionTab7(View view, int position,
                                       ArrayList<Tab7AllSubCategoryModel> subCategoryModels);
    }

    @Override
    public Tab7ViewModel getViewModel() {
        mTab7ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab7ViewModel.class);
        return mTab7ViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.tab7;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab7_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab7ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab7_banner"));
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
    public void updateList(List<DataProductTab7Response> dataProductTab7ResponseList) {
        mTab7Adapter.addItems(dataProductTab7ResponseList);
    }

    private void subscribeData(){
        mTab7ViewModel.getImageLiveDataList().observe(this,
                dataProductTab7Responses -> mTab7ViewModel.setDataToList(dataProductTab7Responses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSellerTab6.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSellerTab6.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSellerTab6.setAdapter(mTab7Adapter);
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
        mTab7ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab7BannerAdapter(getBaseActivity(),
//                    mTab7ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab7ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab7ViewModel.getAllBannerList().size());
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isFragmentLoaded){
            isFragmentLoaded = true;
            setupBannerSlider();
            new Handler().postDelayed(this::setupGridView, 1200);
            new Handler().postDelayed(this::setupRVTopSeller, 1200);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mParentListener.onFragmentInteractionTab7(view, i, getAllSubCategory());
    }

    private void customSendBroadcast(String str){
        Intent intent = new Intent("tab7_banner");
        intent.putExtra("tab7_banner", str);
        LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(intent);
    }

    private void setupGridView(){
        ExpandableHeightGridView mExpandableHeightGridView = mBinding.gvSubCategory;
        Tab7AllSubCategoryAdapter mTab7AllSubCategoryAdapter =
                new Tab7AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab7AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    private ArrayList<Tab7AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab7AllSubCategoryModel> models = new ArrayList<>();

        Tab7AllSubCategoryModel categoryModel = new Tab7AllSubCategoryModel();
        categoryModel.setImage(R.drawable.laptop_pc);
        categoryModel.setName(getString(R.string.sub_cat_pclaptop_laptop));
        models.add(categoryModel);

        categoryModel = new Tab7AllSubCategoryModel();
        categoryModel.setImage(R.drawable.peripherals);
        categoryModel.setName(getString(R.string.sub_cat_pclaptop_router));
        models.add(categoryModel);

        categoryModel = new Tab7AllSubCategoryModel();
        categoryModel.setImage(R.drawable.office);
        categoryModel.setName(getString(R.string.sub_cat_pclaptop_printer));
        models.add(categoryModel);

        categoryModel = new Tab7AllSubCategoryModel();
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
                    if (mTab7ViewModel.getAllBannerList() != null &&
                            mTab7ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab7ViewModel.getAllBannerList().size());
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
                if (action.equals("tab7_banner")){
                    Log.d("tab7_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab7_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab7BannerAdapter(getBaseActivity(),
                                    mTab7ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab7ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab7ViewModel.getAllBannerList().size());
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
        if (getParentFragment() instanceof OnFragTab7InteractionListener){
            mParentListener = (OnFragTab7InteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab5InteractionListener");
        }
    }


}
