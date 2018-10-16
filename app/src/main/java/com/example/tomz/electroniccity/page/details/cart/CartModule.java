package com.example.tomz.electroniccity.page.details.cart;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.cart.CartAddressAdapter;
import com.example.tomz.electroniccity.adapter.cart.CartShopListAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class CartModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(CartViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    CartViewModel provideViewModel(DataManager dataManager,
                                   SchedulerProvider schedulerProvider){
        return new CartViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Cart cart){
        return new LinearLayoutManager(cart.getParent());
    }

//    @Provides
//    CartShopListAdapter provideShopListAdapter(){ return new CartShopListAdapter(new ArrayList<>()); }

    @Provides
    CartAddressAdapter provideAddressAdapter(){ return new CartAddressAdapter(new ArrayList<>()); }

}
