package com.example.consultaceperastreio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.consultaceperastreio.activities.ConsultaCEP;
import com.example.consultaceperastreio.activities.ConsultaRastreio;

public class MainActivity extends AppCompatActivity {

    private Button consultaCep, consultaRastreio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consultaCep = findViewById(R.id.cep_button);
        consultaRastreio = findViewById(R.id.rastreio_button);

        consultaCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultaCEP.class);
                startActivity(intent);
            }
        });

        consultaRastreio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ConsultaRastreio.class);
                startActivity(intent);
            }
        });
    }


}