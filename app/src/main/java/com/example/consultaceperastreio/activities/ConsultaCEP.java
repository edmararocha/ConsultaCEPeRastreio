package com.example.consultaceperastreio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultaceperastreio.R;
import com.example.consultaceperastreio.model.CEP;
import com.example.consultaceperastreio.model.SimpleCallback;
import com.example.consultaceperastreio.utils.ServiceCEP;

public class ConsultaCEP extends AppCompatActivity {

    private EditText etCEP;
    private Button btnConsultarCEP;
    private TextView textCEP;
    private TextView textLogradouro;
    private TextView textComplemento;
    private TextView textBairro;
    private TextView textLocalidade;
    private TextView textUF;
    private TextView textIBGE;
    private TextView textGIA;
    private TextView textDDD;
    private TextView textSIAFI;
    private ProgressBar progressBar;
    private ConstraintLayout info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cep);

        etCEP = findViewById(R.id.edittext_cep);
        btnConsultarCEP = findViewById(R.id.btn_consultCEP);
        textCEP = findViewById(R.id.text_cep_content);
        textLogradouro = findViewById(R.id.text_logradouro_content);
        textComplemento = findViewById(R.id.text_complemento_content);
        textBairro = findViewById(R.id.text_bairro_content);
        textLocalidade = findViewById(R.id.text_localidade_content);
        textUF = findViewById(R.id.text_uf_content);
        textIBGE = findViewById(R.id.text_ibge_content);
        textGIA = findViewById(R.id.text_gia_content);
        textDDD = findViewById(R.id.text_ddd_content);
        textSIAFI = findViewById(R.id.text_siafi_content);
        progressBar = findViewById(R.id.progress_bar);
        info = findViewById(R.id.info_layout);

        progressBar.setVisibility(View.INVISIBLE);
        info.setVisibility(View.INVISIBLE);

        btnConsultarCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etCEP.getText().toString().isEmpty() && etCEP.length() == 8) {

                    progressBar.setVisibility(View.VISIBLE);
                    info.setVisibility(View.INVISIBLE);
                    ServiceCEP service = new ServiceCEP(ConsultaCEP.this);

                    service.getCEP(etCEP.getText().toString(), new SimpleCallback<CEP>() {
                        @Override
                        public void onResponse(CEP response) {
                            CEP cep = response;

                            if (cep.isError() == true) {
                                toast("CEP inválido!");
                            } else {
                                textCEP.setText(cep.getCep());
                                textLogradouro.setText(cep.getLogradouro());
                                textComplemento.setText(cep.getComplemento());
                                textBairro.setText(cep.getBairro());
                                textLocalidade.setText(cep.getLocalidade());
                                textUF.setText(cep.getUf());
                                textIBGE.setText(cep.getIbge());
                                textGIA.setText(cep.getGia());
                                textDDD.setText(cep.getDdd());
                                textSIAFI.setText(cep.getSiafi());
                                progressBar.setVisibility(View.INVISIBLE);
                                info.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onError(String error) {
                            toast(getResources().getString(R.string.generic_error)+error);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });

                } else {
                    toast("Inválido!");
                }
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