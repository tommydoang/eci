package com.example.tomz.electroniccity.utils.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<B extends ViewDataBinding, V extends BaseViewModel> extends Fragment
        /*implements HasSupportFragmentInjector*/ {

    private BaseActivity mBaseActivity;
    private View mRootView;
    private B mViewDataBinding;
    private V mViewModel;

    public abstract V getViewModel();
    public abstract int getBindingVariable();
    @LayoutRes public abstract int getLayoutId();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mBaseActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        mViewModel = getViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        View view = provideFragmentView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this,view);
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onDetach() {
        mBaseActivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    public B getViewDataBinding() {
        return mViewDataBinding;
    }

    public interface Callback {
        void onFragmentAttached();
        void onFragmentDetached(String tag);
    }

//    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return activityDispatchingAndroidInjector;
//    }

//    public abstract View provideFragmentView(LayoutInflater inflater,
//                                             ViewGroup container,
//                                             Bundle savedInstanceState);

//    public static void hideKeyboard(Context context) {
//        try {
//            ((Activity) context).getWindow().setSoftInputMode(WindowManager.
//                    LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//            if ((((Activity) context).getCurrentFocus() != null) &&
//                    (((Activity) context).getCurrentFocus().getWindowToken() != null)) {
//                ((InputMethodManager) context.
//                        getSystemService(Context.INPUT_METHOD_SERVICE)).
//                        hideSoftInputFromWindow(((Activity) context).
//                                getCurrentFocus().getWindowToken(), 0);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    } gak pake lagi

//    public static void showKeyboard(Context context) {
//        ((InputMethodManager) (context).getSystemService(Context.INPUT_METHOD_SERVICE)).
//                toggleSoftInput(InputMethodManager.SHOW_FORCED,
//                        InputMethodManager.HIDE_IMPLICIT_ONLY);
//    } gak dipake lagi

}
