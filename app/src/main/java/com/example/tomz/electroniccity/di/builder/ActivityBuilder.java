package com.example.tomz.electroniccity.di.builder;

import com.example.tomz.electroniccity.page.bn_home_content.tab1.Tab1;
import com.example.tomz.electroniccity.page.bn_home_content.tab1.Tab1Module;
import com.example.tomz.electroniccity.page.bn_home_content.tab2.Tab2;
import com.example.tomz.electroniccity.page.bn_home_content.tab2.Tab2Module;
import com.example.tomz.electroniccity.page.bn_home_content.tab3.Tab3;
import com.example.tomz.electroniccity.page.bn_home_content.tab3.Tab3Module;
import com.example.tomz.electroniccity.page.bn_home_content.tab4.Tab4;
import com.example.tomz.electroniccity.page.bn_home_content.tab4.Tab4Module;
import com.example.tomz.electroniccity.page.bn_home_content.tab5.Tab5;
import com.example.tomz.electroniccity.page.bn_home_content.tab5.Tab5Module;
import com.example.tomz.electroniccity.page.bn_home_content.tab6.Tab6;
import com.example.tomz.electroniccity.page.bn_home_content.tab6.Tab6Module;
import com.example.tomz.electroniccity.page.bn_home_content.tab7.Tab7;
import com.example.tomz.electroniccity.page.bn_home_content.tab7.Tab7Module;
import com.example.tomz.electroniccity.page.bn_home_content.tab8.Tab8;
import com.example.tomz.electroniccity.page.bn_home_content.tab8.Tab8Module;
import com.example.tomz.electroniccity.page.bn_home_content.tab9.Tab9;
import com.example.tomz.electroniccity.page.bn_home_content.tab9.Tab9Module;
import com.example.tomz.electroniccity.page.bn_tab_home.account.FragAcc;
import com.example.tomz.electroniccity.page.bn_tab_home.account.FragAccModule;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.Address;
import com.example.tomz.electroniccity.page.bn_tab_home.account.address.AddressModule;
import com.example.tomz.electroniccity.page.bn_tab_home.account.edit_pass.EditPass;
import com.example.tomz.electroniccity.page.bn_tab_home.account.edit_pass.EditPassModule;
import com.example.tomz.electroniccity.page.bn_tab_home.account.edit_profile.EditProfile;
import com.example.tomz.electroniccity.page.bn_tab_home.account.history_order.HistoryOrder;
import com.example.tomz.electroniccity.page.bn_tab_home.account.history_order.HistoryOrderModule;
import com.example.tomz.electroniccity.page.bn_tab_home.account.invite.FragInvite;
import com.example.tomz.electroniccity.page.bn_tab_home.account.invite.FragInviteModule;
import com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget.RegForgAct;
import com.example.tomz.electroniccity.page.bn_tab_home.account.register_forget.RegForgModule;
import com.example.tomz.electroniccity.page.bn_tab_home.home.FragHome;
import com.example.tomz.electroniccity.page.bn_tab_home.home.FragHomeModule;
import com.example.tomz.electroniccity.page.intro.IntroApps;
import com.example.tomz.electroniccity.page.main.MainActivity;
import com.example.tomz.electroniccity.page.main.MainActivityModule;
import com.example.tomz.electroniccity.page.side_menu.promo.Promo;
import com.example.tomz.electroniccity.page.side_menu.promo.PromoModule;
import com.example.tomz.electroniccity.page.side_menu.value.ValueAdd;
import com.example.tomz.electroniccity.page.side_menu.value.ValueAddModule;
import com.example.tomz.electroniccity.page.splash.SplashActivity;
import com.example.tomz.electroniccity.page.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract IntroApps bindIntroApps();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = FragHomeModule.class)
    abstract FragHome bindFragHome();

    @ContributesAndroidInjector(modules = Tab1Module.class)
    abstract Tab1 bindTab1();

    @ContributesAndroidInjector(modules = Tab2Module.class)
    abstract Tab2 bindTab2();

    @ContributesAndroidInjector(modules = Tab3Module.class)
    abstract Tab3 bindTab3();

    @ContributesAndroidInjector(modules = Tab4Module.class)
    abstract Tab4 bindTab4();

    @ContributesAndroidInjector(modules = Tab5Module.class)
    abstract Tab5 bindTab5();

    @ContributesAndroidInjector(modules = Tab6Module.class)
    abstract Tab6 bindTab6();

    @ContributesAndroidInjector(modules = Tab7Module.class)
    abstract Tab7 bindTab7();

    @ContributesAndroidInjector(modules = Tab8Module.class)
    abstract Tab8 bindTab8();

    @ContributesAndroidInjector(modules = Tab9Module.class)
    abstract Tab9 bindTab9();

    @ContributesAndroidInjector(modules = FragAccModule.class)
    abstract FragAcc bindFragAcc();

    @ContributesAndroidInjector
    abstract EditProfile bindEditProfile();

    @ContributesAndroidInjector(modules = EditPassModule.class)
    abstract EditPass bindEditPass();

    @ContributesAndroidInjector(modules = AddressModule.class)
    abstract Address bindAddress();

    @ContributesAndroidInjector(modules = FragInviteModule.class)
    abstract FragInvite bindFragInvite();

    @ContributesAndroidInjector(modules = RegForgModule.class)
    abstract RegForgAct bindRegisterActivity();

    @ContributesAndroidInjector(modules = PromoModule.class)
    abstract Promo bindPromo();

    @ContributesAndroidInjector(modules = HistoryOrderModule.class)
    abstract HistoryOrder bindHistoryOrder();

    @ContributesAndroidInjector(modules = ValueAddModule.class)
    abstract ValueAdd bindValueAdd();

}
