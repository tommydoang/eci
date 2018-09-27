package com.example.tomz.electroniccity.page.bn_home_content.tab9;

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
import com.example.tomz.electroniccity.adapter.home_tab.tab9.Tab9Adapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab9.Tab9AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab9.Tab9BannerAdapter;
import com.example.tomz.electroniccity.data.model.api.products.tab9.DataProductTab9Response;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab9AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.Tab9LayoutBinding;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab9 extends BaseFragment<Tab9LayoutBinding, Tab9ViewModel> implements
        Tab9Navigator, AdapterView.OnItemClickListener {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Tab9Adapter mTab9Adapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab9ViewModel mTab9ViewModel;
    private Tab9LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private boolean stopSliding = false;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    private boolean isFragmentLoaded = false;
    private ImageView mIvBannerFailed;
    public OnFragTab9InteractionListener mParentListener;

    public interface OnFragTab9InteractionListener {
        void onFragmentInteractionTab9(View view, int position,
                                       ArrayList<Tab9AllSubCategoryModel> subCategoryModels);
    }

    @Override
    public Tab9ViewModel getViewModel() {
        mTab9ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab9ViewModel.class);
        return mTab9ViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.tab9;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab9_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab9ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab9_banner"));
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
    public void updateList(List<DataProductTab9Response> dataProductTab9ResponseList) {
        mTab9Adapter.addItems(dataProductTab9ResponseList);
    }

    private void customSendBroadcast(String str){
        Intent intent = new Intent("tab9_banner");
        intent.putExtra("tab9_banner", str);
        LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(intent);
    }

    private void subscribeData(){
        mTab9ViewModel.getImageLiveDataList().observe(this,
                dataProductTab9Responses -> mTab9ViewModel.setDataToList(dataProductTab9Responses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSellerTab6.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSellerTab6.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSellerTab6.setAdapter(mTab9Adapter);
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
        mTab9ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab9BannerAdapter(getBaseActivity(),
//                    mTab9ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab9ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab9ViewModel.getAllBannerList().size());
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
            new Handler().postDelayed(this::setupGridView, 900);
            new Handler().postDelayed(this::setupRVTopSeller, 1200);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mParentListener.onFragmentInteractionTab9(view, i, getAllSubCategory());
    }

    private BroadcastReceiver BReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("action tes1", action);
            if (action != null){
                if (action.equals("tab9_banner")){
                    Log.d("tab9_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab9_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab9BannerAdapter(getBaseActivity(),
                                    mTab9ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab9ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab9ViewModel.getAllBannerList().size());
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

    @SuppressLint("ClickableViewAccessibility")
    private void setupPagerTouchListener(){
        mBannerPager.setOnTouchListener((view, motionEvent) -> {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_UP:
                    // calls when touch release on ViewPager
                    if (mTab9ViewModel.getAllBannerList() != null &&
                            mTab9ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab9ViewModel.getAllBannerList().size());
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

    private void setupGridView(){
        ExpandableHeightGridView mExpandableHeightGridView = mBinding.gvSubCategory;
        Tab9AllSubCategoryAdapter mTab9AllSubCategoryAdapter =
                new Tab9AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab9AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    private ArrayList<Tab9AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab9AllSubCategoryModel> models = new ArrayList<>();

        Tab9AllSubCategoryModel categoryModel = new Tab9AllSubCategoryModel();
        categoryModel.setImage(R.drawable.telpon);
        categoryModel.setName(getString(R.string.sub_cat_office_phone_fax));
        models.add(categoryModel);

        categoryModel = new Tab9AllSubCategoryModel();
        categoryModel.setImage(R.drawable.proyektor);
        categoryModel.setName(getString(R.string.sub_cat_office_proyektor));
        models.add(categoryModel);

        categoryModel = new Tab9AllSubCategoryModel();
        categoryModel.setImage(R.drawable.voice_record);
        categoryModel.setName(getString(R.string.sub_cat_office_rekam_suara));
        models.add(categoryModel);

        categoryModel = new Tab9AllSubCategoryModel();
        categoryModel.setImage(R.drawable.cctv);
        categoryModel.setName(getString(R.string.sub_cat_office_cctv));
        models.add(categoryModel);

        return models;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragTab9InteractionListener){
            mParentListener = (OnFragTab9InteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab5InteractionListener");
        }
    }


}
