package com.j7arsen.pw.utils.view.progress;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.j7arsen.pw.R;
import com.j7arsen.pw.base.BaseDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by arsen on 28.02.2018.
 */

public class ProgressDialog extends BaseDialogFragment {

    public static final String TAG = "ProgressDialog.TAG";

    @BindView(R.id.pb_dialog_load)
    ProgressBar pbLoad;
    @BindView(R.id.iv_error_logo)
    ImageView ivErrorLogo;
    @BindView(R.id.tv_dialog_progress_error_title)
    TextView tvDialogProgressErrorTitle;
    @BindView(R.id.tv_dialog_progress_error)
    TextView tvDialogProgressError;
    @BindView(R.id.ll_dialog_progress_error)
    LinearLayout llDialogProgressError;
    @BindView(R.id.rl_dialog_progress)
    RelativeLayout rlDialogProgress;

    private String mErrorMessage;

    private int mDialogId;

    private OnProgressDialogVisibleListener mOnProgressDialogVisibleListener;

    private OnButtonClickCallbackListener mOnButtonClickCallbackListener;

    public void setOnProgressDialogVisibleListener(OnProgressDialogVisibleListener onProgressDialogVisibleListener) {
        this.mOnProgressDialogVisibleListener = onProgressDialogVisibleListener;
    }

    public void setOnButtonClickCallbackListener(OnButtonClickCallbackListener oButtonClickCallbackListener) {
        this.mOnButtonClickCallbackListener = oButtonClickCallbackListener;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_progress;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if(mOnProgressDialogVisibleListener != null){
            mOnProgressDialogVisibleListener.onProgressDialogVisible();
        }
        return dialog;
    }

    @OnClick({R.id.btn_progress_error_ok})
    void okClick(View view) {
        switch (view.getId()) {
            case R.id.btn_progress_error_ok:
                if (mOnButtonClickCallbackListener != null) {
                    mOnButtonClickCallbackListener.onButtonClick(mDialogId);
                }
                break;
        }
    }

    public void startLoading() {
        setCancelable(false);
    }

    public void completeLoading() {
        setCancelable(true);
        pbLoad.setVisibility(View.GONE);
        rlDialogProgress.setVisibility(View.GONE);
        llDialogProgressError.setVisibility(View.GONE);
    }

    public void errorLoading(int dialogId, OnButtonClickCallbackListener onOkButtonClickCallbackListener, String errorMessage) {
        setCancelable(true);
        this.mDialogId = dialogId;
        this.mOnButtonClickCallbackListener = onOkButtonClickCallbackListener;
        this.mErrorMessage = errorMessage;
        pbLoad.setVisibility(View.GONE);
        rlDialogProgress.setVisibility(View.VISIBLE);
        llDialogProgressError.setVisibility(View.VISIBLE);
        if (mErrorMessage != null) {
            tvDialogProgressError.setText(mErrorMessage);
        }
    }

    public interface OnButtonClickCallbackListener {
        void onButtonClick(int dialogId);
    }

    public interface OnProgressDialogVisibleListener {
        void onProgressDialogVisible();
    }
}
