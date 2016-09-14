package collioni.douglas.menus;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvItens;
    ArrayList<String> itens;
    ArrayAdapter<String> adapter;

    private void carregarComponentes() {
        lvItens = (ListView) findViewById(R.id.lv_itens);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarComponentes();
        configurarLista();

        // registro um menu de contexto para a lista de itens
        registerForContextMenu(lvItens);
    }

    private void configurarLista() {
        itens = new ArrayList<>();
        itens.add("Item 1");
        itens.add("Item 2");
        itens.add("Item 3");
        itens.add("Item 4");

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, itens);

        lvItens.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // infla o arquivo de menu para a activity
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // pega o id do item de menu selecionado
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_limpar:
                // cria um alert dialog de confirmação
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmação")
                        .setMessage("Confirma a exclusão dos itens?")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // remove todos os itens do array
                                itens.clear();
                                // notifica o adapter que os dados mudaram
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .create();

                // mostra o alert
                alertDialog.show();

                return true;

            case R.id.menu_recarregar:
                // reconfigura a lista de itens
                configurarLista();

                return true;

            case R.id.menu_sobre:
                // cria um alert de informação
                AlertDialog alertDialog1 = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.app_name)
                        .setMessage("Versão 1.0.0")
                        .create();

                // mostra o alert
                alertDialog1.show();

                return true;
        }

        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // verifica se a view é a lista de itens
        if (v.getId() == R.id.lv_itens) {
            // carrega o menu dos itens
            getMenuInflater().inflate(R.menu.item, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // pega o id do item de menu selecionado
        int id = item.getItemId();

        // pega as informações do menu convertendo pro tipo de menu adapter
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // pega a posição do item a partir da informação do menu
        int position = menuInfo.position;

        // pega o item na posição
        String itemSelecionado = itens.get(position);

        switch (id) {
            case R.id.menu_detalhes:
                Toast.makeText(MainActivity.this, itemSelecionado, Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_excluir:
                // remove o item na posição
                itens.remove(position);
                // notifica o adapter
                adapter.notifyDataSetChanged();
                return true;
        }

        return false;
    }
}
