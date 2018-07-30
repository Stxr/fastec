package com.stxr.latte.ui.launcher;

import android.support.v7.widget.ContentFrameLayout;
import android.view.View;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.stxr.latte.R;

/**
 * Created by stxr on 2018/7/30.
 */

public class LauncherHolderCreator implements CBViewHolderCreator {
    @Override
    public Holder createHolder(View itemView) {
        return new LauncherHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.launcher_scoller;
    }
}
