package com.example.tomz.electroniccity.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.tomz.electroniccity.data.model.db.CartMdl;
import com.example.tomz.electroniccity.data.model.db.WishesMdl;

import java.util.List;

@Dao
public interface WishDao {

    @Query("SELECT * FROM wish")
    List<WishesMdl> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(WishesMdl cart);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<WishesMdl> carts);

    @Delete
    void delete(WishesMdl cart);
}
