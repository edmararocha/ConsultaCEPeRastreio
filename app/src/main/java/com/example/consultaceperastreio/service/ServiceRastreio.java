package com.example.consultaceperastreio.service;

import android.content.Context;

import com.example.consultaceperastreio.model.Rastreio;
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

public class ServiceRastreio {
    private Context context;
    private RetrofitServiceRastreio service;


    public ServiceRastreio(Context context) {
        this.context = context;

        initialize();
    }

    private void initialize() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Rastreio.class, new CEPDeserializer()).create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitServiceRastreio.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(RetrofitServiceRastreio.class);

        final RetrofitServiceRastreio service = retrofit.create(RetrofitServiceRastreio.class);
    }

    public void getCodigo(String codigo, final SimpleCallback<Rastreio> callback){
        service.consultarObjeto(codigo).enqueue(new Callback<Rastreio>() {
            @Override
            public void onResponse(Call<Rastreio> call, Response<Rastreio> response) {
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
            public void onFailure(Call<Rastreio> call, Throwable t) {
                t.printStackTrace ();
                callback.onError (t.getMessage());
            }
        });
    }
}