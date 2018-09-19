package com.example.tomz.electroniccity.page.bn_home_content.tab8;

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
import com.example.tomz.electroniccity.adapter.tab8.Tab8Adapter;
import com.example.tomz.electroniccity.adapter.tab8.Tab8AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.tab8.Tab8BannerAdapter;
import com.example.tomz.electroniccity.data.model.api.products.tab8.DataProductTab8Response;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab8AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.Tab8LayoutBinding;
import com.example.tomz.electroniccity.page.bn_home_content.tab6.Tab6;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab8 extends BaseFragment<Tab8LayoutBinding, Tab8ViewModel> implements
        Tab8Navigator, AdapterView.OnItemClickListener {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Tab8Adapter mTab8Adapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab8ViewModel mTab8ViewModel;
    private Tab8LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private boolean stopSliding = false;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    private boolean isFragmentLoaded = false;
    private ImageView mIvBannerFailed;
    public OnFragTab8InteractionListener mParentListener;

    public interface OnFragTab8InteractionListener {
        void onFragmentInteractionTab8(View view, int position,
                                       ArrayList<Tab8AllSubCategoryModel> subCategoryModels);
    }

    @Override
    public Tab8ViewModel getViewModel() {
        mTab8ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab8ViewModel.class);
        return mTab8ViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.tab8;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab8_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab8ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab8_banner"));
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
    public void updateList(List<DataProductTab8Response> dataProductTab8ResponseList) {
        mTab8Adapter.addItems(dataProductTab8ResponseList);
    }

    private void subscribeData(){
        mTab8ViewModel.getImageLiveDataList().observe(this,
                dataProductTab8Responses -> mTab8ViewModel.setDataToList(dataProductTab8Responses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSellerTab6.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSellerTab6.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSellerTab6.setAdapter(mTab8Adapter);
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
        mTab8ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab8BannerAdapter(getBaseActivity(),
//                    mTab8ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab8ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab8ViewModel.getAllBannerList().size());
//            handler.postDelayed(animateViewPager,ANIM_VIEWPAGER_DELAY);
//        },9000);
    }

    private void customSendBroadcast(String str){
        Intent intent = new Intent("tab8_banner");
        intent.putExtra("tab8_banner", str);
        LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(intent);
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
        mParentListener.onFragmentInteractionTab8(view, i, getAllSubCategory());
    }

    private BroadcastReceiver BReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("action tes1", action);
            if (action != null){
                if (action.equals("tab8_banner")){
                    Log.d("tab8_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab8_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab8BannerAdapter(getBaseActivity(),
                                    mTab8ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab8ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab8ViewModel.getAllBannerList().size());
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
                    if (mTab8ViewModel.getAllBannerList() != null &&
                            mTab8ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab8ViewModel.getAllBannerList().size());
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
        Tab8AllSubCategoryAdapter mTab8AllSubCategoryAdapter =
                new Tab8AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab8AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    private ArrayList<Tab8AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab8AllSubCategoryModel> models = new ArrayList<>();

        Tab8AllSubCategoryModel categoryModel = new Tab8AllSubCategoryModel();
        categoryModel.setImage(R.drawable.small_appliances);
        categoryModel.setName(getString(R.string.sub_cat_prt_small_appliance));
        models.add(categoryModel);

        categoryModel = new Tab8AllSubCategoryModel();
        categoryModel.setImage(R.drawable.perlengkapan_dapur);
        categoryModel.setName(getString(R.string.sub_cat_prt_dapur));
        models.add(categoryModel);

        categoryModel = new Tab8AllSubCategoryModel();
        categoryModel.setImage(R.drawable.olah_makanan);
        categoryModel.setName(getString(R.string.sub_cat_prt_olah_makanan));
        models.add(categoryModel);

        categoryModel = new Tab8AllSubCategoryModel();
        categoryModel.setImage(R.drawable.kesehatan);
        categoryModel.setName(getString(R.string.sub_cat_prt_kesehatan));
        models.add(categoryModel);

        categoryModel = new Tab8AllSubCategoryModel();
        categoryModel.setImage(R.drawable.ibu_anak);
        categoryModel.setName(getString(R.string.sub_cat_prt_ibu_anak));
        models.add(categoryModel);

        return models;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragTab8InteractionListener){
            mParentListener = (OnFragTab8InteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab5InteractionListener");
        }
    }


}
