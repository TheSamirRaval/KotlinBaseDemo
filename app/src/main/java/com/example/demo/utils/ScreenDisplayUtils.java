package com.example.demo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by pc on 03-02-2015.
 */
public class ScreenDisplayUtils {

    private static final float MINIMUM_WIDTH = 300f;   //   in dp
    private static final float MINIMUM_HEIGHT = 500f;   //   in dp

    private float mHeight;
    private float mWidth;
    private Context mContext;

    public ScreenDisplayUtils(Context context){
        mContext=context;
       DisplayMetrics displayMetrics= context.getResources().getDisplayMetrics();
        mHeight=displayMetrics.heightPixels;
        mWidth = displayMetrics.widthPixels;
    }

    public float getHeight() {
        return mHeight;
    }

    public float getWidth() {
        return mWidth;
    }


    public static float calculateDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        float px = dp * (metrics.densityDpi / 160f);
        return px;

    }

    public  float calculateDpToPixel(float dp) {
        Resources resources = mContext.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        float px = dp * (metrics.densityDpi / 160f);
        return px;

    }




}
