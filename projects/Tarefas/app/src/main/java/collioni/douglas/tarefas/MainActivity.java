package collioni.douglas.tarefas;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTarefa;
    Button btAdicionar;
    ListView lvTarefas;
    ArrayList<String> tarefas;
    ArrayAdapter<String> adapter;

    private void carregarComponentes() {
        lvTarefas = (ListView) findViewById(R.id.lv_tarefas);
        etTarefa = (EditText) findViewById(R.id.et_tarefa);
        btAdicionar = (Button) findViewById(R.id.bt_adicionar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarComponentes();
        configurarLista();

        // adiciona evento de click no botão "adicionar"
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pega o valor digitado no campo
                String tarefa = etTarefa.getText().toString();

                // testa se o campo está vazio
                if (tarefa.isEmpty()) {
                    return;
                }

                // adiciona a nova tarefa no array da lista
                tarefas.add(tarefa);

                // notifica o adapter que os dados da lista mudaram
                adapter.notifyDataSetChanged();

                // limpa o campo tarefa
                etTarefa.setText("");
            }
        });
    }

    private void configurarLista() {
        // inicializa o array de tarefas com alguns itens
        tarefas = new ArrayList<>();
        tarefas.add("Tarefa 1");
        tarefas.add("Tarefa 2");
        tarefas.add("Tarefa 3");

        // inicializa o adapter usando um layout simples e o array de tarefas
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, tarefas);

        // configura a lista para usar o adapter criado
        lvTarefas.setAdapter(adapter);

        // adiciona listener para itens clicados
        lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // pega a tarefa clicada a partir da posição
                String tarefa = (String) parent.getItemAtPosition(position);

                // mostra um toast com a tarefa clicada
                Toast.makeText(MainActivity.this, tarefa, Toast.LENGTH_SHORT).show();
            }
        });

        lvTarefas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // remove o item do array de tarefas na posição clicada
                tarefas.remove(position);

                // notifica o adapter que os dados mudaram
                adapter.notifyDataSetChanged();

                // retornando true, evita que o método click seja chamado também
                return true;
            }
        });
    }
}
