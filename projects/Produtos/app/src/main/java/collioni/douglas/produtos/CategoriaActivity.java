package collioni.douglas.produtos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class CategoriaActivity extends AppCompatActivity {

    // define uma constante com o nome do parâmetro
    final static String BUNDLE_CATEGORIA = "categoria";

    ListView lvCategorias;

    private void carregarComponentes() {
        lvCategorias = (ListView) findViewById(R.id.lv_categorias);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);

        setTitle("Categorias");
        carregarComponentes();

        // adiciona evento de item click na lista
        lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // pega a categoria clicada
                String categoria = (String) parent.getItemAtPosition(position);

                // cria uma intent para a activity produto
                Intent intent = new Intent(CategoriaActivity.this, ProdutoActivity.class);

                // adiciona a categoria selecionada na intent
                intent.putExtra(BUNDLE_CATEGORIA, categoria);

                // inicia a nova activity
                startActivity(intent);
            }
        });
    }
}
