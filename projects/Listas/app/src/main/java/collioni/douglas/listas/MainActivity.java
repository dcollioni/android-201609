package collioni.douglas.listas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgTipo;
    ListView lvNavegadores;
    ArrayAdapter<CharSequence> adapter;

    // método que carrega componentes da tela pelo ID
    private void carregarComponentes() {
        lvNavegadores = (ListView) findViewById(R.id.lv_navegadores);
        rgTipo = (RadioGroup) findViewById(R.id.rg_tipo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarComponentes();
        configurarLista();

        // adiciona o evento de seleção no radio group
        rgTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_a:
                        configurarLista();
                        break;
                    case R.id.rb_b:
                        configurarListaB();
                        break;
                    case R.id.rb_c:
                        configurarListaC();
                        break;
                    case R.id.rb_d:
                        configurarListaD();
                        break;
                    case R.id.rb_e:
                        configurarListaE();
                        break;
                    case R.id.rb_f:
                        configurarListaF();
                        break;
                }
            }
        });
    }

    private void configurarListaF() {
        adapter = ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.navegadores,
                android.R.layout.simple_list_item_activated_1
        );

        lvNavegadores.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvNavegadores.setAdapter(adapter);

        lvNavegadores.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // pega o número total de itens selecionados
                int total = lvNavegadores.getCheckedItemCount();
                
                // atualiza a barra de título com o total
                mode.setTitle(Integer.toString(total));
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    private void configurarListaE() {
        // adapter com layout de checks
        adapter = ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.navegadores,
                android.R.layout.simple_list_item_checked
        );

        lvNavegadores.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvNavegadores.setAdapter(adapter);
    }

    private void configurarListaD() {
        // adapter com layout de checkbox
        adapter = ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.navegadores,
                android.R.layout.simple_list_item_multiple_choice
        );

        lvNavegadores.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvNavegadores.setAdapter(adapter);
    }

    private void configurarListaC() {
        // adapter com layout de radio buttons
        adapter = ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.navegadores,
                android.R.layout.simple_list_item_single_choice
        );

        lvNavegadores.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvNavegadores.setAdapter(adapter);
    }

    private void configurarListaB() {
        adapter = ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.navegadores,
                android.R.layout.simple_list_item_activated_1
        );

        lvNavegadores.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvNavegadores.setAdapter(adapter);
    }

    private void configurarLista() {
        // inicializa o adapter com o array de navegadores usando layout simples
        adapter = ArrayAdapter.createFromResource(
                MainActivity.this,
                R.array.navegadores,
                android.R.layout.simple_list_item_activated_1
        );

        // passa o adapter para a lista
        lvNavegadores.setAdapter(adapter);
    }
}
