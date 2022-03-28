package com.example.consultaceperastreio.model;

public interface SimpleCallback<T> {

    void onResponse (T response);
    void onError (String error);
}
