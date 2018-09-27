package com.example.tomz.electroniccity.page.side_menu.policy;

import com.example.tomz.electroniccity.databinding.ActivityPolicyBinding;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

public class Policy extends BaseActivity<ActivityPolicyBinding, PolicyViewModel> implements
        PolicyNavigator {
    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public PolicyViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
