package collioni.douglas.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spCores;
    private Button btLogin;

    // propriedade que carrega as shared preferences
    private SharedPreferences sharedPreferences;
    // propriedade que edita as shared preferences
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // abre as shared preferences "main" em modo de edição
        sharedPreferences = getSharedPreferences("main", MODE_APPEND);

        // inicializa o editor de preferences
        editor = sharedPreferences.edit();

        carregarComponentes();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String cor = spCores.getSelectedItem().toString();

                // salva os valores nas preferences
                editor.putString("NOME", nome);
                editor.putString("COR", cor);

                // aplica as alterações feitas
                editor.apply();

                Intent intent = new Intent(MainActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void carregarComponentes() {
        etNome = (EditText) findViewById(R.id.et_nome);
        spCores = (Spinner) findViewById(R.id.sp_cores);
        btLogin = (Button) findViewById(R.id.bt_login);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // pega os valores salvos nas shared preferences
        String nome = sharedPreferences.getString("NOME", "");
        String cor = sharedPreferences.getString("COR", "");

        // se já existe um nome e uma cor nas shared preferences, redireciona para o início
        if (!nome.isEmpty() && !cor.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, InicioActivity.class);
            startActivity(intent);
        }
    }
}
