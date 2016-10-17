package com.moblib.application;

import android.app.Application;

import com.moblib.connection.APIgoogle;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class Moblib extends Application {
    private static Moblib moblibEntr; //Get this application instance
    private Retrofit retroGoogleClient;
    private APIgoogle APIconn;

    @Override
    public void onCreate() {
        super.onCreate();
        moblibEntr = this;
    }

    public static Moblib getInstance() {
        return moblibEntr;
    }

    public APIgoogle getAPIconn(){
        if(APIconn == null){
            APIconn = getRetroGoogleClient().create(APIgoogle.class);
        }
        return  APIconn;
    }

    private Retrofit getRetroGoogleClient(){
        if (retroGoogleClient == null){
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
            okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);

            retroGoogleClient = new Retrofit.Builder()
                    .baseUrl(MobLibConstant.URLConstants.GOOGLE_BOOKS_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retroGoogleClient;
    }
}
