package com.base.yun.myproject.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.base.yun.myproject.libs.ActivityView;
import com.base.yun.myproject.viewmodel.ActivityViewModel;

/**
 * Created by YounghyubYun on 2017. 5. 7..
 */

public abstract class BaseActivity<V extends ActivityViewModel> extends AppCompatActivity implements ActivityView {

    private V mViewModel;

    protected abstract V createViewModel();

    public V getViewModel(){
        return mViewModel;
    }

    @CallSuper
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mViewModel = createViewModel();
    }

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.intent(getIntent());
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.onResume(this);
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
