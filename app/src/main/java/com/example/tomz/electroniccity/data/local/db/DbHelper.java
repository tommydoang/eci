package com.example.tomz.electroniccity.data.local.db;

import com.example.tomz.electroniccity.data.model.db.CartMdl;
import com.example.tomz.electroniccity.data.model.db.WishesMdl;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<List<CartMdl>> getAllCart();
    Observable<List<WishesMdl>> getAllWishes();
    Observable<Boolean> insertCart(final CartMdl cart);
    Observable<Boolean> insertWish(final WishesMdl wish);
    Observable<Boolean> updateQtyItem(int qtyProd, int total_hpi, int idProd);
    Observable<Boolean> deleteAllCart();
    Observable<Boolean> deleteItemCart(int idProd);
    int countAllItem();

}
