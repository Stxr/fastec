package com.stxr.latte.net;

import com.stxr.latte.net.callback.IError;
import com.stxr.latte.net.callback.IFailure;
import com.stxr.latte.net.callback.IRequest;
import com.stxr.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by stxr on 2018/7/10.
 */

public class RestClient {
    private final String URL;
    private final static WeakHashMap<String, Object> PARAMS = RestCreator.PARAMS;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final IFailure FAILURE;
    private final RequestBody REQUESTBODY;


    public RestClient(String url, WeakHashMap<String, Object> params, ISuccess success, IError error, IRequest request, IFailure failure, RequestBody requestbody) {
        URL = url;
        PARAMS.putAll(params);
        SUCCESS = success;
        ERROR = error;
        REQUEST = request;
        FAILURE = failure;
        REQUESTBODY = requestbody;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }
}
