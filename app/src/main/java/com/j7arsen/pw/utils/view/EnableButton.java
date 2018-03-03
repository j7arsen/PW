package com.j7arsen.pw.utils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.j7arsen.pw.R;

/**
 * Created by arsen on 28.02.2018.
 */

public class EnableButton extends AppCompatButton {

    private Drawable mEnableResId;
    private Drawable mDisableResId;
    private boolean mEnable;

    public EnableButton(Context context) {
        super(context);
        initView(context, null);
    }

    public EnableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public EnableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet atrs) {
        TypedArray typedArray = context.obtainStyledAttributes(atrs, R.styleable.EnableButton);
        mEnableResId = typedArray.getDrawable(R.styleable.EnableButton_enableDrawable);
        mDisableResId = typedArray.getDrawable(R.styleable.EnableButton_disableDrawable);
        mEnable = typedArray.getBoolean(R.styleable.EnableButton_enable, false);
        typedArray.recycle();
        initButton();
    }

    private void initButton(){
        if(mEnableResId != null && mDisableResId != null){
            enable(mEnable);
        } else{
            enable(mEnable, R.drawable.selector_button, R.drawable.shape_rounded_disabled_button);
        }
    }

    public void enable(boolean isEnable){
        if(isEnable){
            this.setEnabled(true);
            setBackgroundDrawable(mEnableResId);
        } else{
            this.setEnabled(false);
            setBackgroundDrawable(mDisableResId);
        }

    }

    public void enable(boolean isEnable, @DrawableRes int enableResId, @DrawableRes int disableResId){
        if(isEnable){
            this.setEnabled(true);
            setBackgroundResource(enableResId);
        } else{
            this.setEnabled(false);
            setBackgroundResource(disableResId);
        }

    }

}
