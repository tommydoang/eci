package com.example.tomz.electroniccity.data.local.db;

import com.example.tomz.electroniccity.data.model.db.CartMdl;
import com.example.tomz.electroniccity.data.model.db.WishesMdl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase mAppDatabase) {
        this.mAppDatabase = mAppDatabase;
    }

    @Override
    public Observable<List<CartMdl>> getAllCart() {
        return Observable.fromCallable(() -> mAppDatabase.cartDao().loadAll());
    }

    @Override
    public Observable<List<WishesMdl>> getAllWishes() {
        return Observable.fromCallable(() -> mAppDatabase.wishDao().loadAll());
    }

    @Override
    public Observable<Boolean> insertCart(CartMdl cart) {
        return Observable.fromCallable(() -> {
            mAppDatabase.cartDao().insert(cart);
            return true;
        });
    }

    @Override
    public Observable<Boolean> insertWish(WishesMdl wish) {
        return Observable.fromCallable(() -> {
            mAppDatabase.wishDao().insert(wish);
            return true;
        });
    }

    @Override
    public Observable<Boolean> updateQtyItem(int qtyProd, int idProd, int total_hpi) {
        return Observable.fromCallable(() -> {
            mAppDatabase.cartDao().updateQtyItem(qtyProd,total_hpi,idProd);
            return true;
        });
    }

    @Override
    public Observable<Boolean> deleteAllCart() {
        return Observable.fromCallable(() -> {
            mAppDatabase.cartDao().deleteAll();
            return true;
        });
    }

    @Override
    public Observable<Boolean> deleteItemCart(int idProd) {
        return Observable.fromCallable(() -> {
            mAppDatabase.cartDao().deleteCartItem(idProd);
            return true;
        });
    }

    @Override
    public int countAllItem() {
        return mAppDatabase.cartDao().countItem();
    }

}
