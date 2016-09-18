package collioni.douglas.login;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InicioActivity extends AppCompatActivity {

    private LinearLayout llInicio;
    private TextView tvNome;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        setTitle("Início");

        sharedPreferences = getSharedPreferences("main", MODE_APPEND);
        editor = sharedPreferences.edit();

        carregarComponentes();
    }

    private void carregarComponentes() {
        llInicio = (LinearLayout) findViewById(R.id.ll_inicio);
        tvNome = (TextView) findViewById(R.id.tv_nome);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String nome = sharedPreferences.getString("NOME", "");
        String cor = sharedPreferences.getString("COR", "");

        tvNome.setText(String.format("Olá, %s!", nome));

        if (cor.equals(getString(R.string.azul))) {
            llInicio.setBackgroundResource(R.color.azul);
        } else if (cor.equals(getString(R.string.amarelo))) {
            llInicio.setBackgroundResource(R.color.amarelo);
        } else if (cor.equals(getString(R.string.vermelho))) {
            llInicio.setBackgroundResource(R.color.vermelho);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_logout:
                // limpa os dados da sharedPreferences
                editor.remove("NOME");
                editor.remove("COR");
                editor.apply();

                // finaliza essa activity
                finish();
                return true;
        }

        return false;
    }
}
