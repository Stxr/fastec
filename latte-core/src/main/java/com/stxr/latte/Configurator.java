package com.stxr.latte;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by stxr on 2018/7/8.
 * 初始化配置类
 * 基本思想为建立一个map，然后初始化的时候存入值，用的时候取值
 */

public class Configurator {
    //存取配置的map
    public static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    public static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

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
        //初始化图标
        initIcons();
        //配置完毕
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }
    //从数组里不断添加ICON
    private void initIcons() {
        //判断list里有图标
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }
    //外部调用图标的接口
    public final Configurator withIcons(IconFontDescriptor fontDescriptor) {
        ICONS.add(fontDescriptor);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,Please call configure first");
        }
    }
    @SuppressWarnings("unchecked")
    final <T> T getConfigurations(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }
}
