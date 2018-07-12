package com.stxr.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.stxr.latte.delegates.LatteDelegate;
import com.stxr.latte.net.RestClient;
import com.stxr.latte.net.callback.IError;
import com.stxr.latte.net.callback.ISuccess;

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

    private void testRestClent() {
        RestClient.builder()
                .url("")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String Message) {

                    }
                })
                .build();
    }

}
