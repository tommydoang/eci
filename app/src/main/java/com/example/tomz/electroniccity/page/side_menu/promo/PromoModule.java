package com.example.tomz.electroniccity.page.side_menu.promo;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.PromoAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class PromoModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(PromoViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    PromoViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        return new PromoViewModel(dataManager, schedulerProvider);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(Promo promo){
        return new LinearLayoutManager(promo.getParent());
    }

    @Provides
    PromoAdapter provideAdapter(){ return new PromoAdapter(new ArrayList<>()); }

}
