package collioni.douglas.cidades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spRegiao, spEstado, spCidade;
    TextView tvPopulacao;
    ImageView ivImagem;

    ArrayAdapter<String> adapterEstados;
    ArrayList<String> estados;

    ArrayAdapter<Cidade> adapterCidades;
    ArrayList<Cidade> cidades;

    private void carregarViews() {
        spRegiao = (Spinner) findViewById(R.id.sp_regiao);
        spEstado = (Spinner) findViewById(R.id.sp_estado);
        spCidade = (Spinner) findViewById(R.id.sp_cidade);
        tvPopulacao = (TextView) findViewById(R.id.tv_populacao);
        ivImagem = (ImageView) findViewById(R.id.iv_imagem);
    }

    private void configurarSpEstado() {
        estados = new ArrayList<>();

        adapterEstados = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                estados);

        adapterEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spEstado.setAdapter(adapterEstados);
    }

    private void configurarSpCidade() {
        cidades = new ArrayList<>();

        adapterCidades = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                cidades);

        adapterCidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCidade.setAdapter(adapterCidades);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarViews();
        configurarSpEstado();
        configurarSpCidade();

        spRegiao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String regiao = parent.getItemAtPosition(position).toString();
                popularEstados(regiao);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String estado = parent.getItemAtPosition(position).toString();
                popularCidades(estado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spCidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Cidade cidade = (Cidade) parent.getItemAtPosition(position);

                tvPopulacao.setText("");
                tvPopulacao.setText(String.format("População: %s", cidade.getPopulacao()));

                ivImagem.setVisibility(View.INVISIBLE);
                ivImagem.setImageResource(cidade.getImagem());
                ivImagem.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvPopulacao.setText("");
                ivImagem.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void popularCidades(String estado) {
        cidades.clear();

        if (estado.equals("São Paulo")) {
            cidades.add(new Cidade("Campinas", 1500000, 0));
            cidades.add(new Cidade("São Paulo", 1000000, R.drawable.sao_paulo));
        }
        else if (estado.equals("Rio Grande do Sul")) {
            cidades.add(new Cidade("Porto Alegre", 2000000, R.drawable.porto_alegre));
            cidades.add(new Cidade("São Leopoldo", 250000, 0));
        }
        else if (estado.equals("Bahia")) {
            cidades.add(new Cidade("Porto Seguro", 100000, R.drawable.porto_seguro));
            cidades.add(new Cidade("Salvador", 1500000, 0));
        }

        adapterCidades.notifyDataSetChanged();
    }

    private void popularEstados(String regiao) {
        estados.clear();

        if (regiao == getString(R.string.centro_oeste)) {
            estados.add("Goiás");
            estados.add("Mato Grosso");
            estados.add("Mato Grosso do Sul");
        }
        else if (regiao == getString(R.string.nordeste)) {
            estados.add("Bahia");
            estados.add("Ceará");
            estados.add("Pernambuco");
        }
        else if (regiao == getString(R.string.norte)) {
            estados.add("Acre");
            estados.add("Amazonas");
            estados.add("Pará");
        }
        else if (regiao == getString(R.string.sudeste)) {
            estados.add("Minas Gerais");
            estados.add("Rio de Janeiro");
            estados.add("São Paulo");
        }
        else if (regiao == getString(R.string.sul)) {
            estados.add("Paraná");
            estados.add("Santa Catarina");
            estados.add("Rio Grande do Sul");
        }

        adapterEstados.notifyDataSetChanged();
        spEstado.setAdapter(adapterEstados);
    }
}
