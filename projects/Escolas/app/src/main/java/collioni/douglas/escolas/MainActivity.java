package collioni.douglas.escolas;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String API_URL = "http://schoollineup.apphb.com/api/";

    private Retrofit retrofit;
    private EscolaService escolaService;

    private ListView lvEscolas;
    private List<Escola> escolas;
    private ArrayAdapter<Escola> adapter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurarRetrofit();
        configurarLista();
    }

    private void configurarLista() {
        lvEscolas = (ListView) findViewById(R.id.lv_escolas);

        escolas = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, escolas);

        lvEscolas.setAdapter(adapter);

        lvEscolas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Escola escola = (Escola) parent.getItemAtPosition(position);

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setMessage(
                                String.format("(%s) %s\n\n%s\n\n%s",
                                        escola.getCodigo(),
                                        escola.getNome(),
                                        escola.getTelefone(),
                                        escola.getEndereco())
                        )
                        .create();

                alertDialog.show();
            }
        });
    }

    private void configurarRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        escolaService = retrofit.create(EscolaService.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressDialog = ProgressDialog.show(MainActivity.this, "", "Carregando escolas...", true);

        Call<List<Escola>> call = escolaService.listarEscolas();

        call.enqueue(new Callback<List<Escola>>() {
            @Override
            public void onResponse(Call<List<Escola>> call, Response<List<Escola>> response) {
                // recarrega as escolas da lista com o resultado do callback
                escolas.clear();
                escolas.addAll(response.body());
                ordenarEscolas(escolas);
                adapter.notifyDataSetChanged();

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Escola>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void ordenarEscolas(List<Escola> escolas) {
        Collections.sort(escolas, new Comparator<Escola>() {
            @Override
            public int compare(Escola lhs, Escola rhs) {
                return lhs.getNome().compareTo(rhs.getNome());
            }
        });
    }
}
