package com.stxr.fastec;

import android.app.Application;

import com.stxr.latte.Latte;

/**
 * Created by stxr on 2018/7/8.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();

    }
}
