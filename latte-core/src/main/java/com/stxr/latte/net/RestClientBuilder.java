package com.stxr.latte.net;

import com.stxr.latte.net.callback.IError;
import com.stxr.latte.net.callback.IFailure;
import com.stxr.latte.net.callback.IRequest;
import com.stxr.latte.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by stxr on 2018/7/12.
 */

public class RestClientBuilder {
    private String url;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.PARAMS;
    private ISuccess success;
    private IError error;
    private IRequest request;
    private RequestBody requestbody;
    private IFailure failure;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.url = url;
        return this;
    }

    public final RestClientBuilder params(String key,Object value) {
        PARAMS.put(key, value);
        return this;
    }
    public final RestClientBuilder params(WeakHashMap<String ,Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        this.success = success;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.error = error;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.failure = failure;
        return this;
    }

    public final RestClientBuilder request(IRequest request) {
        this.request = request;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.requestbody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RestClient build() {
        return new RestClient(url, PARAMS, success, error, request, failure, requestbody);
    }
}
