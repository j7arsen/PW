package com.j7arsen.pw.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.util.Arrays;
import java.util.List;

/**
 * Created by arsen on 26.02.2018.
 */

public class ResUtils {

    private Context context;

    public ResUtils(Context context) {
        this.context = context;
    }

    public String getString(int resId) {
        return context.getResources().getString(resId);
    }

    public boolean getBoolean(int resId) {
        return context.getResources().getBoolean(resId);
    }

    public List<String> getStringList(int resId) {
        return Arrays.asList(context.getResources().getStringArray(resId));
    }

    public int getInt(int resId) {
        return context.getResources().getInteger(resId);
    }

    public int getColor(int resId) {
        return ContextCompat.getColor(context, resId);
    }

    public int getDimenInPx(int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    public Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(context, resId);
    }

}
