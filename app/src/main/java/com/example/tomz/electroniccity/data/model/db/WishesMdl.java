package com.example.tomz.electroniccity.data.model.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "wish")
public class WishesMdl {

    //sama kaaya cartmdl
    @PrimaryKey
    @NonNull
    public String sku;

}
