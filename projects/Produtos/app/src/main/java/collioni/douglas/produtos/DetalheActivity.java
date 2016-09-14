package collioni.douglas.produtos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetalheActivity extends AppCompatActivity {

    TextView tvDetalhe;

    private void carregarComponentes() {
        tvDetalhe = (TextView) findViewById(R.id.tv_detalhe);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        setTitle("Detalhe");
        carregarComponentes();

        // pega a intent que gerou essa activity
        Intent intent = getIntent();

        // pega o produto passado por parâmetro no bundle
        Produto produto = (Produto) intent.getSerializableExtra(ProdutoActivity.BUNDLE_PRODUTO);

        tvDetalhe.setText(String.format("Nome: %s\nPreço: %s\nQtd. Estoque: %s",
                produto.getNome(), produto.getPreco(), produto.getQtdEstoque()));
    }
}
