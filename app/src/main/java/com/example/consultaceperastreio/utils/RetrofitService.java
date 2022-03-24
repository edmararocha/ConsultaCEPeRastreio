package com.example.consultaceperastreio.utils;

import com.example.consultaceperastreio.model.RespostaServidorCEP;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    String BASE_URL = "https://viacep.com.br/ws/";

    @FormUrlEncoded
    @GET("{cep}/json")
    Call<RespostaServidorCEP> consultarCEP(@Path("cep") String cep);
}
