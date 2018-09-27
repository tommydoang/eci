package com.example.tomz.electroniccity.page.bn_home_content.tab4;

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
import com.example.tomz.electroniccity.adapter.home_tab.tab4.Tab4Adapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab4.Tab4AllSubCategoryAdapter;
import com.example.tomz.electroniccity.adapter.home_tab.tab4.Tab4BannerAdapter;
import com.example.tomz.electroniccity.data.model.api.products.tab4.DataProductTab4Response;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab4AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.Tab4LayoutBinding;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseFragment;
import com.example.tomz.electroniccity.utils.indicator.PageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Tab4 extends BaseFragment<Tab4LayoutBinding, Tab4ViewModel> implements
        Tab4Navigator, AdapterView.OnItemClickListener {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject Tab4Adapter mTab4Adapter;
    @Inject ViewModelProvider.Factory mViewModelFactory;
    private Tab4ViewModel mTab4ViewModel;
    private Tab4LayoutBinding mBinding;
    private ViewPager mBannerPager;
    private PageIndicator mPageIndicator;
    private Runnable animateViewPager;
    private Handler handler;
    private boolean stopSliding = false;
    private boolean isFragmentLoaded = false;
    private ImageView mIvBannerFailed;
    private static final long ANIM_VIEWPAGER_DELAY = 7000;
    private static final long ANIM_VIEWPAGER_DELAY_USER_VIEW = 10000;
    public OnFragTab4InteractionListener mParentListener;

    public interface OnFragTab4InteractionListener {
        void onFragmentInteractionTab4(View view, int position,
                                       ArrayList<Tab4AllSubCategoryModel> subCategoryModels);
    }

    @Override
    public Tab4ViewModel getViewModel() {
        mTab4ViewModel = ViewModelProviders.of(this, mViewModelFactory).get(Tab4ViewModel.class);
        return mTab4ViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.tab4;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tab4_layout;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTab4ViewModel.setNavigator(this);
        LocalBroadcastManager.getInstance(getBaseActivity())
                .registerReceiver(BReceiver, new IntentFilter("tab4_banner"));
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
    public void updateList(List<DataProductTab4Response> dataProductTab4ResponseList) {
        mTab4Adapter.addItems(dataProductTab4ResponseList);
    }

    private void subscribeData(){
        mTab4ViewModel.getImageLiveDataList().observe(this,
                dataProductTab4Responses -> mTab4ViewModel.setDataToList(dataProductTab4Responses));
    }

    private void setupRVTopSeller(){
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mBinding.rvBestSellerTab4.setLayoutManager(mLinearLayoutManager);
        mBinding.rvBestSellerTab4.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvBestSellerTab4.setAdapter(mTab4Adapter);
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
        mTab4ViewModel.getDataBanner();
//        new Handler().postDelayed(() -> {
//            mBannerPager.setAdapter(new Tab4BannerAdapter(getBaseActivity(),
//                    mTab4ViewModel.getAllBannerList()));
//            mBannerPager.setOffscreenPageLimit(mTab4ViewModel.getAllBannerList().size()-1);
//            mPageIndicator.setViewPager(mBannerPager);
//            runnable(mTab4ViewModel.getAllBannerList().size());
//            handler.postDelayed(animateViewPager,ANIM_VIEWPAGER_DELAY);
//        },9000);
    }

    private void setupGridView(){
        ExpandableHeightGridView mExpandableHeightGridView = mBinding.gvSubCategory;
        Tab4AllSubCategoryAdapter mTab4AllSubCategoryAdapter =
                new Tab4AllSubCategoryAdapter(getBaseActivity(), getAllSubCategory());
        mExpandableHeightGridView.setAdapter(mTab4AllSubCategoryAdapter);
        mExpandableHeightGridView.setExpanded(true);
        mExpandableHeightGridView.setOnItemClickListener(this);
    }

    private ArrayList<Tab4AllSubCategoryModel> getAllSubCategory(){
        ArrayList<Tab4AllSubCategoryModel> models = new ArrayList<>();

        Tab4AllSubCategoryModel categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.ert2);
        categoryModel.setName(getString(R.string.sub_cat_ert_ac));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.kulkas);
        categoryModel.setName(getString(R.string.sub_cat_ert_kulkas));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.mesin_cuci);
        categoryModel.setName(getString(R.string.sub_cat_ert_mesin_cuci));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.air_purify);
        categoryModel.setName(getString(R.string.sub_cat_ert_air_purifier));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.water_purify);
        categoryModel.setName(getString(R.string.sub_cat_ert_water_pur));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.dishwashers);
        categoryModel.setName(getString(R.string.sub_cat_ert_dish_washer));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.water_heater);
        categoryModel.setName(getString(R.string.sub_cat_ert_water_heater));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.kipas_angin);
        categoryModel.setName(getString(R.string.sub_cat_ert_kipas_angin));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.vacuum_cleaner);
        categoryModel.setName(getString(R.string.sub_cat_ert_penghisap_debu));
        models.add(categoryModel);

        categoryModel = new Tab4AllSubCategoryModel();
        categoryModel.setImage(R.drawable.aksesoris_ert);
        categoryModel.setName(getString(R.string.sub_cat_ert_aksesories));
        models.add(categoryModel);

        return models;
    }


    private void customSendBroadcast(String str){
        Intent intent = new Intent("tab4_banner");
        intent.putExtra("tab4_banner", str);
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
            new Handler().postDelayed(this::setupGridView, 1200);
            new Handler().postDelayed(this::setupRVTopSeller, 1200);
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
                    if (mTab4ViewModel.getAllBannerList() != null &&
                            mTab4ViewModel.getAllBannerList().size() != 0) {
                        stopSliding = false;
                        runnable(mTab4ViewModel.getAllBannerList().size());
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
                if (action.equals("tab4_banner")){
                    Log.d("tab4_banner tes1", "MASUKK!!");
                    String messageFrom = intent.getStringExtra("tab4_banner");
                    Log.d("receiveFrom tes1", messageFrom);
                    switch (messageFrom){
                        case "get_banner_done":
                            mBannerPager.setAdapter(new Tab4BannerAdapter(getBaseActivity(),
                                    mTab4ViewModel.getAllBannerList()));
                            mBannerPager.setOffscreenPageLimit(mTab4ViewModel.getAllBannerList().size());
                            mPageIndicator.setViewPager(mBannerPager);
                            runnable(mTab4ViewModel.getAllBannerList().size());
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mParentListener.onFragmentInteractionTab4(view, i, getAllSubCategory());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnFragTab4InteractionListener){
            mParentListener = (OnFragTab4InteractionListener) getParentFragment();
        } else {
            throw new RuntimeException(context.toString()
                    +" must implement OnFragTab4InteractionListener");
        }
    }


}
