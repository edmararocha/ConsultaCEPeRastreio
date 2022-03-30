package com.example.consultaceperastreio.service;

import com.example.consultaceperastreio.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitServiceCEP {

    String BASE_URL = "https://viacep.com.br/ws/";

    @GET("{CEP}/json")
    Call<CEP> consultarCEP(@Path("CEP") String CEP);
}
