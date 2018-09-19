package com.example.tomz.electroniccity.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "cart")
public class CartMdl {

    //buat model produk jd disini ada sku_produk, nama_produk,
    // gambar_produk, harga_produk, spek barang, deskripsi barang
    @PrimaryKey
    @NonNull
    public String id_prod;

    @ColumnInfo(name = "sku")
    public String sku;


}
