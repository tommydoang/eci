package com.example.tomz.electroniccity.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by tomz on 4/30/2017.
 */

public class CustomTextViewLatoFont extends AppCompatTextView {

    private static final String TAG = "TextView";

    public CustomTextViewLatoFont(Context context) {
        super(context);
    }

    public CustomTextViewLatoFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont2(context);
    }

    public CustomTextViewLatoFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont2(context);
    }

    private void setCustomFont2(Context context) {
        Typeface customFont = FontCache.getTypeface("Lato_Regular.ttf", context);
        setTypeface(customFont);
    }

}
