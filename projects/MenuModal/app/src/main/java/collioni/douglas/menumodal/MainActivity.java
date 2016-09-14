package collioni.douglas.menumodal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ListView lvPaises;
    ArrayList<String> paises;
    ArrayAdapter<String> adapter;

    private void carregarComponentes() {
        lvPaises = (ListView) findViewById(R.id.lv_paises);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarComponentes();
        configurarLista();
    }

    private void configurarLista() {
        paises = new ArrayList<>();
        paises.add("Alemanha");
        paises.add("Brasil");
        paises.add("França");
        paises.add("Japão");
        paises.add("Suécia");
        paises.add("Tailândia");

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_activated_1, paises);

        // registra um menu modal para a lista
        lvPaises.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvPaises.setAdapter(adapter);

        lvPaises.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            // declara um array para guardar os itens selecionados
            ArrayList<Integer> selecionados;

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // pega o total de itens selecionados na lista
                int total = lvPaises.getCheckedItemCount();

                // atualiza a barra de título com o total de itens selecionados
                mode.setTitle(Integer.toString(total));

                // testa se o item foi selecionado
                if (checked) {
                    // adiciona o item no array de selecionados
                    selecionados.add(position);
                } else {
                    // remove o item do array de selecionados
                    selecionados.remove(Integer.valueOf(position));
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // carrega o menu de países no action mode
                mode.getMenuInflater().inflate(R.menu.paises, menu);

                // inicializa o array de selecionados
                selecionados = new ArrayList<>();

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // pega o id do item clicado
                int id = item.getItemId();

                switch (id) {
                    case R.id.menu_excluir:
                        // exclui os itens selecionados
                        excluirSelecionados(selecionados);

                        // fecha o modo de menu
                        mode.finish();
                        return true;

                    case R.id.menu_duplicar:
                        // duplica os itens selecionados
                        duplicarSelecionados(selecionados);

                        // fecha o modo de menu
                        mode.finish();
                        return true;
                }

                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    private void duplicarSelecionados(ArrayList<Integer> selecionados) {
        // para cada posição no array de selecionados
        for (int position : selecionados) {
            String pais = paises.get(position);
            // adiciona o país novamente na lista original
            paises.add(pais);
        }

        // notifica o adapter da lista que os dados mudaram
        adapter.notifyDataSetChanged();
    }

    private void excluirSelecionados(ArrayList<Integer> selecionados) {
        // ordena o array de posições em ordem descrescente para evitar bug
        Collections.sort(selecionados, new Comparator<Integer>() {
            @Override
            public int compare(Integer lhs, Integer rhs) {
                return rhs.compareTo(lhs);
            }
        });

        // para cada posição no array de selecionados
        for (int position : selecionados) {
            // remove o país da lista original
            paises.remove(position);
        }

        // notifica o adapter da lista que os dados mudaram
        adapter.notifyDataSetChanged();
    }
}
