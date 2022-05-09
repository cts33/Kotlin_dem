package com.example.library.http;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class ParamsInterceptor implements Interceptor {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        HttpUrl httpUrl = oldRequest.url();
        if (oldRequest.method().equals("GET") || (oldRequest.method().equals("POST") && oldRequest.body() != null && oldRequest.body() instanceof MultipartBody)) {
            HashMap<String, String> params = addCommonParams(httpUrl);
            HttpUrl.Builder builder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .encodedQuery(null);
            params.forEach(builder::addQueryParameter);
            Request newRequest = applyHeader(oldRequest, params);
            Request request = newRequest.newBuilder()
                    .method(newRequest.method(), newRequest.body())
                    .url(builder.build())
                    .build();
            return chain.proceed(request);
        } else if (oldRequest.method().equals("POST")) {
            RequestBody oldBody = oldRequest.body();
            MediaType contentType = oldBody.contentType();
            if (oldBody instanceof FormBody) {
                HashMap<String, String> params = addCommonParams((FormBody) oldBody);
                FormBody.Builder builder = new FormBody.Builder();
                params.forEach(builder::add);
                Request newRequest = applyHeader(oldRequest, params);
                Request request = newRequest.newBuilder()
                        .method(newRequest.method(), builder.build())
                        .url(newRequest.url())
                        .build();
                return chain.proceed(request);
            } else if (contentType != null && contentType.subtype().equals("json")) {
                RequestBody newBody = addJsonCommonParams(oldBody);
                Request newRequest = applyHeader(oldRequest, new HashMap<>());
                Request request = newRequest.newBuilder()
                        .method(newRequest.method(), newBody)
                        .url(newRequest.url())
                        .build();
                return chain.proceed(request);
            } else if (oldBody instanceof MultipartBody) {
                return chain.proceed(oldRequest);
            } else {
                return chain.proceed(oldRequest);
            }
        } else {
            return chain.proceed(oldRequest);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private HashMap<String, String> addCommonParams(HttpUrl httpUrl) {
        HashMap<String, String> params = new HashMap<>();
        int i = 0;
        int size = httpUrl.querySize();
        while (i < size) {
            String name = httpUrl.queryParameterName(i);
            String value = httpUrl.queryParameterValue(i);
            params.put(name, value);
            i++;
        }
        HashMap<String, String> common = new HashMap<>();
        applyCommonParams(common);
        common.forEach((commonKey, commonValue) -> {
            if (!params.containsKey(commonKey)) {
                params.put(commonKey, commonValue);
            }
        });
        return params;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private HashMap<String, String> addCommonParams(FormBody formBody) {
        HashMap<String, String> params = new HashMap<>();
        int i = 0;
        int size = formBody.size();
        while (i < size) {
            String name = formBody.name(i);
            String value = formBody.value(i);
            params.put(name, value);
            i++;
        }
        HashMap<String, String> common = new HashMap<>();
        applyCommonParams(common);
        common.forEach((commonKey, commonValue) -> {
            if (!params.containsKey(commonKey)) {
                params.put(commonKey, commonValue);
            }
        });
        return params;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private RequestBody addJsonCommonParams(RequestBody requestBody) {
        String customReq = bodyToString(requestBody);
        try {
            JSONObject obj = new JSONObject(customReq);
            HashMap<String, String> params = new HashMap<>();
            applyCommonParams(params);
            params.forEach((paramKey, paramValue) -> {
                if (!obj.has(paramKey)) {
                    try {
                        obj.put(paramKey, paramValue);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            return RequestBody.create(requestBody.contentType(), obj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String bodyToString(RequestBody request) {
        try {
            Buffer buffer = new Buffer();
            if (request != null) {
                request.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (IOException e) {
            return "did not work";
        }
    }

    // 子类可以继承并添加通用参数
    protected void applyCommonParams(HashMap<String, String> params) {

    }

    // 子类可以集成并添加header
    protected Request applyHeader(Request oldRequest, HashMap<String, String> params) {
        return oldRequest;
    }
}
