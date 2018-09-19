package com.example.tomz.electroniccity.utils.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<K> extends ViewModel {

    private final SchedulerProvider schedulerProvider;
    private final DataManager mDataManager;
    private CompositeDisposable mCompositeDisposable;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private K mNavigator;


    public BaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        this.mDataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public K getNavigator() {
        return mNavigator;
    }

    public void setNavigator(K navigator) {
        this.mNavigator = navigator;
    }

    public void onViewCreated(){
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public void onDestroyView() {
        mCompositeDisposable.dispose();
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

}
