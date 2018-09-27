package com.example.tomz.electroniccity.page.bn_home_content.tab3;

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
import com.example.tomz.electroniccity.adapter.home_tab.tab3.Tab3Adapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab3.Tab3AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab3.Tab3BannerAdapter;
import com.example.tomz.electroniccity.data.model.api.products.tab3.DataProductTab3Response;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab3AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.Tab3LayoutBinding;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab3 extends BaseFragment<Tab3LayoutBinding, Tab3ViewModel> implements
        Tab3Navigator, AdapterView.OnItemClickListener{

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Tab3Adapter mTab3Adapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab3ViewModel mTab3ViewModel;
    private Tab3LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private ImageView mIvBannerFailed;
    private boolean stopSliding = false;
    private boolean isFragmentLoaded = false;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    public OnFragTab3InteractionListener mParentListener;

    public Tab3(){
        // Required empty public constructor
    }

    @Override
    public Tab3ViewModel getViewModel() {
        mTab3ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab3ViewModel.class);
        return mTab3ViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.tab3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab3_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab3ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab3_banner"));
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
    public void updateList(List<DataProductTab3Response> dataProductTab3ResponseList) {
        mTab3Adapter.addItems(dataProductTab3ResponseList);
    }

    private void subscribeData(){
        mTab3ViewModel.getImageLiveDataList().observe(this,
                dataProductTab3Responses -> mTab3ViewModel.setDataToList(dataProductTab3Responses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSellerTab3.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSellerTab3.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSellerTab3.setAdapter(mTab3Adapter);
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
        mTab3ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab3BannerAdapter(getBaseActivity(),
//                    mTab3ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab3ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab3ViewModel.getAllBannerList().size());
//            handler.postDelayed(animateViewPager,ANIM_VIEWPAGER_DELAY);
//        },9000);
    }

    private void customSendBroadcast(String str){
        Intent intent = new Intent("tab3_banner");
        intent.putExtra("tab3_banner", str);
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

    private BroadcastReceiver BReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("action tes1", action);
            if (action != null){
                if (action.equals("tab3_banner")){
                    Log.d("tab3_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab3_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab3BannerAdapter(getBaseActivity(),
                                    mTab3ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab3ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab3ViewModel.getAllBannerList().size());
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
            setupBannerSlider();
            new Handler().postDelayed(this::setupGridView, 1200);
            new Handler().postDelayed(this::setupRVTopSeller, 1200);
        }
    }

    private void setupGridView(){
        ExpandableHeightGridView mExpandableHeightGridView = mBinding.gvSubCategory;
        Tab3AllSubCategoryAdapter mTab3AllSubCategoryAdapter =
                new Tab3AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab3AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    private ArrayList<Tab3AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab3AllSubCategoryModel> models = new ArrayList<>();

        Tab3AllSubCategoryModel categoryModel = new Tab3AllSubCategoryModel();
        categoryModel.setImage(R.drawable.player);
        categoryModel.setName(getString(R.string.sub_cat_av_player));
        models.add(categoryModel);

        categoryModel = new Tab3AllSubCategoryModel();
        categoryModel.setImage(R.drawable.ht);
        categoryModel.setName(getString(R.string.sub_cat_av_ht));
        models.add(categoryModel);

        categoryModel = new Tab3AllSubCategoryModel();
        categoryModel.setImage(R.drawable.htp);
        categoryModel.setName(getString(R.string.sub_cat_av_htp));
        models.add(categoryModel);

        categoryModel = new Tab3AllSubCategoryModel();
        categoryModel.setImage(R.drawable.compo);
        categoryModel.setName(getString(R.string.sub_cat_av_hifi));
        models.add(categoryModel);

        categoryModel = new Tab3AllSubCategoryModel();
        categoryModel.setImage(R.drawable.portable);
        categoryModel.setName(getString(R.string.sub_cat_av_portable_speaker));
        models.add(categoryModel);

        categoryModel = new Tab3AllSubCategoryModel();
        categoryModel.setImage(R.drawable.headphone);
        categoryModel.setName(getString(R.string.sub_cat_av_earphone));
        models.add(categoryModel);

        categoryModel = new Tab3AllSubCategoryModel();
        categoryModel.setImage(R.drawable.aksesoris_av);
        categoryModel.setName(getString(R.string.sub_cat_av_accesories));
        models.add(categoryModel);

        return models;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mParentListener.onFragmentInteractionTab3(view, i, getAllSubCategory());
    }

    public interface OnFragTab3InteractionListener{
        void onFragmentInteractionTab3(View view ,int position,
                                       ArrayList<Tab3AllSubCategoryModel> tab3AllSubCategoryModels);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragTab3InteractionListener){
            mParentListener = (OnFragTab3InteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab4InteractionListener");
        }
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
                    if (mTab3ViewModel.getAllBannerList() != null &&
                            mTab3ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab3ViewModel.getAllBannerList().size());
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


}
