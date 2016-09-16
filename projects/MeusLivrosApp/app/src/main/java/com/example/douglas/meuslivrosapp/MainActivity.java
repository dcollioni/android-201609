package com.example.douglas.meuslivrosapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvLivros;
    List<Livro> livros;
    LivroAdapter adapter;

    EditText etFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurarDb();
        carregarComponentes();
        configurarLista();
    }

    private void configurarDb() {
        // TODO: instanciar db factory
    }

    private void carregarComponentes() {
        etFiltro = (EditText) findViewById(R.id.et_filtro);
        lvLivros = (ListView) findViewById(R.id.lv_livros);
    }

    private void configurarLista() {
        livros = new ArrayList<>();
        adapter = new LivroAdapter(MainActivity.this, livros);
        lvLivros.setAdapter(adapter);
        lvLivros.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_novo_livro:
                Intent i = new Intent(MainActivity.this, FormActivity.class);
                startActivity(i);
                return true;

            case R.id.menu_excluir_todos:
                return true;
        }

        return false;
    }
}
