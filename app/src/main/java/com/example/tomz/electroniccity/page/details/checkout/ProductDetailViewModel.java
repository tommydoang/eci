package com.example.tomz.electroniccity.page.details.checkout;

import android.util.Log;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model.db.CartMdl;
import com.example.tomz.electroniccity.utils.base.BaseViewModel;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

public class ProductDetailViewModel extends BaseViewModel<ProductDetailNavigator> {

    public ProductDetailViewModel(DataManager dataManager,
                                  SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBuyClicked(){
        Log.d("CLICKED TES1","MASUKK!!!");
        getNavigator().onSaveToDatabase();
    }



}
