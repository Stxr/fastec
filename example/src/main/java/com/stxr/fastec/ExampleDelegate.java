package com.stxr.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.stxr.latte.delegates.LatteDelegate;

/**
 * Created by stxr on 2018/7/10.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    protected Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

}
