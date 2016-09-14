package collioni.douglas.produtos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btEntrar;

    private void carregarComponentes() {
        btEntrar = (Button) findViewById(R.id.bt_entrar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarComponentes();

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cria uma intent para a activity categoria
                Intent intent = new Intent(MainActivity.this, CategoriaActivity.class);

                // inicia a categoria activity
                startActivity(intent);
            }
        });
    }
}
