package collioni.douglas.consultacep;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String API_URL = "http://correiosapi.apphb.com/";

    private Retrofit retrofit;
    private CorreioService correioService;

    private EditText etCep;
    private Button btBuscar;
    private TextView tvEndereco;

    // propriedade para mostrar uma mensagem de progresso
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarComponentes();
        configurarRetrofit();
        configurarBtBuscar();
    }

    private void configurarBtBuscar() {
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mostra mensagem de carregando
                progressDialog = ProgressDialog.show(
                        MainActivity.this, "", "Buscando CEP...", true);

                String cep = etCep.getText().toString();

                // limpa o resultado anterior
                tvEndereco.setText("");

                // cria uma call de getEndereco passando o cep
                Call<Endereco> call = correioService.getEndereco(cep);

                call.enqueue(new Callback<Endereco>() {
                    @Override
                    public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                        // fecha mensagem de carregando
                        progressDialog.dismiss();

                        // pega o objeto endereço retornado
                        Endereco endereco = response.body();

                        // se a resposta for inválida
                        if (!response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "CEP não encontrado", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        tvEndereco.setText(
                                String.format("%s %s \nBairro: %s \nCidade: %s \nEstado: %s",
                                        endereco.getTipoDeLogradouro(),
                                        endereco.getLogradouro(),
                                        endereco.getBairro(),
                                        endereco.getCidade(),
                                        endereco.getEstado())
                        );
                    }

                    @Override
                    public void onFailure(Call<Endereco> call, Throwable t) {
                    }
                });
            }
        });
    }

    private void configurarRetrofit() {
        // cria o retrofit passando a URL base e adicionando o converter GSON
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // cria o service a partir do retrofit
        correioService = retrofit.create(CorreioService.class);
    }

    private void carregarComponentes() {
        etCep = (EditText) findViewById(R.id.et_cep);
        btBuscar = (Button) findViewById(R.id.bt_buscar);
        tvEndereco = (TextView) findViewById(R.id.tv_endereco);
    }
}
