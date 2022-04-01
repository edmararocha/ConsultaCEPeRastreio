package com.example.consultaceperastreio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultaceperastreio.MainActivity;
import com.example.consultaceperastreio.R;
import com.example.consultaceperastreio.model.CEP;
import com.example.consultaceperastreio.model.Rastreio;
import com.example.consultaceperastreio.model.SimpleCallback;
import com.example.consultaceperastreio.model.SimpleCallbackRastreio;
import com.example.consultaceperastreio.service.ServiceCEP;
import com.example.consultaceperastreio.service.ServiceRastreio;
import com.example.consultaceperastreio.utils.MaskEditUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class ConsultaRastreio extends AppCompatActivity {

    private EditText etCodigo;
    private Button btnConsultarRastreio;
    private TextView textCodigo;
    private TextView textDescricao;
    private TextView textLocal;
    private ProgressBar progressBar;
    private ConstraintLayout info;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_rastreio);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        etCodigo = findViewById(R.id.edittext_rastreio);
        btnConsultarRastreio = findViewById(R.id.btn_consultRastreio);
        textCodigo = findViewById(R.id.text_codigo_content);
        textDescricao = findViewById(R.id.text_descricao_content);
        textLocal = findViewById(R.id.text_local_content);
        progressBar = findViewById(R.id.progress_bar);
        info = findViewById(R.id.info_layout);
        backButton = findViewById(R.id.back_image_button_rastreio);

        progressBar.setVisibility(View.INVISIBLE);
        info.setVisibility(View.INVISIBLE);

        btnConsultarRastreio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("Código:", etCodigo.getText().toString());

                if (!etCodigo.getText().toString().isEmpty() && etCodigo.getText().toString().length() == 13) {

                    progressBar.setVisibility(View.VISIBLE);
                    info.setVisibility(View.INVISIBLE);

                    ServiceRastreio service = new ServiceRastreio(ConsultaRastreio.this);

                    service.getCodigo(etCodigo.getText().toString(),new SimpleCallbackRastreio<Rastreio>() {

                        @Override
                        public void onResponse(Rastreio response) throws JSONException {
                            Gson gson = new Gson();

                            String jsonString = gson.toJson(response.getObjetos().get(0));
                            JSONObject jsonObject = new JSONObject(jsonString);

                            if(!jsonObject.getString("modalidade").equals("V")) {
                                textCodigo.setText(jsonObject.getString("codObjeto"));
                                textDescricao.setText(jsonObject.getJSONArray("eventos").getJSONObject(0).getString("descricao"));
                                textLocal.setText(jsonObject.getJSONArray("eventos").getJSONObject(0).getJSONObject("unidade").getJSONObject("endereco").getString("cidade") + " - " +
                                        jsonObject.getJSONArray("eventos").getJSONObject(0).getJSONObject("unidade").getJSONObject("endereco").getString("uf"));

                                info.setVisibility(View.VISIBLE);

                            } else {
                                toast(jsonObject.getString("mensagem"));
                            }


                            Log.i("OBJETO", jsonString);

                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(String error) {
                            toast("Código inválido!");
                        }
                    });
                } else {
                    toast("Digite um código válido!");
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void toast(String msg){
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}