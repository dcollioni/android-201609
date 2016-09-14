package collioni.douglas.tarefascustom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etDescricao;
    Spinner spPrioridade;
    Button btAdicionar;
    ListView lvTarefas;
    ArrayList<Tarefa> tarefas;
    TarefaAdapter adapter;

    private void carregarComponentes() {
        lvTarefas = (ListView) findViewById(R.id.lv_tarefas);
        etDescricao = (EditText) findViewById(R.id.et_descricao);
        spPrioridade = (Spinner) findViewById(R.id.sp_prioridade);
        btAdicionar = (Button) findViewById(R.id.bt_adicionar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarComponentes();
        configurarLista();

        // adiciona evento de click no botão
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pega os valores dos campos
                String descricao = etDescricao.getText().toString();
                String prioridade = spPrioridade.getSelectedItem().toString();

                // adiciona uma nova tarefa no array
                tarefas.add(new Tarefa(descricao, prioridade));

                // notifica o adapter que os dados mudaram
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void configurarLista() {
        // inicializa o array de tarefas
        tarefas = new ArrayList<>();
        tarefas.add(new Tarefa("Estudar Android", "Alta"));
        tarefas.add(new Tarefa("Desenvolver um App", "Alta"));
        tarefas.add(new Tarefa("Publicar na Google Play", "Média"));
        tarefas.add(new Tarefa("Ficar rico", "Baixa"));

        // inicializa o adapter
        adapter = new TarefaAdapter(MainActivity.this, tarefas);

        // seta o adapter na lista
        lvTarefas.setAdapter(adapter);
    }
}
