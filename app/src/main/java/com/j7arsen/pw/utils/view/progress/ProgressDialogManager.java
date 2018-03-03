package com.j7arsen.pw.utils.view.progress;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by arsen on 28.02.2018.
 */

public class ProgressDialogManager implements ProgressDialog.OnProgressDialogVisibleListener {

    private static ProgressDialogManager ourInstance = new ProgressDialogManager();

    private ProgressDialog mProgressDialog;

    private ProgressEvent mProgressEvent;

    private ProgressDialogManager() {
    }

    public static ProgressDialogManager getInstance() {
        return ourInstance;
    }

    private void subscribe() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog();
            mProgressDialog.setOnProgressDialogVisibleListener(this);
        }
    }

    public void unsubscribe() {
        if (mProgressDialog != null && mProgressDialog.isAdded()) {
            mProgressDialog.dismissAllowingStateLoss();
            mProgressDialog = null;
        }
    }

    public void startLoading(AppCompatActivity activity) {
        subscribe();
        if (!mProgressDialog.isVisible() && !mProgressDialog.isAdded()) {
            mProgressDialog.show(activity.getSupportFragmentManager(), ProgressDialog.TAG);
        }
        mProgressEvent = new ProgressEvent(ProgressEvent.START_PROGRESS);
        mProgressDialog.startLoading();
    }

    public void startLoading(Fragment fragment) {
        subscribe();
        try {
            mProgressDialog.show(fragment.getFragmentManager(), ProgressDialog.TAG);
            mProgressEvent = new ProgressEvent(ProgressEvent.START_PROGRESS);
            mProgressDialog.startLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void errorLoading(ProgressDialog.OnButtonClickCallbackListener onOkButtonClickCallbackListener, String errorMessage) {
        errorLoading(0, onOkButtonClickCallbackListener, errorMessage);
    }

    public void errorLoading(int dialogId, ProgressDialog.OnButtonClickCallbackListener onOkButtonClickCallbackListener, String errorMessage) {
        if (mProgressDialog != null && mProgressDialog.isAdded()) {
            mProgressDialog.errorLoading(dialogId, onOkButtonClickCallbackListener, errorMessage);
        } else {
            mProgressEvent = new ProgressEvent(ProgressEvent.ERROR_PROGRESS, errorMessage, onOkButtonClickCallbackListener);
        }
    }

    public void completeLoading() {
        if (mProgressDialog != null && mProgressDialog.isAdded()) {
            mProgressDialog.completeLoading();
            unsubscribe();
        } else {
            mProgressEvent = new ProgressEvent(ProgressEvent.COMPLETE_PROGRESS);
        }
    }

    @Override
    public void onProgressDialogVisible() {
        switch (mProgressEvent.getStatus()) {
            case ProgressEvent.COMPLETE_PROGRESS:
                completeLoading();
                break;
            case ProgressEvent.ERROR_PROGRESS:
                errorLoading(mProgressEvent.getOnButtonClickCallbackListener(), mProgressEvent.getErrorMessage());
                break;
        }
    }
}
