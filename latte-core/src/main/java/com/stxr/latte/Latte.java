package com.stxr.latte;

import android.content.Context;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by stxr on 2018/7/8.
 * 初始化配置
 */

public class Latte {
    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplication() {
        return Configurator.getInstance().getConfigurations(ConfigType.APPLICATION_CONTEXT);
    }

}
