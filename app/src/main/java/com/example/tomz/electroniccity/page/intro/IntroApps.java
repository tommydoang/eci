package com.example.tomz.electroniccity.page.intro;

import android.annotation.TargetApi;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.adapter.intro.IntroViewPagerAdapter;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.databinding.ActivityIntroBinding;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.page.main.MainActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class IntroApps extends AppCompatActivity implements HasActivityInjector, View.OnClickListener{

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private int[] layouts;
    private Button btnSkip, btnNext;

    @Inject IntentHelper mIntentHelper;
    @Inject DataManager mDataManager;
    @Inject DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        if (mDataManager.getIntroFlag() == 0){
            mDataManager.setIntroFlag(0);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        ActivityIntroBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro);

        changeStatusBarColor();

        viewPager = mBinding.viewPager;
        dotsLayout = mBinding.layoutDots;
        btnSkip = mBinding.btnSkip;
        btnNext = mBinding.btnNext;

        layouts = new int[]{
                R.layout.intro1,
                R.layout.intro2,
                R.layout.intro3,
                R.layout.intro4};

        addBottomDots(0);

        IntroViewPagerAdapter myViewPagerAdapter = new IntroViewPagerAdapter(this, layouts);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(this);
        btnNext.setOnClickListener(this);

    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void addBottomDots(int currentPage){
        TextView[] dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.text_btn_intro_start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.text_btn_intro_next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    @Override
    public void onClick(View view) {
        int current;
        switch (view.getId()){
            case R.id.btn_skip:
                current = getItem(+3);
                if (current <= layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {
                    viewPager.setCurrentItem(current-1);
                }
                break;
            case R.id.btn_next:
                current = getItem(+1);
                if (current < layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {
                    mIntentHelper.createIntent(IntroApps.this, MainActivity.class);
                    mDataManager.setIntroFlag(1);
                    finish();
                }
                break;
        }
    }
}
