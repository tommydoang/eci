package com.example.tomz.electroniccity.page.bn_home_content.tab5;

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
import com.example.tomz.electroniccity.adapter.home_tab.tab5.Tab5Adapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab5.Tab5AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab5.Tab5BannerAdapter;
import com.example.tomz.electroniccity.data.model.api.products.tab5.DataProductTab5Response;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab5AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.Tab5LayoutBinding;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab5 extends BaseFragment<Tab5LayoutBinding, Tab5ViewModel> implements
        Tab5Navigator, AdapterView.OnItemClickListener {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Tab5Adapter mTab5Adapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab5ViewModel mTab5ViewModel;
    private Tab5LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private boolean stopSliding = false;
    private ImageView mIvBannerFailed;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    private boolean isFragmentLoaded = false;
    public OnFragTab5InteractionListener mParentListener;

    public interface OnFragTab5InteractionListener {
        void onFragmentInteractionTab5(View view, int position,
                                       ArrayList<Tab5AllSubCategoryModel> subCategoryModels);
    }

    @Override
    public Tab5ViewModel getViewModel() {
        mTab5ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab5ViewModel.class);
        return mTab5ViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.tab5;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab5_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab5ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab5_banner"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
    }

    @Override
    public void updateList(List<DataProductTab5Response> dataProductTab5ResponseList) {
        mTab5Adapter.addItems(dataProductTab5ResponseList);
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

    private void customSendBroadcast(String str){
        Intent intent = new Intent("tab5_banner");
        intent.putExtra("tab5_banner", str);
        LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(intent);
    }


    private void subscribeData(){
        mTab5ViewModel.getImageLiveDataList().observe(this,
                dataProductTab5Responses -> mTab5ViewModel.setDataToList(dataProductTab5Responses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSellerTab5.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSellerTab5.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSellerTab5.setAdapter(mTab5Adapter);
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
        mTab5ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab5BannerAdapter(getBaseActivity(),
//                    mTab5ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab5ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab5ViewModel.getAllBannerList().size());
//            handler.postDelayed(animateViewPager,ANIM_VIEWPAGER_DELAY);
//        },9000);
    }

    private void setupGridView(){
        ExpandableHeightGridView mExpandableHeightGridView = mBinding.gvSubCategory;
        Tab5AllSubCategoryAdapter mTab5AllSubCategoryAdapter =
                new Tab5AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab5AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    private ArrayList<Tab5AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab5AllSubCategoryModel> models = new ArrayList<>();

        Tab5AllSubCategoryModel categoryModel = new Tab5AllSubCategoryModel();
        categoryModel.setImage(R.drawable.camera);
        categoryModel.setName(getString(R.string.sub_cat_kamera_kamera));
        models.add(categoryModel);

        categoryModel = new Tab5AllSubCategoryModel();
        categoryModel.setImage(R.drawable.video_camera);
        categoryModel.setName(getString(R.string.sub_cat_kamera_video_kamera));
        models.add(categoryModel);

        categoryModel = new Tab5AllSubCategoryModel();
        categoryModel.setImage(R.drawable.photo_printer);
        categoryModel.setName(getString(R.string.sub_cat_kamera_photo_printer));
        models.add(categoryModel);

        categoryModel = new Tab5AllSubCategoryModel();
        categoryModel.setImage(R.drawable.aksesoris_cam);
        categoryModel.setName(getString(R.string.sub_cat_kamera_aksesoris));
        models.add(categoryModel);

        return models;
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
        mParentListener.onFragmentInteractionTab5(view, i, getAllSubCategory());
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
                    if (mTab5ViewModel.getAllBannerList() != null &&
                            mTab5ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab5ViewModel.getAllBannerList().size());
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragTab5InteractionListener){
            mParentListener = (OnFragTab5InteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab5InteractionListener");
        }
    }

    private BroadcastReceiver BReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("action tes1", action);
            if (action != null){
                if (action.equals("tab5_banner")){
                    Log.d("tab5_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab5_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab5BannerAdapter(getBaseActivity(),
                                    mTab5ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab5ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab5ViewModel.getAllBannerList().size());
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



}
