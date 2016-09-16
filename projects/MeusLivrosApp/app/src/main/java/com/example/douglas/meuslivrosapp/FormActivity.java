package com.example.douglas.meuslivrosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FormActivity extends AppCompatActivity {

    EditText etTitulo, etAutor, etNumeroPaginas;
    Spinner spCategoria;
    Button btSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        configurarDb();
        carregarComponentes();
        configurarBotao();
    }

    private void configurarDb() {
        // TODO: instanciar db factory
    }

    private void carregarComponentes() {
        etTitulo = (EditText) findViewById(R.id.et_titulo);
        etAutor = (EditText) findViewById(R.id.et_autor);
        etNumeroPaginas = (EditText) findViewById(R.id.et_numero_paginas);
        spCategoria = (Spinner) findViewById(R.id.sp_categoria);
        btSalvar = (Button) findViewById(R.id.bt_salvar);
    }

    void configurarBotao() {
        // TODO: adicionar evento de click no botão
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: abrir conexão e instanciar LivroDao
    }

    @Override
    protected void onPause() {
        super.onPause();

        // TODO: fechar conexão
    }
}
