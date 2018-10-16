package com.example.tomz.electroniccity.adapter.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.local.db.dao.CartDao;
import com.example.tomz.electroniccity.data.model.db.shop.DBShopListResponse;
//import com.example.tomz.electroniccity.databinding.CartShopListItemBinding;
import com.example.tomz.electroniccity.utils.CommonUtils;
import com.example.tomz.electroniccity.utils.base.BaseViewHolder;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CartShopListAdapter extends RecyclerView.Adapter<CartShopListAdapter.MyViewHolder>{

    private List<DBShopListResponse> mDbShopModel;
    private CustomTextViewLatoFont mTvQtyItem, mTvHargaNormal, mTvHargaPromo;
    private int hargaNormalTemp = 0, hargaPromoTemp = 0, layoutPosition;
    private String hargaNormal, hargaPromo;
    private ArrayList<String> listHargaNormal = new ArrayList<>();
    private ArrayList<String> listHargaPromo = new ArrayList<>();
    private Button mBtnIncr, mBtnDecr;
    private String text;
    @Inject CartDao cartDao;
    private UpdateQty mUpdateQtyListener;
//    private CartShopListItemBinding mBinding;
    private Context mCtx;
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
//        /*CartShopListItemBinding*/ mBinding = DataBindingUtil
//                .inflate(LayoutInflater.from(parent.getContext()),
//                        R.layout.cart_shop_list_item, parent, false);
//        return new BindingHolder(mBinding);
        View view = inflater.inflate(R.layout.cart_shop_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartShopListAdapter.MyViewHolder holder, int position) {
        holder.tvQtyItem.setText(String.valueOf(mDbShopModel.get(position).getQtyItem()));
        Glide.with(mCtx).load(mDbShopModel.get(position).getImageProduk()).into(holder.ivImgProd);
        holder.tvNameProd.setText(mDbShopModel.get(position).getNameProduk());
        holder.tvSkuProd.setText(mDbShopModel.get(position).getSkuProduk());
        holder.tvHargaNormalProduk.setText(CommonUtils.setCustomCurrency(mDbShopModel.get(position).getHargaNormalProduk()));
        holder.tvHargaPromoProduk.setText(mDbShopModel.get(position).getHargaPromoProduk());
    }

//    @Override
//    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//        holder.onBind(position);
//    }

    @Override
    public int getItemCount() {
        if (mDbShopModel.size() > 0) {
            return mDbShopModel.size();
        } else {
            return 0;
        }
    }

//    public class BindingHolder extends BaseViewHolder implements
//            CartItemViewModel.CartItemViewModelNavigatorListener, View.OnClickListener {
//
//        private CartShopListItemBinding binding;
//        private CartItemViewModel cartItemViewModel;
//        private DBShopListResponse dmi;
//        public TextView mTvHargaNorm;
//
//        public BindingHolder(CartShopListItemBinding binding) {
//            super(binding.getRoot());
//            this.binding = binding;
//        }
//
//        @Override
//        public void onBind(int position) {
//            mTvHargaNormal = binding.tvHargaProduk;
//            mTvHargaPromo = binding.tvPromoProduk;
//            mTvQtyItem = binding.tvItemQty;
//            mBtnIncr = binding.increase;
//            mBtnDecr = binding.decrease;
//
//            mBtnIncr.setTag(R.integer.btn_plus_view,itemView);
//            mBtnDecr.setTag(R.integer.btn_minus_view,itemView);
//            mTvQtyItem.setText(String.valueOf(mDbShopModel.get(position).getQtyItem()));
//            Log.d("onBind tes1", String.valueOf(mDbShopModel.get(position).getQtyItem()));
//
//            dmi = mDbShopModel.get(position);
//            cartItemViewModel = new CartItemViewModel(dmi,this);
//            binding.setCart(cartItemViewModel);
//            binding.executePendingBindings();
//
//            mBtnIncr.setOnClickListener(this);
//            mBtnDecr.setOnClickListener(this);
//
//            Log.d("aPos tes1", getAdapterPosition()+"");
//            Log.d("lPos tes1", getLayoutPosition()+"");
//        }
//
//        @Override
//        public void onIncrClick(int qty, String harga) {
//            int qtyTemp = qty + 1;
////            if (qtyTemp <= 2) {
//                Log.d("qtyItem tes1", qtyTemp + "");
//                mTvQtyItem.setText(String.valueOf(qtyTemp));
//                mDbShopModel.get(getAdapterPosition()).setQtyItem(qtyTemp);
//
//                Log.d("onIncrClick tes1", harga);
//                hargaNormalTemp = Integer.parseInt(harga) * qtyTemp;
//                mTvHargaNormal.setText(CommonUtils.setCustomCurrency(String.valueOf(hargaNormalTemp)));
////                mUpdateQtyListener.updateQtyItem(qtyTemp, Integer.parseInt(dmi.getId_prod()));
//            Log.d("onIncr tes1", getAdapterPosition()+"");
////            }
//        }
//
//        @Override
//        public void onDecrClick(int qty, String harga) {
//            Log.d("onDecrClick tes1 1", qty+"");
//
////            if (qty > 1) {
//                int qtyTemp = qty - 1;
//                Log.d("onBtnClick tes1", qtyTemp + "");
//                mTvQtyItem.setText(String.valueOf(qtyTemp));
//
//                Log.d("onDecrClick tes1 2", harga);
//                hargaNormalTemp = Integer.parseInt(harga)*qtyTemp;
//                mTvHargaNormal.setText(CommonUtils.setCustomCurrency(String.valueOf(hargaNormalTemp)));
////                mUpdateQtyListener.updateQtyItem(qtyTemp, Integer.parseInt(dmi.getId_prod()));
////            }
//        }
//
//        @Override
//        public void onDelClick(int id_prod) {
//            Log.d("onDelClick tes1", id_prod+"");
//            Log.d("onDEL tes1", getAdapterPosition()+"");
//            mDbShopModel.remove(getAdapterPosition());
//            notifyItemRemoved(getAdapterPosition());
//            mUpdateQtyListener.deleteCartItem(id_prod);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (view.getId() == mBtnIncr.getId()){
//                View tmpView = (View) mBtnIncr.getTag(R.integer.btn_plus_view);
//                CustomTextViewLatoFont tvQtyItem = tmpView.findViewById(R.id.tv_item_qty);
//                int qtyTemp = Integer.parseInt(tvQtyItem.getText().toString()) + 1;
////                tvQtyItem.setText(String.valueOf(qtyTemp));
//                mDbShopModel.get(getAdapterPosition()).setQtyItem(qtyTemp);
//                tvQtyItem.setText(mDbShopModel.get(getAdapterPosition()).getQtyItem());
//                Log.d("aPos tes1", getAdapterPosition()+"");
//                Log.d("qtyTemp tes1", String.valueOf(qtyTemp));
//            }
//        }
//    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView ivImgProd, ivDelete;
        private Button btn_plus, btn_minus;
        private CustomTextViewLatoFont tvNameProd, tvSkuProd, tvHargaNormalProduk, tvHargaPromoProduk, tvQtyItem;

        public MyViewHolder(View itemView) {
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

                    CustomTextViewLatoFont tvHarga = tempview.findViewById(R.id.tvHargaProduk);
                    int harga = Integer.parseInt(mDbShopModel.get(getAdapterPosition()).getHargaNormalProduk()) * number;
                    tvHarga.setText(CommonUtils.setCustomCurrency(String.valueOf(harga)));
                    mDbShopModel.get(getAdapterPosition()).setHargaPromoProduk(String.valueOf(harga));

                    mUpdateQtyListener.updateQtyItem(number, Integer.parseInt(mDbShopModel
                            .get(getAdapterPosition()).getId_prod()));
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

                    mUpdateQtyListener.updateQtyItem(number, Integer.parseInt(mDbShopModel
                            .get(getAdapterPosition()).getId_prod()));
                }
            } else if (v.getId() == ivDelete.getId()){
//                mDbShopModel.remove(getAdapterPosition());
//                notifyItemRemoved(getAdapterPosition());
//                mUpdateQtyListener.deleteCartItem(Integer.parseInt(mDbShopModel
//                        .get(getAdapterPosition()).getId_prod()));
                Log.d("ivDel tes1", getAdapterPosition()+"");
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

    public int getHargaNormalTemp(){
        return hargaNormalTemp;
    }

    public int getHargaPromoTemp() {
        return hargaPromoTemp;
    }

//    public int getQtyItem(){
//        return qtyItem;
//    }

    public interface UpdateQty {
        void updateQtyItem(int mQty, int mId_prod);
        void deleteCartItem(int mId_prod);
    }
}
