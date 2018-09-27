package com.example.tomz.electroniccity.page.side_menu.about;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.tomz.electroniccity.ViewModelProviderFactory;
import com.example.tomz.electroniccity.adapter.side_menu.about.AboutAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class AboutModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(AboutViewModel homeViewModel){
        return new ViewModelProviderFactory<>(homeViewModel);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(About about){
        return new LinearLayoutManager(about.getParent());
    }

    @Provides
    AboutViewModel provideMainViewModel(DataManager dataManager,
                                        SchedulerProvider schedulerProvider){
        return new AboutViewModel(dataManager, schedulerProvider);
    }

    @Provides
    AboutAdapter provideAdapter() { return new AboutAdapter(new ArrayList<>()); }

}
