package com.example.consultaceperastreio.model;

import org.json.JSONException;

public interface SimpleCallbackRastreio<T> {
    void onResponse (T response) throws JSONException;
    void onError (String error);
}
