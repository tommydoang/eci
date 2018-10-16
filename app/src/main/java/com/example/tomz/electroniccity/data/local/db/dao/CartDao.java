package com.example.tomz.electroniccity.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.tomz.electroniccity.data.model.db.CartMdl;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cart")
    List<CartMdl> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartMdl cartMdl);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CartMdl> cartMdls);

    @Query("UPDATE cart SET qty = :qtyProd WHERE id_prod = :idProd")
    void updateQtyItem (int qtyProd, int idProd);

    @Query("DELETE FROM cart")
    void deleteAll();

    @Query("DELETE FROM cart WHERE id_prod = :idProd")
    void deleteCartItem (int idProd);

    @Query("SELECT COUNT(*) FROM cart")
    int countItem();


}
