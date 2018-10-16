package com.example.tomz.electroniccity.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.tomz.electroniccity.data.local.db.dao.CartDao;
import com.example.tomz.electroniccity.data.local.db.dao.WishDao;
import com.example.tomz.electroniccity.data.model.db.CartMdl;
import com.example.tomz.electroniccity.data.model.db.WishesMdl;

@Database(entities = {CartMdl.class, WishesMdl.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CartDao cartDao();

    public abstract WishDao wishDao();

}
