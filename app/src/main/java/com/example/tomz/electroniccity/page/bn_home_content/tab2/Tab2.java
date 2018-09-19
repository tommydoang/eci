package com.example.tomz.electroniccity.page.bn_home_content.tab2;

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
import com.example.tomz.electroniccity.adapter.tab2.Tab2Adapter;
import com.example.tomz.electroniccity.adapter.tab2.Tab2AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.tab2.Tab2BannerAdapter;
import com.example.tomz.electroniccity.data.model.api.products.tab2.DataProductTab2Response;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab2AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.Tab2LayoutBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab1.Tab1;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab2 extends BaseFragment<Tab2LayoutBinding, Tab2ViewModel> implements
        Tab2Navigator, AdapterView.OnItemClickListener {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Tab2Adapter mTab2Adapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab2ViewModel mTab2ViewModel;
    private Tab2LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private boolean stopSliding = false;
    private boolean isFragmentLoaded = false;
    private ImageView mIvBannerFailed;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    public OnFragTab2InteractionListener mParentListener;

    public Tab2() {
        // Required empty public constructor
    }

    public interface OnFragTab2InteractionListener{
        void onFragmentInteractionTab2(View view ,int position,
                                       ArrayList<Tab2AllSubCategoryModel> tab2AllSubCategoryModels);
    }
    @Override
    public Tab2ViewModel getViewModel() {
        mTab2ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab2ViewModel.class);
        return mTab2ViewModel;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onDestroyView tes1", "MASUKKKK!!!");
        LocalBroadcastManager.getInstance(getBaseActivity()).unregisterReceiver(BReceiver);
    }

    @Override
    public int getBindingVariable() {
        return BR.tab2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab2_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab2ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab2_banner"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
    }

    @Override
    public void handleErrorProduct(Throwable throwable) {
        Log.e("errProdTab2 tes1", throwable.getMessage()+"");
    }

    @Override
    public void handleErrorBanner(Throwable throwable) {
        Log.e("errBannerTab2 tes1", throwable.getMessage()+"");
        mIvBannerFailed.setBackgroundResource(R.drawable.img_banner_failed);
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
    public void updateList(List<DataProductTab2Response> dataProductTab2ResponseList) {
        mTab2Adapter.addItems(dataProductTab2ResponseList);
    }

    private void customSendBroadcast(String str){
        Intent intent = new Intent("tab2_banner");
        intent.putExtra("tab2_banner", str);
        LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(intent);
    }

    private void subscribeData(){
        mTab2ViewModel.getImageLiveDataList().observe(this,
                dataProductTab2Responses -> mTab2ViewModel.setDataToList(dataProductTab2Responses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSellerTab2.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSellerTab2.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSellerTab2.setAdapter(mTab2Adapter);
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
        mTab2ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab2BannerAdapter(getBaseActivity(),
//                    mTab2ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab2ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab2ViewModel.getAllBannerList().size());
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

    private BroadcastReceiver BReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("action tes1", action);
            if (action != null){
                if (action.equals("tab2_banner")){
                    Log.d("tab2_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab2_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab2BannerAdapter(getBaseActivity(),
                                    mTab2ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab2ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab2ViewModel.getAllBannerList().size());
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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !isFragmentLoaded){
            isFragmentLoaded = true;
//            setupRVTopSeller();
            setupBannerSlider();
            new Handler().postDelayed(this::setupGridView, 1900);
            new Handler().postDelayed(this::setupRVTopSeller, 1900);
        }
    }

    private ArrayList<Tab2AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab2AllSubCategoryModel> models = new ArrayList<>();

        Tab2AllSubCategoryModel categoryModel = new Tab2AllSubCategoryModel();
        categoryModel.setImage(R.drawable.led_tv);
        categoryModel.setName(getString(R.string.sub_cat_tv_led));
        models.add(categoryModel);

        categoryModel = new Tab2AllSubCategoryModel();
        categoryModel.setImage(R.drawable.uhd_tv);
        categoryModel.setName(getString(R.string.sub_cat_tv_uhd));
        models.add(categoryModel);

        categoryModel = new Tab2AllSubCategoryModel();
        categoryModel.setImage(R.drawable.oled_tv);
        categoryModel.setName(getString(R.string.sub_cat_tv_oled));
        models.add(categoryModel);

        categoryModel = new Tab2AllSubCategoryModel();
        categoryModel.setImage(R.drawable.aksesoris_tv);
        categoryModel.setName(getString(R.string.sub_cat_tv_accesories));
        models.add(categoryModel);

        return models;
    }

    private void setupGridView(){
        ExpandableHeightGridView mExpandableHeightGridView = mBinding.gvSubCategory;
        Tab2AllSubCategoryAdapter mTab2AllSubCategoryAdapter =
                new Tab2AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab2AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        mParentListener.onFragmentInteractionTab2(view, position, getAllSubCategory());
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
                    if (mTab2ViewModel.getAllBannerList() != null &&
                            mTab2ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab2ViewModel.getAllBannerList().size());
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
        if (getParentFragment() instanceof Tab1.OnFragTab1InteractionListener){
            mParentListener = (OnFragTab2InteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab2InteractionListener");
        }
    }
}
