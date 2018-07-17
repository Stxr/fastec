package com.stxr.latte.net;

import android.content.Context;

import com.stxr.latte.net.callback.IError;
import com.stxr.latte.net.callback.IFailure;
import com.stxr.latte.net.callback.IRequest;
import com.stxr.latte.net.callback.ISuccess;
import com.stxr.latte.net.callback.RestClientCallback;
import com.stxr.latte.ui.LatteLoader;
import com.stxr.latte.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;

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
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;


    public RestClient(String url, WeakHashMap<String, Object> params, ISuccess success, IError error, IRequest request, IFailure failure, RequestBody requestbody, LoaderStyle loaderStyle, File file, Context context) {
        URL = url;
        LOADER_STYLE = loaderStyle;
        FILE = file;
        CONTEXT = context;
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
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
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
            case POST_RAW:
                call = service.postRaw(URL, REQUESTBODY);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, REQUESTBODY);
                break;
            case UPLOAD:
                //okhttp3 中upload标准用法
                RequestBody body = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", FILE.getName(),body);
                call = service.upload(URL, part);
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
        if (REQUESTBODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }
    public void post() {
        if (REQUESTBODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }
    public void deltete() {
        request(HttpMethod.DELETE);
    }

    public Callback<String> getRestClientCallback() {
        return new RestClientCallback(URL, SUCCESS, ERROR, REQUEST, FAILURE,LOADER_STYLE);
    }
}
