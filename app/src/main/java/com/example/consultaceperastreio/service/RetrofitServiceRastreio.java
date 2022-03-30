package com.example.consultaceperastreio.service;

import com.example.consultaceperastreio.model.Rastreio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitServiceRastreio {

    String BASE_URL = "https://proxyapp.correios.com.br/v1/sro-rastro/";

    @GET("{codigo}")
    Call<Rastreio> consultarObjeto(@Path("codigo") String codigo);
}
