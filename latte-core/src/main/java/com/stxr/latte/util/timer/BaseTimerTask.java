package com.stxr.latte.util.timer;

import java.util.TimerTask;

/**
 * Created by stxr on 2018/7/27.
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener iTimerListener;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.iTimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (iTimerListener != null) {
            iTimerListener.onTimer();
        }
    }
}
