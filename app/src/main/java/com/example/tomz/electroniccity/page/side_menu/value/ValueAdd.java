package com.example.tomz.electroniccity.page.side_menu.value;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProvider;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.side_menu.value_add.ValueAddAdapter;
import com.example.tomz.electroniccity.data.model.api.valueadded.DataValueAddResponse;
import com.example.tomz.electroniccity.databinding.ActivityValueAddBinding;
import com.example.tomz.electroniccity.utils.ExpandableHeightGridView;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class ValueAdd extends BaseActivity<ActivityValueAddBinding, ValueAddViewModel> implements
        ValueAddNavigator{

    @Inject ViewModelProvider.Factory mViewModelFactory;
    @Inject ValueAddViewModel mValueAddViewModel;
    private ActivityValueAddBinding mValueBinding;
    private ExpandableHeightGridView mGvPremiumService;
    private int idx = 0;
    private ArrayList<Bitmap> strImg = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_value_add;
    }

    @Override
    public ValueAddViewModel getViewModel() {
        return mValueAddViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.value;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mValueBinding = getViewDataBinding();
        mValueAddViewModel.setNavigator(this);
        setupView();
    }

    private void setupView(){
        Toolbar mToolbar = mValueBinding.toolbar;
        mGvPremiumService = mValueBinding.gvPremiumService;

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Objects.requireNonNull(mToolbar.getNavigationIcon())
                    .setColorFilter(getResources().getColor(R.color.white),
                    PorterDuff.Mode.SRC_ATOP);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainStatusBarColor();
        }
        mValueAddViewModel.getDataValueAddMenu();
    }

    private void prepareGridView(List<DataValueAddResponse> dataValueAddResponseList){
        ArrayList<String> imgValueAdd = new ArrayList<>();
        for (int idx = 0; idx < dataValueAddResponseList.size(); idx++){
            imgValueAdd.add(dataValueAddResponseList.get(idx).getImage_value());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mGvPremiumService.setAdapter(new ValueAddAdapter(this, dataValueAddResponseList));
        } else {
            //noinspection unchecked
            new getImgValueAdded().execute(imgValueAdd);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void mainStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.newlightblue));
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }


    @Override
    public void onSuccess(List<DataValueAddResponse> dataValueAddResponseList) {
        mGvPremiumService.setVisibility(View.VISIBLE);
        prepareGridView(dataValueAddResponseList);
    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateList(List<DataValueAddResponse> dataValueAddResponseList) {

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

    @SuppressLint("StaticFieldLeak")
    private class getImgValueAdded extends AsyncTask<ArrayList<String>,Void,ArrayList<Bitmap>> {
        @SafeVarargs
        @Override
        protected final ArrayList<Bitmap> doInBackground(ArrayList<String>... imgParams) {
            if (idx == 0 || idx < 12) {
                for (int ipk = 0; ipk < 12; ipk++) {
                    idx+=1;
                    try {
                        URL url = new URL(imgParams[0].get(ipk));
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream input = connection.getInputStream();
                        strImg.add(BitmapFactory.decodeStream(input));
                    } catch (Exception e) {
                        Log.e("errDIB tes1", e.getMessage());
                    }
                }
                if (idx == 12) {
                    return strImg;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Bitmap> bitmap) {
            mGvPremiumService.setAdapter(new ValueAddAdapter(ValueAdd.this, bitmap));
        }
    }


}
