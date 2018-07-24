package com.stxr.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.stxr.latte.app.Latte;
import com.stxr.latte.delegates.LatteDelegate;
import com.stxr.latte.net.RestClient;
import com.stxr.latte.net.callback.IError;
import com.stxr.latte.net.callback.IFailure;
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
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Latte.getApplication(), response, Toast.LENGTH_SHORT).show();
                        Log.e("tag", response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String Message) {
                        Toast.makeText(Latte.getApplication(), Message, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(Latte.getApplication(), "failure", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }

}
