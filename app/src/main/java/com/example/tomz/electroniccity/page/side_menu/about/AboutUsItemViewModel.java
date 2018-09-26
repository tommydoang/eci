package com.example.tomz.electroniccity.page.side_menu.about;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tomz.electroniccity.data.model.api.about.DataAboutUsResponse;

public class AboutUsItemViewModel {

    public final ObservableField<String> id;
    public final ObservableField<String> title;
    public final ObservableField<String> description;
    public final ObservableField<String> image;
    public final ObservableField<String> tab;
    public final ObservableField<String> sort;
    public final ObservableField<String> isPublish;

    public AboutUsItemViewModel(DataAboutUsResponse dataAboutUsResponse) {
        id = new ObservableField<>(dataAboutUsResponse.getId());
        title = new ObservableField<>(dataAboutUsResponse.getTitle());
        description = new ObservableField<>(dataAboutUsResponse.getDescription());
        image = new ObservableField<>(dataAboutUsResponse.getImage());
        tab = new ObservableField<>(dataAboutUsResponse.getTab());
        sort = new ObservableField<>(dataAboutUsResponse.getSort());
        isPublish = new ObservableField<>(dataAboutUsResponse.getPublish());

        Log.d("img ItemVM tes1", image+"");
    }

    @BindingAdapter({"ivAboutUs"})
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).asBitmap().load(url).into(view);
    }

}
