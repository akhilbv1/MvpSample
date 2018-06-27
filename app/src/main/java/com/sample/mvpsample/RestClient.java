package com.sample.mvpsample;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akhil on 10/4/18.
 */

public class RestClient {

    private static String LOGIN_BASE_URL = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/";

    private   final  RestApi loginApiService;

    public RestClient(){

        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        httpClient.addInterceptor(log);

        Retrofit retrofitLogin = new Retrofit.Builder()
                                 .baseUrl(LOGIN_BASE_URL)
                                 .client(httpClient.build())
                                 .addConverterFactory(GsonConverterFactory.create())
                                 .build();

        loginApiService = retrofitLogin.create(RestApi.class);
    }

    public  RestApi getLoginApiService() {
        return loginApiService;
    }
}

