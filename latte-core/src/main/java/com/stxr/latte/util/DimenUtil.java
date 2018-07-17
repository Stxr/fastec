package com.stxr.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.stxr.latte.app.Latte;

/**
 * Created by stxr on 2018/7/16.
 */

public class DimenUtil {
    public static int getScreenWith() {
        Resources resources = Latte.getApplication().getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight() {
        Resources resources = Latte.getApplication().getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
