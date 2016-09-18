package com.example.douglas.meuslivrosapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.douglas.meuslivrosapp.data.Db4oFactory;
import com.example.douglas.meuslivrosapp.data.LivroDao;

import java.text.Normalizer;

public class FormActivity extends AppCompatActivity {

    private Db4oFactory db4o;
    private LivroDao livroDao;
    private long livroID;

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

        // pega o parâmetro LIVRO_ID quando passado
        Intent intent = getIntent();
        livroID = intent.getLongExtra("LIVRO_ID", 0);
    }

    private void configurarDb() {
        // escolhe o diretório para criar a conexão
        String dir = getDir("data", 0).toString();
        // cria a conexão com o banco passando o diretório
        db4o = new Db4oFactory(dir);
    }

    private void carregarComponentes() {
        etTitulo = (EditText) findViewById(R.id.et_titulo);
        etAutor = (EditText) findViewById(R.id.et_autor);
        etNumeroPaginas = (EditText) findViewById(R.id.et_numero_paginas);
        spCategoria = (Spinner) findViewById(R.id.sp_categoria);
        btSalvar = (Button) findViewById(R.id.bt_salvar);
    }

    void configurarBotao() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pega os valores dos campos
                String titulo = etTitulo.getText().toString();
                String autor = etAutor.getText().toString();
                String categoria = spCategoria.getSelectedItem().toString();
                String numPaginas = etNumeroPaginas.getText().toString();

                if (titulo.isEmpty() || autor.isEmpty() || numPaginas.isEmpty()) {
                    Toast.makeText(FormActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numeroPaginas = Integer.parseInt(numPaginas);

                // cria um objeto livro com os valores dos campos
                Livro livro = new Livro();
                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setCategoria(categoria);
                livro.setNumeroPaginas(numeroPaginas);

                if (livroID == 0) {
                    // insere o livro no banco
                    livroDao.inserir(livro);
                } else {
                    // atualiza o livro no banco
                    livroDao.atualizar(livroID, livro);
                }

                // finaliza a activity
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // abre a conexão com o banco
        db4o.abrirConexao();
        // instancia a classe que manipula os livros
        livroDao = new LivroDao(db4o);

        // atualiza o título da tela
        setTitle(R.string.novo_livro);

        if (livroID > 0) {
            setTitle(R.string.editar_livro);
            carregarLivro();
        }
    }

    private void carregarLivro() {
        // busca o livro do banco pelo ID
        Livro livro = livroDao.buscar(livroID);

        // carraga os campos da tela com os dados do livro
        String titulo = livro.getTitulo();
        String autor = livro.getAutor();
        String categoria = livro.getCategoria();
        int numeroPaginas = livro.getNumeroPaginas();

        etTitulo.setText(titulo);
        etAutor.setText(autor);
        etNumeroPaginas.setText(Integer.toString(numeroPaginas));

        int position = ((ArrayAdapter)spCategoria.getAdapter()).getPosition(categoria);
        spCategoria.setSelection(position);
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4o.fecharConexao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (livroID > 0) {
            getMenuInflater().inflate(R.menu.livros, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_excluir_livros:
                AlertDialog alertDialog = new AlertDialog.Builder(FormActivity.this)
                        .setMessage("Confirma a exclusão?")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                livroDao.excluir(livroID);
                                finish();
                            }
                        })
                        .create();

                alertDialog.show();

                return true;
        }

        return false;
    }
}
