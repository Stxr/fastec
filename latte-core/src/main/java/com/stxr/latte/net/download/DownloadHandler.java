package com.stxr.latte.net.download;

import android.os.AsyncTask;

import com.stxr.latte.net.RestCreator;
import com.stxr.latte.net.callback.IError;
import com.stxr.latte.net.callback.IFailure;
import com.stxr.latte.net.callback.IRequest;
import com.stxr.latte.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Url;

/**
 * Created by stxr on 2018/7/18.
 */

public class DownloadHandler {
    private final static WeakHashMap<String, Object> PARAMS = RestCreator.PARAMS;
    private final String URL;
    private final String DOWNLOAD_DIR;
    private final String NAME;
    private final String EXTENSION;
    private final ISuccess SUCCESS;
    private final IError ERROR;

    private final IRequest REQUEST;
    private final IFailure FAILURE;

    public DownloadHandler(String url, String download_dir, String name, String extension, ISuccess success, IError error, IRequest request, IFailure failure) {
        URL = url;
        DOWNLOAD_DIR = download_dir;
        NAME = name;
        EXTENSION = extension;
        SUCCESS = success;
        ERROR = error;
        REQUEST = request;
        FAILURE = failure;
    }

    public void downloadHandler() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService()
                .download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody body = response.body();
                            //开始下载
                            SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            //线程池的形式执行
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR,
                                    EXTENSION,
                                    body,
                                    NAME);
                            //要判断否则会文件不全
                            if (task.isCancelled()) {
                                REQUEST.onRequestEnd();
                            }
                        } else {
                            if (ERROR!=null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
