package com.j7arsen.pw.utils.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.j7arsen.pw.R;

/**
 * Created by arsen on 01.03.2018.
 */

public class EmptyView extends LinearLayout {

    ImageView ivIcon;
    TextView tvTitle;

    public EmptyView(Context context) {
        super(context);
        initView(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        int defaultMargin = dpToPx(8, context);
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(defaultMargin, defaultMargin, defaultMargin, defaultMargin);
        ivIcon = new ImageView(context);
        ivIcon.setLayoutParams(params);
        ivIcon.setImageResource(R.drawable.ic_empty);

        tvTitle = new TextView(context);
        tvTitle.setText(R.string.label_no_data);
        tvTitle.setTextColor(Color.WHITE);
        tvTitle.setLayoutParams(params);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, spToPx(20, context));
        tvTitle.setTypeface(Typeface.DEFAULT_BOLD);
        addView(ivIcon);
        addView(tvTitle);
        invalidate();

    }

    public void hide() {
        setVisibility(GONE);
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    private int dpToPx(float valueInDp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    private int spToPx(float valueInDp, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, valueInDp, metrics);
    }
}
