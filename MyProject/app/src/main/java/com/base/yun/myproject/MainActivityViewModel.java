package com.base.yun.myproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.base.yun.myproject.libs.ActivityView;
import com.base.yun.myproject.viewmodel.ActivityViewModel;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
/**
 * Created by YounghyubYun on 2017. 5. 7..
 */

public final class MainActivityViewModel<V extends ActivityView> implements ActivityViewModel<V> {

    //viewmodel 구독자
    private final PublishSubject<V> mViewPublishSubject = PublishSubject.create();

    //filter, view가 null이 아닌 것은 배제한다
    private final Observable<V> mViewObservable = mViewPublishSubject.filter(v -> v != null);


    private static final String TAG = "MainActivityViewModel";

    private final PublishSubject<Intent> intent = PublishSubject.create();

    @Override
    public void intent(@NonNull final Intent intent) {
        this.intent.onNext(intent);
    }

    @CallSuper
    @Override
    public void onCreate(@NonNull Context context, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate " + this.toString());
        onDropView();
    }

    @CallSuper
    @Override
    public void onResume(@NonNull V view) {
        Log.d(TAG, "onResume " + this.toString());
        mViewPublishSubject.onNext(view);
    }

    @CallSuper
    @Override
    public void onPause() {
        onDropView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        mViewPublishSubject.onComplete();
    }

    private void onDropView(){
        mViewPublishSubject.onNext(null);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @NonNull
    protected Observable<Intent> intentObservable() {
        return intent;
    }
}
