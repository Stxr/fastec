package com.stxr.latte.net.callback;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by stxr on 2018/7/13.
 */

public class RestClientCallback implements Callback<String> {

    private final String URL;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final IFailure FAILURE;

    public RestClientCallback(String url, ISuccess success, IError error, IRequest request, IFailure failure) {
        URL = url;
        SUCCESS = success;
        ERROR = error;
        REQUEST = request;
        FAILURE = failure;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST!=null) {
            REQUEST.onRequestEnd();
        }
    }
}
