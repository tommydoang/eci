package com.example.tomz.electroniccity.adapter.tab2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tomz.electroniccity.R;
import com.example.tomz.electroniccity.data.model.tabsubcategory.Tab2AllSubCategoryModel;
import com.example.tomz.electroniccity.utils.font.CustomTextViewLatoFont;

import java.util.ArrayList;

public class Tab2AllSubCategoryAdapter extends BaseAdapter {

    private Context ctx;
    private ArrayList<Tab2AllSubCategoryModel> tab2AllSubCategoryModels;

    public Tab2AllSubCategoryAdapter(Context ctx,
                                     ArrayList<Tab2AllSubCategoryModel> tab2AllSubCategoryModels) {
        this.ctx = ctx;
        this.tab2AllSubCategoryModels = tab2AllSubCategoryModels;
    }

    @Override
    public int getCount() {
        return tab2AllSubCategoryModels.size();
    }

    @Override
    public Object getItem(int position) {
        return tab2AllSubCategoryModels.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) {
            view= LayoutInflater.from(ctx).inflate(R.layout.tab2_all_sub_category,viewGroup,false);
        }

        final Tab2AllSubCategoryModel s= (Tab2AllSubCategoryModel) this.getItem(i);

        ImageView img= view.findViewById(R.id.ivImage);
        CustomTextViewLatoFont nameTxt= view.findViewById(R.id.tvTextCat);

        Bitmap bMap = BitmapFactory.decodeResource(ctx.getResources(), s.getImage());
        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap,
                (int)(bMap.getWidth()*0.2), (int)(bMap.getHeight()*0.2), true);

        //BIND
        nameTxt.setText(s.getName());
        img.setImageBitmap(bMapScaled);

        return view;
    }
}
