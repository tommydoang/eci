package com.example.tomz.electroniccity.adapter.cart;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.db.shop.DBShopListResponse;
import com.example.tomz.electroniccity.utils.CommonUtils;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.util.List;

public class CartShopListAdapter extends RecyclerView.Adapter<CartShopListAdapter.MyViewHolder>{

    private List<DBShopListResponse> mDbShopModel;
    private UpdateQty mUpdateQtyListener;
    private Context mCtx;
    private int hargaTotal;
    private LayoutInflater inflater;

    public CartShopListAdapter(Context ctx, UpdateQty updateQty,
                               List<DBShopListResponse> mDbShopListResponseList) {
        this.mCtx = ctx;
        this.mDbShopModel = mDbShopListResponseList;
        this.mUpdateQtyListener = updateQty;
        inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public CartShopListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cart_shop_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartShopListAdapter.MyViewHolder holder, int position) {
        holder.tvQtyItem.setText(String.valueOf(mDbShopModel.get(position).getQtyItem()));
        Glide.with(mCtx).load(mDbShopModel.get(position).getImageProduk()).into(holder.ivImgProd);
        holder.tvNameProd.setText(mDbShopModel.get(position).getNameProduk());
        holder.tvSkuProd.setText(mDbShopModel.get(position).getSkuProduk());
        if (mDbShopModel.get(position).getQtyItem() > 1){
            holder.tvHargaNormalProduk.setText(CommonUtils.setCustomCurrency(mDbShopModel.get(position).getTotalHargaPerItem()));
            holder.tvHargaPromoProduk.setText(mDbShopModel.get(position).getHargaPromoProduk());
            hargaTotal += Integer.parseInt(mDbShopModel.get(position).getTotalHargaPerItem());
            getTotalHargaPerItem();
        } else {
            holder.tvHargaNormalProduk.setText(CommonUtils.setCustomCurrency(mDbShopModel.get(position).getHargaNormalProduk()));
            holder.tvHargaPromoProduk.setText(mDbShopModel.get(position).getHargaPromoProduk());
            hargaTotal += Integer.parseInt(mDbShopModel.get(position).getTotalHargaPerItem());
            getTotalHargaPerItem();
        }
    }

    @Override
    public int getItemCount() {
        if (mDbShopModel.size() > 0) {
            return mDbShopModel.size();
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView ivImgProd, ivDelete;
        private Button btn_plus, btn_minus;
        private CustomTextViewLatoFont tvNameProd, tvSkuProd, tvHargaNormalProduk, tvHargaPromoProduk, tvQtyItem;

        MyViewHolder(View itemView) {
            super(itemView);

            tvNameProd =itemView.findViewById(R.id.tvNamaProduk);
            tvSkuProd = itemView.findViewById(R.id.tvSkuArtikelProduk);
            tvHargaNormalProduk = itemView.findViewById(R.id.tvHargaProduk);
            tvHargaPromoProduk = itemView.findViewById(R.id.tvPromoProduk);
            ivImgProd = itemView.findViewById(R.id.ivImgProduk);
            tvQtyItem = itemView.findViewById(R.id.tv_item_qty);
            btn_plus = itemView.findViewById(R.id.increase);
            btn_minus = itemView.findViewById(R.id.decrease);
            ivDelete = itemView.findViewById(R.id.iv_icon_delete);

            btn_plus.setTag(R.integer.btn_plus_view, itemView);
            btn_minus.setTag(R.integer.btn_minus_view, itemView);
            ivDelete.setTag(R.integer.iv_delete_view , itemView);
            btn_plus.setOnClickListener(this);
            btn_minus.setOnClickListener(this);
            ivDelete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (v.getId() == btn_plus.getId()){

                Log.d("INCR tes1", getAdapterPosition()+"");
                if (mDbShopModel.get(getAdapterPosition()).getQtyItem() < 2) {
                    View tempview = (View) btn_plus.getTag(R.integer.btn_plus_view);
                    CustomTextViewLatoFont tv = tempview.findViewById(R.id.tv_item_qty);
                    int number = Integer.parseInt(tv.getText().toString()) + 1;
                    tv.setText(String.valueOf(number));
                    mDbShopModel.get(getAdapterPosition()).setQtyItem(number);

//                    if (mDbShopModel.get(getAdapterPosition()).getHargaPromoProduk().isEmpty()) {
                        CustomTextViewLatoFont tvHarga = tempview.findViewById(R.id.tvHargaProduk);
                        int harga = Integer.parseInt(mDbShopModel
                                .get(getAdapterPosition()).getHargaNormalProduk()) * number;
                        tvHarga.setText(CommonUtils.setCustomCurrency(String.valueOf(harga)));
                        mDbShopModel.get(getAdapterPosition()).setTotalHargaPerItem(String.valueOf(harga));

                        Log.d("hargaAdapINCR tes1", harga+"");
                        Log.d("idProdAdap tes1", mDbShopModel.get(getAdapterPosition()).getId_prod());
                        mUpdateQtyListener.updateQtyItem(number, harga, Integer.parseInt(mDbShopModel
                                .get(getAdapterPosition()).getId_prod()));
//                    }
                }

            } else if(v.getId() == btn_minus.getId()) {

                if (mDbShopModel.get(getAdapterPosition()).getQtyItem() > 1) {

                    View tempview = (View) btn_minus.getTag(R.integer.btn_minus_view);
                    CustomTextViewLatoFont tv = tempview.findViewById(R.id.tv_item_qty);
                    int number = Integer.parseInt(tv.getText().toString()) - 1;
                    tv.setText(String.valueOf(number));
                    mDbShopModel.get(getAdapterPosition()).setQtyItem(number);

                    CustomTextViewLatoFont tvHarga = tempview.findViewById(R.id.tvHargaProduk);
                    int harga = Integer.parseInt(mDbShopModel.get(getAdapterPosition()).getHargaNormalProduk()) * number;
                    tvHarga.setText(CommonUtils.setCustomCurrency(String.valueOf(harga)));
                    mDbShopModel.get(getAdapterPosition()).setHargaPromoProduk(String.valueOf(harga));

                    Log.d("hargaAdapDECR tes1", harga+"");
                    mUpdateQtyListener.updateQtyItem(number, harga, Integer.parseInt(mDbShopModel
                            .get(getAdapterPosition()).getId_prod()));
                }

            } else if (v.getId() == ivDelete.getId()){
                int idProd = Integer.parseInt(mDbShopModel.get(getAdapterPosition()).getId_prod());
                int position = getAdapterPosition();
                mDbShopModel.remove(position);
                notifyItemRemoved(position);
                mUpdateQtyListener.deleteCartItem(idProd);
            }
        }
    }

    public void addItems(List<DBShopListResponse> blogList) {
        mDbShopModel.addAll(blogList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mDbShopModel.clear();
    }

    private void getTotalHargaPerItem(){
        Intent intent = new Intent("cart_shop_list_adapter");
        intent.putExtra("harga_total_shop",hargaTotal);
        LocalBroadcastManager.getInstance(mCtx).sendBroadcast(intent);
    }

    public interface UpdateQty {
        void updateQtyItem(int mQty, int mTotal_Harga_Per_Item, int mId_prod);
        void deleteCartItem(int mId_prod);
    }
}
