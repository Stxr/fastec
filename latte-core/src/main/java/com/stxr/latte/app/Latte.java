package com.stxr.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by stxr on 2018/7/8.
 * 初始化配置
 */

public class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static <T> T getConfiguration(Enum<ConfigType> key) {
        return Configurator.getInstance().getConfigurations(key);
    }
    public static Context getApplication() {
        return Configurator.getInstance().getConfigurations(ConfigType.APPLICATION_CONTEXT);
    }

}
