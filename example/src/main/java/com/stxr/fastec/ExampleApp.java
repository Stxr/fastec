package com.stxr.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.stxr.latte.Latte;

/**
 * Created by stxr on 2018/7/8.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcons(new FontAwesomeModule())
                .withApiHost("http://127.0.0.1/")
                .configure();

    }
}
