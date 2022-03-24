package com.example.consultaceperastreio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.consultaceperastreio.R;

public class ConsultaCEP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cep);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}