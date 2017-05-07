package com.base.yun.myproject;

import android.os.Bundle;

import com.base.yun.myproject.base.BaseActivity;

public class MainActivity extends BaseActivity<MainActivityViewModel> {

    @Override
    protected MainActivityViewModel createViewModel() {
        return new MainActivityViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
