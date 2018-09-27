package com.example.tomz.electroniccity.page.main;

import android.annotation.TargetApi;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tomz.electroniccity.BR;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.model._testdoang.DataModelIdeas;
import com.example.tomz.electroniccity.databinding.ActivityMainBinding;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.helper.LoadFragmentHelper;
import com.example.tomz.electroniccity.helper.ToastHelper;
import com.example.tomz.electroniccity.helper.alertdialog.AlertDialogHelper;
import com.example.tomz.electroniccity.helper.alertdialog.AlertDialogHelpers;
import com.example.tomz.electroniccity.helper.bottom_nav.bnvHelper;
import com.example.tomz.electroniccity.helper.ping.CheckInternetHelper;
import com.example.tomz.electroniccity.helper.ping.CheckInternetHelpers;
import com.example.tomz.electroniccity.helper.snackbar.SnackBarHelper;
import com.example.tomz.electroniccity.page.bn_tab_home.account.FragAcc;
import com.example.tomz.electroniccity.page.bn_tab_home.cart.FragCart;
import com.example.tomz.electroniccity.page.bn_tab_home.store.FragStore;
import com.example.tomz.electroniccity.page.bn_tab_home.home.FragHome;
import com.example.tomz.electroniccity.page.side_menu.about.About;
import com.example.tomz.electroniccity.page.side_menu.care.CustomerCare;
import com.example.tomz.electroniccity.page.side_menu.promo.Promo;
import com.example.tomz.electroniccity.page.side_menu.value.ValueAdd;
import com.example.tomz.electroniccity.utils.connection.ConnectionDetector;
import com.example.tomz.electroniccity.utils.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements
        MainNavigator, NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        HasSupportFragmentInjector, HasFragmentInjector, View.OnClickListener {

    @Inject IntentHelper mIntentHelper;
    @Inject ConnectionDetector mConnectionDetector;
    @Inject DataManager mDataManager;
    @Inject LoadFragmentHelper mLoadFragmentHelper;
    @Inject MainViewModel mMainViewModel;
    @Inject ViewModelProvider.Factory mViewModelProvideFactory;
    @Inject DispatchingAndroidInjector<Fragment> mSupportFragmentDispatchingAndroidInjector;
    @Inject DispatchingAndroidInjector<android.app.Fragment> mFragmentDispatchingAndroidInjector;
    private Fragment mFragment;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mHomeCoorLayout;
    private BottomNavigationView mBottomNavigationView;
    private ActivityMainBinding mHomeBinding;
    private ImageView mDrawerIcon, mSearchIcon;
    private int countFragBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);
        setupView();
        showAsDefault();
        attachKeyboardListeners();
    }

    private void setupView(){
        mDrawerLayout = mHomeBinding.drawerLayout;
        mNavigationView = mHomeBinding.headNavView;
        mToolbar = mHomeBinding.mainContentLayout.toolbar;
        mHomeCoorLayout = mHomeBinding.mainContentLayout.mainCoorLayout;
        mBottomNavigationView = mHomeBinding.mainContentLayout.bottomNavView;
        mDrawerIcon = mHomeBinding.mainContentLayout.icHamburgerMenu;
        mSearchIcon = mHomeBinding.mainContentLayout.icSearch;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mainStatusBarColor();
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.drawer_open,R.string.drawer_closed);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        checkConnection();
        bnvHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mDrawerIcon.setOnClickListener(this);
        mSearchIcon.setOnClickListener(this);
    }

    private void showAsDefault(){
        mFragment = new FragHome();
        mLoadFragmentHelper.loadFragment(this, R.id.main_container, mFragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()){
            case R.id.bot_nav_home:
                mFragment = new FragHome();
                mLoadFragmentHelper.loadFragment(this, R.id.main_container, mFragment);
                ToastHelper.createToast(this, "INI BERANDA!!", Toast.LENGTH_LONG);
                break;
            case R.id.bot_nav_fav_store:
                mFragment = new FragStore();
                mLoadFragmentHelper.loadFragment(this, R.id.main_container, mFragment);
                ToastHelper.createToast(this, "TOKO FAVORIT!!", Toast.LENGTH_LONG);
                break;
            case R.id.bot_nav_cart:
                mFragment = new FragCart();
                mLoadFragmentHelper.loadFragment(this, R.id.main_container, mFragment);
                ToastHelper.createToast(this, "KERANJANG!!", Toast.LENGTH_LONG);
                break;
            case R.id.bot_nav_my_acc:
                mFragment = new FragAcc();
                mLoadFragmentHelper.loadFragment(this, R.id.main_container, mFragment);
                ToastHelper.createToast(this, "AKUN SAYA!!", Toast.LENGTH_LONG);
                break;
            case R.id.nav_promo:
                ToastHelper.createToast(this, "INI PROMO!!", Toast.LENGTH_LONG);
                mIntentHelper.createIntent(this, Promo.class);
                break;
            case R.id.nav_care:
                ToastHelper.createToast(this, "CUSTOMER CARE!!", Toast.LENGTH_LONG);
                mIntentHelper.createIntent(this, CustomerCare.class);
                break;
            case R.id.nav_value:
                mIntentHelper.createIntent(this, ValueAdd.class);
                break;
            case R.id.nav_about:
                mIntentHelper.createIntent(this, About.class);
                break;
            case R.id.nav_privacy:
                break;
        }
        return true;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mSupportFragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavigationView.getMenu().getItem(0).setChecked(false);
        mNavigationView.getMenu().getItem(1).setChecked(false);
        mNavigationView.getMenu().getItem(2).setChecked(false);
        mNavigationView.getMenu().getItem(3).setChecked(false);
        mNavigationView.getMenu().getItem(4).setChecked(false);
    }

    private void checkConnection(){
        if (mConnectionDetector.isConnectingToInternet(this)) {
            SnackBarHelper.createWithAction(mHomeCoorLayout,
                    getString(R.string.err_no_network),
                    getString(R.string.text_snackbar_action_retry),
                    Snackbar.LENGTH_INDEFINITE, () ->
                            CheckInternetHelper.startPing(false,
                                    new CheckInternetHelpers() {
                                        @Override
                                        public void onResultPing() {
                                            SnackBarHelper.createWithoutAction(mHomeCoorLayout,
                                                    getString(R.string.err_no_network),
                                                    Snackbar.LENGTH_LONG);
                                        }
                                        @Override
                                        public void onOtherResult() {
                                            if (mDataManager.getAuthToken().isEmpty()) {
                                                mMainViewModel.getAuthApiCall(getResultCipher());
                                            }

                                        }
                                    })
            );
        } else {
            CheckInternetHelper.startPing(false,
                    new CheckInternetHelpers() {
                        @Override
                        public void onResultPing() {
                            SnackBarHelper.createWithAction(mHomeCoorLayout,
                                    getString(R.string.err_internet_access),
                                    getString(R.string.text_snackbar_action_retry),
                                    Snackbar.LENGTH_INDEFINITE, () ->
                                            CheckInternetHelper.startPing(false,
                                                    new CheckInternetHelpers() {
                                                        @Override
                                                        public void onResultPing() {
                                                            SnackBarHelper.createWithoutAction(mHomeCoorLayout,
                                                                    getString(R.string.err_internet_access),
                                                                    Snackbar.LENGTH_LONG);
                                                        }
                                                        @Override
                                                        public void onOtherResult() {
                                                            if (mDataManager.getAuthToken().isEmpty()) {
                                                                mMainViewModel.getAuthApiCall(getResultCipher());
                                                            }

                                                        }
                                                    })
                            );
                        }
                        @Override
                        public void onOtherResult() {
                            if (mDataManager.getAuthToken().isEmpty()) {
                                mMainViewModel.getAuthApiCall(getResultCipher());
                            }
                        }
                    });
            }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void mainStatusBarColor(){
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.newlightblue));
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            countFragBackStack = getSupportFragmentManager().getBackStackEntryCount();
            Log.d("countFrag tes1", "MAIN " + countFragBackStack+"");
