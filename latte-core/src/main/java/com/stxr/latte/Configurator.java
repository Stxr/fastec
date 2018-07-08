package com.stxr.latte;

import android.os.Handler;
import android.view.LayoutInflater;

import java.util.StringTokenizer;
import java.util.WeakHashMap;

/**
 * Created by stxr on 2018/7/8.
 * 初始化配置类
 * 基本思想为建立一个map，然后初始化的时候存入值，用的时候取值
 */

public class Configurator {
    //存取配置的map
    public static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator() {
        //默认初始化未配置
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    //单例模式
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final void configure() {
        //配置完毕
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public WeakHashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private void checkConfiguation() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,Please call configure first");
        }
    }
    @SuppressWarnings("unchecked")
    final <T> T getConfigurations(Enum<ConfigType> key) {
        checkConfiguation();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
