package com.stxr.latte.net;

import com.stxr.latte.net.callback.IError;
import com.stxr.latte.net.callback.IFailure;
import com.stxr.latte.net.callback.IRequest;
import com.stxr.latte.net.callback.ISuccess;
import com.stxr.latte.net.callback.RestClientCallback;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.HTTP;

/**
 * Created by stxr on 2018/7/10.
 */

public class RestClient {
    private final static WeakHashMap<String, Object> PARAMS = RestCreator.PARAMS;
    private final String URL;
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

    private void request(HttpMethod method) {
        RestService service = RestCreator.getRestService();
        Call<String> call = null;
        switch (method) {
            case GET:
                 call = service.get(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
//                call = service.upload(URL, PARAMS);
                break;
            case DOWNLOAD:
//                call = service.download(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRestClientCallback());
        }
    }

    public void get() {
        request(HttpMethod.GET);
    }
    public void put() {
        request(HttpMethod.PUT);
    }
    public void post() {
        request(HttpMethod.POST);
    }
    public void deltete() {
        request(HttpMethod.DELETE);
    }

    public Callback<String> getRestClientCallback() {
        return new RestClientCallback(URL, SUCCESS, ERROR, REQUEST, FAILURE);
    }
}
