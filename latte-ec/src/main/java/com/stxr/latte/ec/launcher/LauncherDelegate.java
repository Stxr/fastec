package com.stxr.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.stxr.latte.delegates.LatteDelegate;
import com.stxr.latte.ec.R;
import com.stxr.latte.ec.R2;
import com.stxr.latte.util.timer.BaseTimerTask;
import com.stxr.latte.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;

/**
 * Created by stxr on 2018/7/27.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {
    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView tv_timer;

    private int count = 5;
    private Timer timer;

    @Override
    protected Object setLayout() {
        return R.layout.launcher_delegate;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        timer = new Timer();
        BaseTimerTask timerTask = new BaseTimerTask(this);
        timer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void onTimer() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                count--;
                tv_timer.setText(MessageFormat.format("跳过\n{0}s", count));
                if (count <= 0) {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                }
            }
        });
    }
}
