package com.j7arsen.pw.utils;

import android.app.Activity;

import com.j7arsen.pw.R;

/**
 * Created by arsen on 26.02.2018.
 */

public class UI {

    public static void animationOpenActivity(Activity activity) {
        activity.overridePendingTransition(R.anim.screen_in, R.anim.screen_out);
    }

    public static void animationCloseActivity(Activity activity) {
        activity.overridePendingTransition(R.anim.screen_back_in, R.anim.screen_back_out);
    }

    public static void openActivityWithoutAnimation(Activity activity) {
        activity.overridePendingTransition(0, 0);
    }

}
