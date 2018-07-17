package com.stxr.latte.net.callback;

import com.stxr.latte.ui.LatteLoader;
import com.stxr.latte.ui.LoaderStyle;

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
    private final LoaderStyle LOADER_STYLE;

    public RestClientCallback(String url, ISuccess success, IError error, IRequest request, IFailure failure, LoaderStyle loaderStyle) {
        URL = url;
        SUCCESS = success;
        ERROR = error;
        REQUEST = request;
        FAILURE = failure;
        LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
        stopLoading();
    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        stopLoading();
    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            LatteLoader.stopLoading();
        }
    }
}
