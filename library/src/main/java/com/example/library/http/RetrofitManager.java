package com.example.library.http;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final String TAG = RetrofitManager.class.getSimpleName();

    private static final long CONNECT_TIME_OUT = 50L;
    private static final long READ_TIME_OUT = 50L;
    private static final long WRITE_TIME_OUT = 50L;

    private static final HashMap<String, Retrofit> retrofitMap = new HashMap<>();
    private static final HashMap<String, CommonParams> commonParamsMap = new HashMap<>();
    private static final HashMap<String, ArrayList<Interceptor>> customInterceptorsMap = new HashMap<>();

    private static Application context;

    public static void init(Application app) {
        context = app;
    }

    public static CommonParams getCommonParams(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw new NullPointerException("url is empty");
        }
        CommonParams commonParams = commonParamsMap.get(baseUrl);
        if (commonParams == null) {
            commonParams = new CommonParams();
            commonParamsMap.put(baseUrl, commonParams);
        }
        return commonParams;
    }

    public static ArrayList<Interceptor> getCustomInterceptors(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw new NullPointerException("url is empty");
        }
        ArrayList<Interceptor> interceptors = customInterceptorsMap.get(baseUrl);
        if (interceptors == null) {
            interceptors = new ArrayList<>();
            customInterceptorsMap.put(baseUrl, interceptors);
        }
        return interceptors;
    }
    public static <T> T getApi(Class<T> tClass){
         String baseUrl= "https://api.58moto.com/";
        return new RetrofitManager.Builder().setBaseUrl(baseUrl).build().create(tClass);
    }

    public static final class Builder {
        private String baseUrl = null;


        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setHeaders(HashMap<String, String> headers) {
            if (headers.size() > 0) {
                CommonParams commonParams = getCommonParams(baseUrl);
                commonParams.addHeaders(headers);
            }
            return this;
        }

        public Builder setCommonParams(HashMap<String, String> params) {
            if (params != null && params.size() > 0) {
                CommonParams commonParams = getCommonParams(baseUrl);
                commonParams.addCommonParams(params);
            }
            return this;
        }

        public Builder addCustomInterceptors(List<Interceptor> interceptors) {
            if (interceptors != null && interceptors.size() > 0) {
                ArrayList<Interceptor> customInterceptors = getCustomInterceptors(baseUrl);
                customInterceptors.addAll(interceptors);
            }
            return this;
        }


        public Retrofit build() {

            Retrofit retrofit = retrofitMap.get(baseUrl);
            if (retrofit == null) {
                CommonParams paramsInterceptor = commonParamsMap.get(baseUrl);
                ArrayList<Interceptor> customInterceptors = getCustomInterceptors(baseUrl);
                OkHttpClient client = buildClient(context, paramsInterceptor, customInterceptors);
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                builder.client(client);
                retrofit = builder.build();
                retrofitMap.put(baseUrl, retrofit);
            }
            Log.d(TAG, "build, retrofit:" + retrofit);
            return retrofit;
        }

        private OkHttpClient buildClient(Context context, Interceptor paramsInterceptor, ArrayList<Interceptor> customInterceptors) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false);


            if (paramsInterceptor != null) {
                builder.addInterceptor(paramsInterceptor);
            }
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            if (customInterceptors != null && customInterceptors.size() > 0) {
                for (int i = 0; i < customInterceptors.size(); i++) {
                    builder.addInterceptor(customInterceptors.get(i));
                }
            }
            return builder.build();
        }
    }
}