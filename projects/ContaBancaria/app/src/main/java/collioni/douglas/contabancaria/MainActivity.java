package collioni.douglas.contabancaria;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etNome, etCpfCnpj;
    RadioGroup rgTipo;
    CheckBox cbCartao, cbSeguro, cbTalao;
    Button btCadastrar;

    private void carregarViews() {
        etNome = (EditText) findViewById(R.id.et_nome);
        etCpfCnpj = (EditText) findViewById(R.id.et_cpf_cnpj);
        rgTipo = (RadioGroup) findViewById(R.id.rg_tipo);
        cbCartao = (CheckBox) findViewById(R.id.cb_cartao);
        cbSeguro = (CheckBox) findViewById(R.id.cb_seguro);
        cbTalao = (CheckBox) findViewById(R.id.cb_talao);
        btCadastrar = (Button) findViewById(R.id.bt_cadastrar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarViews();

        rgTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                etCpfCnpj.setText("");
                if (checkedId == R.id.rb_pf) {
                    etCpfCnpj.setHint(R.string.cpf);
                }
                else if (checkedId == R.id.rb_pj) {
                    etCpfCnpj.setHint(R.string.cnpj);
                }
            }
        });

        cbCartao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbSeguro.setChecked(false);
                cbSeguro.setEnabled(isChecked);
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();

                String tipo = (rgTipo.getCheckedRadioButtonId() == R.id.rb_pf)
                        ? getString(R.string.pessoa_fisica) : getString(R.string.pessoa_juridica);

                String cpfCnpjLabel = etCpfCnpj.getHint().toString();
                String cpfCnpj = etCpfCnpj.getText().toString();

                String cartao = cbCartao.isChecked() ? getString(R.string.sim) : getString(R.string.nao);
                String seguro = cbSeguro.isChecked() ? getString(R.string.sim) : getString(R.string.nao);
                String talao = cbTalao.isChecked() ? getString(R.string.sim) : getString(R.string.nao);

                StringBuilder builder = new StringBuilder();
                builder.append(String.format("%s: %s", getString(R.string.nome), nome));
                builder.append(String.format("\n%s: %s", getString(R.string.tipo), tipo));
                builder.append(String.format("\n%s: %s", cpfCnpjLabel, cpfCnpj));
                builder.append(String.format("\n%s: %s", getString(R.string.cartao_de_credito), cartao));
                builder.append(String.format("\n%s: %s", getString(R.string.seguro_do_cartao), seguro));
                builder.append(String.format("\n%s: %s", getString(R.string.talao_de_cheques), talao));

                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(builder.toString())
                        .create()
                        .show();
            }
        });
    }
}
