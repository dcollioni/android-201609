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

import com.example.douglas.meuslivrosapp.data.Db4oFactory;
import com.example.douglas.meuslivrosapp.data.LivroDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Db4oFactory db4o;
    private LivroDao livroDao;

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
        configurarFiltro();
    }

    private void configurarFiltro() {
        etFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                carregarLivros();
            }
        });
    }

    private void configurarDb() {
        String dir = getDir("data", 0).toString();
        db4o = new Db4oFactory(dir);
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

        lvLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // pega o livro na posição selecionada
                Livro livro = (Livro) parent.getItemAtPosition(position);

                // busca o ID do livro no banco
                long livroID = livroDao.buscarId(livro);

                // cria intent para a tela form
                Intent intent = new Intent(MainActivity.this, FormActivity.class);

                // passa o ID do livro para a intent
                intent.putExtra("LIVRO_ID", livroID);

                // iniciar a outra tela
                startActivity(intent);
            }
        });

        lvLivros.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            List<Livro> selecionados;

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Livro livro = livros.get(position);

                if (checked) {
                    selecionados.add(livro);
                } else {
                    selecionados.remove(livro);
                }

                adapter.setSelectedItems(selecionados);
                adapter.notifyDataSetChanged();

                int total = lvLivros.getCheckedItemCount();
                mode.setTitle(Integer.toString(total));
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                selecionados = new ArrayList<>();
                mode.getMenuInflater().inflate(R.menu.livros, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.menu_excluir_livros:
                        excluirLivros(selecionados, mode);
                        return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                selecionados.clear();
                adapter.setSelectedItems(selecionados);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void excluirLivros(final List<Livro> livros, final ActionMode mode) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("Confirma a exclusão?")
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (Livro livro : livros) {
                            long livroID = livroDao.buscarId(livro);
                            livroDao.excluir(livroID);
                        }
                        carregarLivros();
                        if (mode != null) {
                            mode.finish();
                        }
                    }
                })
                .create();

        alertDialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4o.abrirConexao();
        livroDao = new LivroDao(db4o);
        carregarLivros();
    }

    private void carregarLivros() {
        String filtro = etFiltro.getText().toString();

        // busca os livros do banco
        List<Livro> livrosDb = livroDao.listar(filtro);

        // atualiza o array de livros com os dados do banco
        livros.clear();
        livros.addAll(livrosDb);

        // notifica o adapter
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4o.fecharConexao();
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
                excluirLivros(livros, null);
                return true;
        }

        return false;
    }
}
