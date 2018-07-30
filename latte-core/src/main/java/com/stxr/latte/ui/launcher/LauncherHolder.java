package com.stxr.latte.ui.launcher;

import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.stxr.latte.R;

/**
 * Created by stxr on 2018/7/30.
 */

public class LauncherHolder extends Holder<Integer> {
    private AppCompatImageView imageView;
    public LauncherHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        imageView = itemView.findViewById(R.id.iv_launcher_scroller);
    }

    @Override
    public void updateUI(Integer data) {
        imageView.setBackgroundResource(data);
    }
}
