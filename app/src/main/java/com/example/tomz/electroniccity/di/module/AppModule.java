package com.example.tomz.electroniccity.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.tomz.electroniccity.data.AppDataManager;
import com.example.tomz.electroniccity.data.DataManager;
import com.example.tomz.electroniccity.data.local.db.AppDatabase;
import com.example.tomz.electroniccity.data.local.db.AppDbHelper;
import com.example.tomz.electroniccity.data.local.db.DbHelper;
import com.example.tomz.electroniccity.data.local.db.dao.CartDao;
import com.example.tomz.electroniccity.data.local.pref.AppPreferencesHelper;
import com.example.tomz.electroniccity.data.local.pref.PreferencesHelper;
import com.example.tomz.electroniccity.data.remote.ApiClient;
import com.example.tomz.electroniccity.data.remote.ApiHelper;
import com.example.tomz.electroniccity.data.remote.AppApiHelper;
import com.example.tomz.electroniccity.data.remote.InterfaceApi;
import com.example.tomz.electroniccity.di.ApiInfo;
import com.example.tomz.electroniccity.di.ApplicationContext;
import com.example.tomz.electroniccity.di.DatabaseInfo;
import com.example.tomz.electroniccity.di.PerActivity;
import com.example.tomz.electroniccity.di.PreferencesInfo;
import com.example.tomz.electroniccity.helper.IntentHelper;
import com.example.tomz.electroniccity.helper.LoadFragmentHelper;
import com.example.tomz.electroniccity.helper.firebase.AnalyticsClient;
import com.example.tomz.electroniccity.utils.AppConstants;
import com.example.tomz.electroniccity.utils.MaskProcess;
import com.example.tomz.electroniccity.utils.connection.ConnectionDetector;
import com.example.tomz.electroniccity.utils.NewEnableGPS;
import com.example.tomz.electroniccity.utils.rx.AppRxHelper;
import com.example.tomz.electroniccity.utils.rx.SchedulerProvider;
import com.example.tomz.electroniccity.utils.services.CheckServices;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
//import retrofit2.Retrofit;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppRxHelper();
    }

    @Provides
    @Singleton
    Gson provideGson() { return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferencesInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper){
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    MaskProcess provideMaskProcess() { return new MaskProcess(); }

    @Provides
    ConnectionDetector provideConnectionDetector() {
        return new ConnectionDetector();
    }

    @Provides
    CheckServices provideCheckServices(){
        return new CheckServices();
    }

    @Provides
    NewEnableGPS provideNewEnableGPS(){
        return new NewEnableGPS();
    }

    @Provides
    FirebaseAnalytics provideFirebaseAnalytics(@ApplicationContext Context ctx){
        return new AnalyticsClient().init(ctx);
    }

    @Provides
    IntentHelper provideIntentHelper(){
        return new IntentHelper();
    }

    @Provides
    LoadFragmentHelper provideFragmentLoader() { return new LoadFragmentHelper(); }

    @Singleton
    @Provides
    CartDao provideCartDao(AppDatabase appDatabase){
        return appDatabase.cartDao();
    }

//    @Provides
//    LocalBroadcastManager provideBroadcastManager(@PerActivity Context ctx) {
//        return LocalBroadcastManager.getInstance(ctx);
//    }


//    @Provides
//    FragmentManager provideFragmentManager(MainActivity mainActivity) {
//        Log.d("fragManager tes1", "CALLED!!");
//        return mainActivity.getSupportFragmentManager();
//    }

//    @Provides
//    ViewPagerTabAdapter providePagerAdapter(FragmentManager fm){
//        return new ViewPagerTabAdapter(fm);
//    }

//    @Provides
//    @Singleton
//    Retrofit provideRetrofit(ApiClient apiClient){
//        Log.d("appmodule tes1", "provideRetrofit called");
//        return apiClient.initRetro();
//    }

//    @Provides
//    @Singleton
//    InterfaceApi provideInterfaceApi(Retrofit retrofit){
//        Log.d("appmodule tes1", "provideInterfaceApi called");
//        return retrofit.create(InterfaceApi.class);
//    }

//    @Provides
//    @Singleton
//    SharedPreferences provideSharedPrefs(@PreferencesInfo Context ctx) {
//        Log.d("appmodule tes1", "provideSharedPrefs called");
//        return ctx.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE);
//    }

}
