package com.j7arsen.pw.utils.view.progress;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.j7arsen.pw.R;

/**
 * Created by arsen on 28.02.2018.
 */

public class ProgressView extends RelativeLayout {

    private ProgressBar pbLoad;
    private TextView tvProgressErrorTitle;
    private TextView tvProgressError;
    private ImageView ivErrorIcon;
    private Button btnProgressErrorAction;
    private LinearLayout llProgressError;
    private RelativeLayout rlProgress;
    private OnRetryListener mOnRetryListener;

    private String mErrorMessage;

    public void setOnRetryListener(OnRetryListener onRetryListener) {
        this.mOnRetryListener = onRetryListener;
    }

    public ProgressView(Context context) {
        super(context);
        init(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.include_progress_layout, this);
    }

    public void startLoading() {
        this.setVisibility(VISIBLE);
        rlProgress.setVisibility(View.VISIBLE);
        pbLoad.setVisibility(View.VISIBLE);
        llProgressError.setVisibility(View.GONE);
    }

    public void completeLoading() {
        this.setVisibility(GONE);
        rlProgress.setVisibility(View.GONE);
        pbLoad.setVisibility(View.GONE);
        llProgressError.setVisibility(View.GONE);
    }

    public void errorLoading(String errorMessage) {
        this.setVisibility(VISIBLE);
        this.mErrorMessage = errorMessage;
        rlProgress.setVisibility(View.VISIBLE);
        pbLoad.setVisibility(View.GONE);
        if (mErrorMessage != null) {
            llProgressError.setVisibility(View.VISIBLE);
            tvProgressError.setText(mErrorMessage);
        }
    }

    public void gone() {
        this.setVisibility(GONE);
        rlProgress.setVisibility(View.GONE);
        llProgressError.setVisibility(View.GONE);
        pbLoad.setVisibility(View.GONE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        pbLoad = (ProgressBar) this.findViewById(R.id.pb_load);
        ivErrorIcon = (ImageView) this.findViewById(R.id.iv_error_icon);
        tvProgressErrorTitle = (this).findViewById(R.id.tv_progress_error_title);
        tvProgressError = (TextView) this.findViewById(R.id.tv_progress_error);
        btnProgressErrorAction = (Button) this.findViewById(R.id.btn_progress_error_retry);
        llProgressError = (LinearLayout) this.findViewById(R.id.ll_progress_error);
        rlProgress = (RelativeLayout) this.findViewById(R.id.rl_progress);

        btnProgressErrorAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRetryListener != null) {
                    mOnRetryListener.onRetry();
                }
            }
        });
    }

    public interface OnRetryListener {
        void onRetry();
    }
}
