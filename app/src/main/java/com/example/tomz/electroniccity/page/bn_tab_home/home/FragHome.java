package com.example.tomz.electroniccity.page.bn_tab_home.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.ViewPagerTabAdapter;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab1AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab2AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab3AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab4AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab5AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab6AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab7AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab8AllSubCategoryModel;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab9AllSubCategoryModel;
import com.example.tomz.electroniccity.databinding.FragHomeBinding;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.page.bn_home_content.tab1.Tab1;
import com.example.tomz.electroniccity.page.bn_home_content.tab2.Tab2;
import com.example.tomz.electroniccity.page.bn_home_content.tab3.Tab3;
import com.example.tomz.electroniccity.page.bn_home_content.tab4.Tab4;
import com.example.tomz.electroniccity.page.bn_home_content.tab5.Tab5;
import com.example.tomz.electroniccity.page.bn_home_content.tab6.Tab6;
import com.example.tomz.electroniccity.page.bn_home_content.tab7.Tab7;
import com.example.tomz.electroniccity.page.bn_home_content.tab8.Tab8;
import com.example.tomz.electroniccity.page.bn_home_content.tab9.Tab9;
import com.example.tomz.electroniccity.utils.base.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

public class FragHome extends BaseFragment<FragHomeBinding, FragHomeViewModel> implements
        FragHomeNavigator, Tab1.OnFragTab1InteractionListener,
        Tab2.OnFragTab2InteractionListener,
        Tab3.OnFragTab3InteractionListener,
        Tab4.OnFragTab4InteractionListener,
        Tab5.OnFragTab5InteractionListener,
        Tab6.OnFragTab6InteractionListener,
        Tab7.OnFragTab7InteractionListener,
        Tab8.OnFragTab8InteractionListener,
        Tab9.OnFragTab9InteractionListener{

    @Inject FragHomeViewModel mFragHomeViewModel;
    @Inject ViewPagerTabAdapter mViewPagerTabAdapter;
    private FragHomeBinding mFragHomeBinding;

    public FragHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragHomeViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragHomeBinding = getViewDataBinding();
        prepareViewPager();
    }

    private void prepareViewPager(){
        mViewPagerTabAdapter.addFrag(new Tab1(),getString(R.string.tab_welcome));
        mViewPagerTabAdapter.addFrag(new Tab2(),getString(R.string.tab_tv));
        mViewPagerTabAdapter.addFrag(new Tab3(),getString(R.string.tab_audio));
        mViewPagerTabAdapter.addFrag(new Tab4(),getString(R.string.tab_ert));
        mViewPagerTabAdapter.addFrag(new Tab5(),getString(R.string.tab_kamera));
        mViewPagerTabAdapter.addFrag(new Tab6(),getString(R.string.tab_laptop_pc));
        mViewPagerTabAdapter.addFrag(new Tab7(),getString(R.string.tab_gadget));
        mViewPagerTabAdapter.addFrag(new Tab8(),getString(R.string.tab_prt));
        mViewPagerTabAdapter.addFrag(new Tab9(),getString(R.string.tab_office));
        mFragHomeBinding.pagerLayout.setOffscreenPageLimit(mViewPagerTabAdapter.getCount()); //sesuai bnyk tab
        mFragHomeBinding.pagerLayout.setAdapter(mViewPagerTabAdapter);
        mFragHomeBinding.tabLayout.setupWithViewPager(mFragHomeBinding.pagerLayout);
    }

    @Override
    public FragHomeViewModel getViewModel() {
        return mFragHomeViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.fraghome;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    public void goBack() {

    }

    @Override
    public void onFragmentInteractionTab1(View view, int position,
                                          ArrayList<Tab1AllSubCategoryModel> subCategoryModels) {
        switch (position){
            case 0:
                mFragHomeBinding.pagerLayout.setCurrentItem(1);
                break;
            case 1:
                mFragHomeBinding.pagerLayout.setCurrentItem(2);
                break;
            case 2:
                mFragHomeBinding.pagerLayout.setCurrentItem(3);
                break;
            case 3:
                mFragHomeBinding.pagerLayout.setCurrentItem(4);
                break;
            case 4:
                mFragHomeBinding.pagerLayout.setCurrentItem(5);
                break;
            case 5:
                mFragHomeBinding.pagerLayout.setCurrentItem(6);
                break;
            case 6:
                mFragHomeBinding.pagerLayout.setCurrentItem(7);
                break;
            case 7:
                mFragHomeBinding.pagerLayout.setCurrentItem(8);
                break;
        }
    }

    @Override
    public void onFragmentInteractionTab2(View view, int position,
                                          ArrayList<Tab2AllSubCategoryModel> subCategoryModels) {
        ToastHelper.createToast(getBaseActivity(),
                "FRAG HOME " + subCategoryModels.get(position).getName(), Toast.LENGTH_LONG);
    }

    @Override
    public void onFragmentInteractionTab3(View view, int position,
                                          ArrayList<Tab3AllSubCategoryModel> subCategoryModels) {
        ToastHelper.createToast(getBaseActivity(),
                "FRAG HOME " + subCategoryModels.get(position).getName(), Toast.LENGTH_LONG);
    }

    @Override
    public void onFragmentInteractionTab4(View view, int position,
                                          ArrayList<Tab4AllSubCategoryModel> subCategoryModels) {
        ToastHelper.createToast(getBaseActivity(),
                "FRAG HOME " + subCategoryModels.get(position).getName(), Toast.LENGTH_LONG);
    }

    @Override
    public void onFragmentInteractionTab5(View view, int position,
                                          ArrayList<Tab5AllSubCategoryModel> subCategoryModels) {
        ToastHelper.createToast(getBaseActivity(),
                "FRAG HOME " + subCategoryModels.get(position).getName(), Toast.LENGTH_LONG);

    }

    @Override
    public void onFragmentInteractionTab6(View view, int position,
                                          ArrayList<Tab6AllSubCategoryModel> subCategoryModels) {
        ToastHelper.createToast(getBaseActivity(),
                "FRAG HOME " + subCategoryModels.get(position).getName(), Toast.LENGTH_LONG);

    }

    @Override
    public void onFragmentInteractionTab7(View view, int position,
                                          ArrayList<Tab7AllSubCategoryModel> subCategoryModels) {
        ToastHelper.createToast(getBaseActivity(),
                "FRAG HOME " + subCategoryModels.get(position).getName(), Toast.LENGTH_LONG);

    }

    @Override
    public void onFragmentInteractionTab8(View view, int position,
                                          ArrayList<Tab8AllSubCategoryModel> subCategoryModels) {
        ToastHelper.createToast(getBaseActivity(),
                "FRAG HOME " + subCategoryModels.get(position).getName(), Toast.LENGTH_LONG);

    }

    @Override
    public void onFragmentInteractionTab9(View view, int position,
                                          ArrayList<Tab9AllSubCategoryModel> subCategoryModels) {
        ToastHelper.createToast(getBaseActivity(),
                "FRAG HOME " + subCategoryModels.get(position).getName(), Toast.LENGTH_LONG);
    }
}
