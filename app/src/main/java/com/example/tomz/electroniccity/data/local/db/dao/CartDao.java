package com.example.tomz.electroniccity.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tomz.electroniccity.data.model.db.CartMdl;

import java.util.List;

@Dao
public interface CartDao {

    @Query("SELECT * FROM cart")
    List<CartMdl> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartMdl cartMdl);

    @Query("UPDATE cart SET qty = :qtyProd, total_harga_per_item = :total_hpi WHERE id_prod = :idProd")
    void updateQtyItem (int qtyProd, int total_hpi, int idProd );

    @Query("DELETE FROM cart")
    void deleteAll();

    @Query("DELETE FROM cart WHERE id_prod = :idProd")
    void deleteCartItem (int idProd);

    @Query("SELECT COUNT(*) FROM cart")
    int countItem();

    @Query("SELECT * FROM cart WHERE id_prod = :idProd")
    CartMdl isDataExist(int idProd);

}
