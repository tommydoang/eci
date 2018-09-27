package com.example.tomz.electroniccity.page.bn_home_content.tab1;

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
import com.example.tomz.electroniccity.adapter.home_tab.tab1.Tab1AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab1.Tab1Adapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab1.Tab1BannerAdapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab1.Tab1DealAdapter;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab1AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataDealTab1Response;
import com.example.tomz.electroniccity.data.model.api.products.tab1.DataProductTab1Response;
import com.example.tomz.electroniccity.databinding.Tab1LayoutBinding;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab1 extends BaseFragment<Tab1LayoutBinding, Tab1ViewModel> implements
        Tab1Navigator, AdapterView.OnItemClickListener {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject LinearLayoutManager mDealLinearLayoutManager;
    @Inject Tab1Adapter mTab1Adapter;
    @Inject Tab1DealAdapter mTab1DealAdapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab1ViewModel mTab1ViewModel;
    private Tab1LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private ImageView mIvBannerFailed;
    private boolean stopSliding = false;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    public OnFragTab1InteractionListener mParentListener;

    public Tab1() {
        // Required empty public constructor
    }

    @Override
    public Tab1ViewModel getViewModel() {
        mTab1ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab1ViewModel.class);
        return mTab1ViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.tab1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab1_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab1ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab1_banner"));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
        setupBannerSlider();
        new Handler().postDelayed(this::setupGridView, 1000);
        new Handler().postDelayed(this::setupRVTopDeal, 1500);
        new Handler().postDelayed(this::setupRVTopSeller,1900);

    }

    @Override
    public void handleErrorProduct(Throwable throwable) {
        Log.e("errProdTab1 tes1", throwable.getMessage()+"");
    }

    @Override
    public void handleErrorBanner(Throwable throwable) {
        Log.e("errBannerTab1 tes1", throwable.getMessage()+"");
        mIvBannerFailed.setBackgroundResource(R.drawable.img_banner_failed);
    }

    @Override
    public void handleErrorDeal(Throwable throwable) {
        Log.e("errDealTab1 tes1", throwable.getMessage()+"");
    }

    @Override
    public void onSuccessBanner(String status) {
        if (status.equals("SUCCSESS") || status.equals("SUCCESS")){
            customSendBroadcast("get_banner_done");
        }
    }

    @Override
    public void onFailedProduct() {

    }

    @Override
    public void onFailedBanner() {
        customSendBroadcast("get_banner_failed");
    }

    @Override
    public void onFailedDeal() {

    }

    @Override
    public void updateList(List<DataProductTab1Response> dataProductTab1ResponseList) {
        mTab1Adapter.addItems(dataProductTab1ResponseList);
    }

    private void subscribeData(){
        mTab1ViewModel.getImageLiveDataList().observe(this,
                dataProductResponses -> mTab1ViewModel.setDataToList(dataProductResponses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSeller.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSeller.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSeller.setAdapter(mTab1Adapter);
        subscribeData();
    }

    @Override
    public void updateDealList(List<DataDealTab1Response> dataDealTab1ResponseList) {
        mTab1DealAdapter.addItems(dataDealTab1ResponseList);
    }

    private void subscribeDataDeal(){
        mTab1ViewModel.getImageLiveDataDealList().observe(this,
                dataDealTab1Responses -> mTab1ViewModel.setDataDealToList(dataDealTab1Responses));
    }

    private void setupRVTopDeal(){
        mDealLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvSuperDeal.setLayoutManager(mDealLinearLayoutManager);
        mBinding.rvSuperDeal.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvSuperDeal.setAdapter(mTab1DealAdapter);
        subscribeDataDeal();
    }

    private void setupBannerSlider(){
        mBannerPager = mBinding.bannerViewPager;
        mPageIndicator = mBinding.indicator;
        mIvBannerFailed = mBinding.ivBannerFailed;
        setupPagerTouchListener();
        subscribeDataBanner();
    }

    private void subscribeDataBanner() {
        mTab1ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab1BannerAdapter(getBaseActivity(),
//                    mTab1ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab1ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab1ViewModel.getAllBannerList().size());
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

    private void customSendBroadcast(String str){
        Log.d("sendBroadServ tes1", "MASUKK!!!");
        Intent intent = new Intent("tab1_banner");
        intent.putExtra("tab1_banner", str);
        LocalBroadcastManager.getInstance(getBaseActivity()).sendBroadcast(intent);
    }

    private BroadcastReceiver BReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("action tes1", action);
            if (action != null){
                if (action.equals("tab1_banner")){
                    Log.d("tab1_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab1_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab1BannerAdapter(getBaseActivity(),
                                    mTab1ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab1ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab1ViewModel.getAllBannerList().size());
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
                    if (mTab1ViewModel.getAllBannerList() != null &&
                            mTab1ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab1ViewModel.getAllBannerList().size());
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
        Tab1AllSubCategoryAdapter mTab1AllSubCategoryAdapter =
                new Tab1AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab1AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    private ArrayList<Tab1AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab1AllSubCategoryModel> models = new ArrayList<>();

        Tab1AllSubCategoryModel categoryModel = new Tab1AllSubCategoryModel();
        categoryModel.setImage(R.drawable.tv2);
        categoryModel.setName(getString(R.string.cat_tab1_tv));
        models.add(categoryModel);

        categoryModel = new Tab1AllSubCategoryModel();
        categoryModel.setImage(R.drawable.av);
        categoryModel.setName(getString(R.string.cat_tab1_av));
        models.add(categoryModel);

        categoryModel = new Tab1AllSubCategoryModel();
        categoryModel.setImage(R.drawable.ert2);
        categoryModel.setName(getString(R.string.cat_tab1_ert));
        models.add(categoryModel);

        categoryModel = new Tab1AllSubCategoryModel();
        categoryModel.setImage(R.drawable.camera);
        categoryModel.setName(getString(R.string.cat_tab1_kamera));
        models.add(categoryModel);

        categoryModel = new Tab1AllSubCategoryModel();
        categoryModel.setImage(R.drawable.laptop_pc);
        categoryModel.setName(getString(R.string.cat_tab1_laptop_pc));
        models.add(categoryModel);

        categoryModel = new Tab1AllSubCategoryModel();
        categoryModel.setImage(R.drawable.hp_tablet);
        categoryModel.setName(getString(R.string.cat_tab1_hp_tablet));
        models.add(categoryModel);

        categoryModel = new Tab1AllSubCategoryModel();
        categoryModel.setImage(R.drawable.prt2);
        categoryModel.setName(getString(R.string.cat_tab1_prt));
        models.add(categoryModel);

        categoryModel = new Tab1AllSubCategoryModel();
        categoryModel.setImage(R.drawable.office);
        categoryModel.setName(getString(R.string.cat_tab1_kantor));
        models.add(categoryModel);

        return models;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        mParentListener.onFragmentInteractionTab1(view, position, getAllSubCategory());
    }

    public interface OnFragTab1InteractionListener {
        void onFragmentInteractionTab1(View view, int position,
                                       ArrayList<Tab1AllSubCategoryModel> subCategoryModels);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragTab1InteractionListener){
            mParentListener = (OnFragTab1InteractionListener)getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab1InteractionListener");
        }
    }

    @Override
    public void onPause() {
        Log.d("onPause tes1", "TAB1 MASUKKK!!!");
        LocalBroadcastManager.getInstance(getBaseActivity()).unregisterReceiver(BReceiver);
        super.onPause();
    }
}
