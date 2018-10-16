package com.example.tomz.electroniccity.page.details.checkout;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductDetailModule {

    @Provides
    ProductDetailViewModel providesViewModel(DataManager dataManager,
                                             SchedulerProvider schedulerProvider){
        return new ProductDetailViewModel(dataManager, schedulerProvider);
    }

}