//            if (countFragBackStack == 1 || countFragBackStack == 2){
                AlertDialogHelper.createGeneralDialog(this,
                        getString(R.string.text_b4_exit),
                        getString(R.string.text_yes), getString(R.string.text_no),
                        new AlertDialogHelpers() {
                            @Override
                            public void onPositiveClicked() {
                                mIntentHelper.createIntentAction2Exit(MainActivity.this,
                                        Intent.ACTION_MAIN);
                                finish();
                                System.exit(0);
                            }
                            @Override
                            public void onNegativeClicked() {

                            }
                        });
//            } if (countFragBackStack == 3){
//                getSupportFragmentManager().popBackStackImmediate();
//                countFragBackStack = 1;
//            }
        }
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return mFragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onShowKeyboard(int keyboardHeight) {
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onHideKeyboard() {
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelProvideFactory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.main;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    public void onSuccessGetAuthKey(String statusResponse) {
        if (statusResponse.equals("SUCCSESS") || statusResponse.equals("SUCCESS")){
            //get all products disini
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void updateBlog(List<DataModelIdeas> dataModelIdeasList) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ic_hamburger_menu:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.ic_search:
                ToastHelper.createToast(this, "SEARCH!!",Toast.LENGTH_LONG);
                break;
        }
    }
}
