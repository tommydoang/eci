package com.example.tomz.electroniccity.page.side_menu.value;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.data.model.api.valueadded.DataValueAddResponse;

public class ValueAddItemViewModel {

    public final ObservableField<String> imageURL;

    public ValueAddItemViewModel(DataValueAddResponse dataValueAddResponse){
        imageURL = new ObservableField<>(dataValueAddResponse.getImage_value());
    }

    @BindingAdapter({"imgValueAdd"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

}
