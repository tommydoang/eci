package com.example.tomz.electroniccity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {

    private V viewModel;

    public ViewModelProviderFactory(V vm) { this.viewModel = vm; }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(viewModel.getClass())) {
            //noinspection unchecked
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
