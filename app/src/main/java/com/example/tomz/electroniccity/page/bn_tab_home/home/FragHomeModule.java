package com.example.tomz.electroniccity.page.bn_tab_home.home;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.tomz.electroniccity.adapter.home_tab.ViewPagerTabAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module/*(includes = Tab1Module.class)*/
public class FragHomeModule {

    @Provides
    FragmentManager provideFragmentManager(FragHome fragHome) {
        Log.d("fragManager tes1", "CALLED!!");
        return fragHome.getChildFragmentManager();
    }

    @Provides
    ViewPagerTabAdapter providePagerAdapter(FragmentManager fm){
        return new ViewPagerTabAdapter(fm);
    }

    @Provides
    FragHomeViewModel provideFragHomeViewModel(DataManager dataManager,
                                               SchedulerProvider schedulerProvider){
        return new FragHomeViewModel(dataManager,schedulerProvider);
    }

}
