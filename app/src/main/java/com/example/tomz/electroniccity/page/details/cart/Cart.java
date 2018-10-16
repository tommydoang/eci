package com.example.tomz.electroniccity.page.details.cart;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.cart.CartAddressAdapter;
import com.example.tomz.electroniccity.adapter.cart.CartShopListAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.local.db.dao.CartDao;
import com.example.tomz.electroniccity.data.model.api.membership.DataAddressResponse;
import com.example.tomz.electroniccity.data.model.db.shop.DBShopListResponse;
import com.example.tomz.electroniccity.databinding.ActivityCartBinding;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Cart extends BaseActivity<ActivityCartBinding,CartViewModel> implements
        CartNavigator, CartShopListAdapter.UpdateQty {

    @Inject LinearLayoutManager mLinearLayoutManager;
    @Inject LinearLayoutManager mLinearLayoutManager2;
    @Inject CartViewModel mCartViewModel;
    @Inject CartDao mCartDao;
    @Inject DataManager mDataManager;
    @Inject CartAddressAdapter mCartAddressAdapter;
    private ActivityCartBinding mPaymentBinding;
    private RecyclerView mCartShopListRV, mCartAddrListRV;
    private LinearLayout mLayoutBodyCart;
    private int sizeDB = 0;
    private List<DBShopListResponse> dbShopListResponseList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    public CartViewModel getViewModel() {
        return mCartViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.cart;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaymentBinding = getViewDataBinding();
        mCartViewModel.setNavigator(this);
        dbShopListResponseList = new ArrayList<>();
        setupView();
        new pullDataFromDB().execute();
        new Handler().postDelayed(this::setupCartRV, 900);
        new Handler().postDelayed(this::setupCartAddr, 900);
    }

    private void parseDataFromDB(){
        if (sizeDB >= 1){
            if (Looper.myLooper() == null) {
                Looper.prepare();
                for (int idx = 0; idx < sizeDB; idx++) {
                    DBShopListResponse dbShopList = new DBShopListResponse();
                    dbShopList.setId_prod(mCartDao.loadAll().get(idx).getId_prod());
                    dbShopList.setImageProduk(mCartDao.loadAll().get(idx).getImageProduk());
                    dbShopList.setNameProduk(mCartDao.loadAll().get(idx).getNameProduk());
                    dbShopList.setSkuProduk(mCartDao.loadAll().get(idx).getSkuProduk());
                    dbShopList.setHargaNormalProduk(mCartDao.loadAll().get(idx).getHargaNormalProduk());
                    dbShopList.setHargaPromoProduk(mCartDao.loadAll().get(idx).getHargaPromoProduk());
                    dbShopList.setDeskripsiProd(mCartDao.loadAll().get(idx).getDeskripsiProd());
                    dbShopList.setSpeksifikasiProd(mCartDao.loadAll().get(idx).getSpeksifikasiProd());
                    dbShopList.setQtyItem(mCartDao.loadAll().get(idx).getQtyItem());
//                    Log.d("sizeDB >=1 tes1 " + idx + "", mCartDao.loadAll().get(idx).getQtyItem() + "");
                    dbShopListResponseList.add(dbShopList);
                }
                Looper.myLooper().quit();
            } else {
                Looper.myLooper().quit();
            }
        } else {
            Log.d("db_kosong tes1", "KOSONG!!!!");
        }
    }

    private void setupView(){
        Toolbar mToolbar = mPaymentBinding.toolbar;
        mCartShopListRV = mPaymentBinding.rvShopList;
        mCartAddrListRV = mPaymentBinding.rvAddressCart;
        mLayoutBodyCart = mPaymentBinding.layoutBodyCart;

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white),
                    PorterDuff.Mode.SRC_ATOP);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainStatusBarColor();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void mainStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.newlightblue));
    }

    private void setupCartRV(){
        mLayoutBodyCart.setVisibility(View.VISIBLE);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCartShopListRV.setLayoutManager(mLinearLayoutManager);
        mCartShopListRV.setItemAnimator(new DefaultItemAnimator());
        mCartShopListRV.setAdapter(new CartShopListAdapter(this,this, dbShopListResponseList));
    }

    private void setupCartAddr(){
        mLinearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        mCartAddrListRV.setLayoutManager(mLinearLayoutManager2);
        mCartAddrListRV.setItemAnimator(new DefaultItemAnimator());
        mCartAddrListRV.setAdapter(mCartAddressAdapter);

        new Handler().postDelayed(() -> mCartViewModel
                .addressRequest(mDataManager.getUserId(), "1"),1000);
        new Handler().postDelayed(this::subscribeDataAddr, 1400);
    }

    private void subscribeDataAddr(){
        mCartViewModel.getLiveCartAddrDataList().observe(this,
                dataAddressResponseList -> mCartViewModel.setDataToCartAddrList(dataAddressResponseList));
    }

    @Override
    public void onSuccessAddress() {

    }

    @Override
    public void onFailedAddress(String msg) {
        Log.e("onFailAddr tes1", msg);
    }


    @Override
    public void handleError(Throwable throwable) {
        Log.e("onHandleErr tes1", throwable.getMessage()+"");
    }

    @Override
    public void updateCartAddressList(List<DataAddressResponse> dataAddressResponseList) {
        mCartAddressAdapter.addItems(dataAddressResponseList);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void updateQtyItem(int mQty, int mId_prod) {
        updateParam param = new updateParam(mQty, mId_prod);
        new updateDataQty().execute(param);
    }

    @Override
    public void deleteCartItem(int mId_prod) {
        deleteParam param = new deleteParam(mId_prod);
        new deleteItem().execute(param);
    }

    @SuppressLint("StaticFieldLeak")
    private class pullDataFromDB extends AsyncTask<Void,Void,List<DBShopListResponse>> {

        @Override
        protected List<DBShopListResponse> doInBackground(Void... voids) {
            if (mCartDao.countItem()>=0){
                Log.d("sizeDBPay tes1", mCartDao.countItem()+"");
                sizeDB = mCartDao.countItem();
                parseDataFromDB();
            }
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class updateDataQty extends AsyncTask<updateParam,Void,List<DBShopListResponse>> {
        @Override
        protected List<DBShopListResponse> doInBackground(updateParam... updateParams) {
            prepareToUpdate(updateParams[0].qty, updateParams[0].id_prod);
            return null;
        }
    }

    private void prepareToUpdate(int qty, int id_prod){
        Log.d("prepareUpd tes1 1", qty+"");
        Log.d("prepareUpd tes1 2", id_prod+"" );
        mCartDao.updateQtyItem(qty, id_prod);
    }

    private static class updateParam{
        int qty, id_prod;

        updateParam(int qty, int id_prod) {
            this.qty = qty;
            this.id_prod = id_prod;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class deleteItem extends AsyncTask<deleteParam,Void,List<DBShopListResponse>>{

        @Override
        protected List<DBShopListResponse> doInBackground(deleteParam... deleteParams) {
            prepareToDelete(deleteParams[0].id_prod);
            return null;
        }
    }

    private void prepareToDelete (int id_prod){
        Log.d("prepareDel tes1", id_prod+"");
        mCartDao.deleteCartItem(id_prod);
    }

    private static class deleteParam{
        int id_prod;

        deleteParam(int id_prod){
            this.id_prod = id_prod;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
