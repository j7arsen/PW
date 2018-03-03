package com.j7arsen.pw.utils.screencreator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.j7arsen.pw.base.BaseFragment;

/**
 * Created by arsen on 26.02.2018.
 */

public interface IScreenCreator {

    void startActivity(Activity activity, Class initClass);

    void startActivity(Fragment fragment, Activity activity, Class initClass);

    void startActivity(Activity activity, Class initClass, int requestCode);

    void startActivity(Fragment fragment, Activity activity, Class initClass, int requestCode);

    void startActivity(Activity activity, Class initClass, Bundle bundle);

    void startActivity(Fragment fragment, Activity activity, Class initClass, Bundle bundle);

    void startActivity(Activity activity, Class initClass, Bundle bundle, int requestCode);

    void startActivity(Fragment fragment, Activity activity, Class initClass, Bundle bundle, int requestCode);

    <T extends BaseFragment> T newInstance(Class<T> mClass);

    <T extends BaseFragment> T newInstance(Class<T> mClass, Bundle bundle);

}
