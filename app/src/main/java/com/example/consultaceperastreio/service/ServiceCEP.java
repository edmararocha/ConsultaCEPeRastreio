package com.example.consultaceperastreio.service;

import android.content.Context;

import com.example.consultaceperastreio.model.CEP;
import com.example.consultaceperastreio.model.SimpleCallback;
import com.example.consultaceperastreio.utils.CEPDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCEP {

    private Context context;
    private RetrofitServiceCEP service;

    public ServiceCEP (Context context) {
        this.context = context;

        initialize();
    }

    private void initialize() {
        Gson gson = new GsonBuilder().registerTypeAdapter(CEP.class, new CEPDeserializer()).create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitServiceCEP.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(RetrofitServiceCEP.class);

        final RetrofitServiceCEP service = retrofit.create(RetrofitServiceCEP.class);
    }

    public void getCEP(String CEP, final SimpleCallback<CEP> callback){
        service.consultarCEP(CEP).enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful () && response.body () != null) {
                    callback.onResponse (response.body());
                } else {
                    if (response.body () != null) {
                        callback.onError("erro");
                    } else {
                        callback.onError("erro");
                    }
                }
            }
            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                t.printStackTrace ();
                callback.onError (t.getMessage());
            }
        });
    }
}
