package com.example.tomz.electroniccity.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "cart")
public class CartMdl {

    //buat model produk jd disini ada sku_produk, nama_produk,
    // gambar_produk, harga_produk, spek barang, deskripsi barang

    @SuppressWarnings("NullableProblems")
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id_prod")
    private String id_prod;

    @ColumnInfo(name = "sku")
    private String skuProduk;

    @ColumnInfo(name = "name_prod")
    private String nameProduk;

    @ColumnInfo(name = "harga_prod")
    private String hargaNormalProduk;

    @ColumnInfo(name = "harga_spc_prod")
    private String hargaPromoProduk;

    @ColumnInfo(name = "desk_prod")
    private String deskripsiProd;

    @ColumnInfo(name = "spek_prod")
    private String speksifikasiProd;

    @ColumnInfo(name = "image_produk")
    private String imageProduk;

    @ColumnInfo(name = "qty")
    private int qtyItem;

    public CartMdl(){}

    public CartMdl(@NonNull String id_prod, String skuProduk, String nameProduk,
                   String hargaNormalProduk, String hargaPromoProduk, String deskripsiProd,
                   String speksifikasiProd, String imageProduk, int qtyItem) {
        this.id_prod = id_prod;
        this.skuProduk = skuProduk;
        this.nameProduk = nameProduk;
        this.hargaNormalProduk = hargaNormalProduk;
        this.hargaPromoProduk = hargaPromoProduk;
        this.deskripsiProd = deskripsiProd;
        this.speksifikasiProd = speksifikasiProd;
        this.imageProduk = imageProduk;
        this.qtyItem = qtyItem;
    }

    @NonNull
    public String getId_prod() {
        return id_prod;
    }

    public void setId_prod(@NonNull String id_prod) {
        this.id_prod = id_prod;
    }

    public String getSkuProduk() {
        return skuProduk;
    }

    public void setSkuProduk(String skuProduk) {
        this.skuProduk = skuProduk;
    }

    public String getNameProduk() {
        return nameProduk;
    }

    public void setNameProduk(String nameProduk) {
        this.nameProduk = nameProduk;
    }

    public String getHargaNormalProduk() {
        return hargaNormalProduk;
    }

    public void setHargaNormalProduk(String hargaNormalProduk) {
        this.hargaNormalProduk = hargaNormalProduk;
    }

    public String getHargaPromoProduk() {
        return hargaPromoProduk;
    }

    public void setHargaPromoProduk(String hargaPromoProduk) {
        this.hargaPromoProduk = hargaPromoProduk;
    }

    public String getDeskripsiProd() {
        return deskripsiProd;
    }

    public void setDeskripsiProd(String deskripsiProd) {
        this.deskripsiProd = deskripsiProd;
    }

    public String getSpeksifikasiProd() {
        return speksifikasiProd;
    }

    public void setSpeksifikasiProd(String speksifikasiProd) {
        this.speksifikasiProd = speksifikasiProd;
    }

    public String getImageProduk() {
        return imageProduk;
    }

    public void setImageProduk(String imageProduk) {
        this.imageProduk = imageProduk;
    }

    public int getQtyItem() {
        return qtyItem;
    }

    public void setQtyItem(int qtyItem) {
        this.qtyItem = qtyItem;
    }
}
