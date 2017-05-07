package com.base.yun.myproject.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.base.yun.myproject.libs.ActivityView;

/**
 * Created by YounghyubYun on 2017. 5. 7..
 */

public interface ActivityViewModel<V extends ActivityView> extends ViewModel {

    void intent(final @NonNull Intent intent);

    void onCreate(final @NonNull Context context, final @Nullable Bundle savedInstanceState);

    void onResume(final @NonNull V view);

    void onPause();

    void onDestroy();
}