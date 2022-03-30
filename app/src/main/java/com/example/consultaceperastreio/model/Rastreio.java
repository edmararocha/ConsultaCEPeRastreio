package com.example.consultaceperastreio.model;

import java.util.ArrayList;

public class Rastreio {
    private ArrayList<Object> objetos;
    private String quantidade;
    private String resultado;
    private String versao;

    public ArrayList<Object> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<Object> objetos) {
        this.objetos = objetos;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}
