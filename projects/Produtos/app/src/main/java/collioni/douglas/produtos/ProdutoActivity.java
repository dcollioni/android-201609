package collioni.douglas.produtos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {

    final static String BUNDLE_PRODUTO = "produto";
    String categoria;
    ListView lvProdutos;
    ArrayList<Produto> produtos;
    ArrayAdapter<Produto> adapter;

    private void carregarComponentes() {
        lvProdutos = (ListView) findViewById(R.id.lv_produtos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        carregarComponentes();
        configurarLista();

        // pega a intent que gerou a criação dessa activity
        Intent intent = getIntent();

        // pega o valor do parâmetro "categoria" passado pela intent
        categoria = intent.getStringExtra(CategoriaActivity.BUNDLE_CATEGORIA);

        // atualiza o título da activity
        setTitle(String.format("Produtos (%s)", categoria));
    }

    private void configurarLista() {
        produtos = new ArrayList<>();
        adapter = new ArrayAdapter<>(ProdutoActivity.this, android.R.layout.simple_list_item_1, produtos);
        lvProdutos.setAdapter(adapter);

        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // pega o produto clicado
                Produto produto = (Produto) parent.getItemAtPosition(position);

                // cria uma intent para a tela de detalhe
                Intent intent = new Intent(ProdutoActivity.this, DetalheActivity.class);

                // passa o produto para a intent nova (Produto deve implementar Serializable)
                intent.putExtra(BUNDLE_PRODUTO, produto);

                // inicia a activity detalhe
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // limpa o array de produtos
        produtos.clear();

        // a partir da categoria que chegou, carrega os produtos adequados na lista
        if (categoria.equals(getString(R.string.eletronicos))) {
            produtos.add(new Produto("Notebook", 3500, 25));
            produtos.add(new Produto("iPhone", 5500, 30));
            produtos.add(new Produto("Tablet", 2000, 18));
        } else if (categoria.equals(getString(R.string.escritorio))) {
            produtos.add(new Produto("Caneta", 20, 130));
            produtos.add(new Produto("Papel", 35, 400));
        } else if (categoria.equals(getString(R.string.outros))) {
            produtos.add(new Produto("Outro 1", 1000, 40));
            produtos.add(new Produto("Outro 2", 2000, 80));
        }

        // notifica o adapter que os dados mudaram
        adapter.notifyDataSetChanged();
    }
}
