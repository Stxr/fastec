package com.stxr.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.stxr.latte.app.Latte;

/**
 * Created by stxr on 2018/7/8.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化，将数据都传入一个Map中
        Latte.init(this)
                .withIcons(new FontAwesomeModule())
                .withApiHost("http://127.0.0.1/")
                .configure();

    }
}
