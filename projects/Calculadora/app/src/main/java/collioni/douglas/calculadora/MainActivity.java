package collioni.douglas.calculadora;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = (EditText) findViewById(R.id.et_numero);
        Button bt0 = (Button) findViewById(R.id.bt_0);
        Button bt1 = (Button) findViewById(R.id.bt_1);
        Button bt2 = (Button) findViewById(R.id.bt_2);
        Button bt3 = (Button) findViewById(R.id.bt_3);
        Button bt4 = (Button) findViewById(R.id.bt_4);
        Button bt5 = (Button) findViewById(R.id.bt_5);
        Button bt6 = (Button) findViewById(R.id.bt_6);
        Button bt7 = (Button) findViewById(R.id.bt_7);
        Button bt8 = (Button) findViewById(R.id.bt_8);
        Button bt9 = (Button) findViewById(R.id.bt_9);
        Button btMult = (Button) findViewById(R.id.bt_mult);
        Button btDiv = (Button) findViewById(R.id.bt_div);
        Button btSub = (Button) findViewById(R.id.bt_sub);
        Button btSoma = (Button) findViewById(R.id.bt_soma);
        Button btIgual = (Button) findViewById(R.id.bt_igual);
        Button btC = (Button) findViewById(R.id.bt_c);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarNumero(((Button)v).getText().toString());
            }
        };

        bt0.setOnClickListener(listener);
        bt1.setOnClickListener(listener);
        bt2.setOnClickListener(listener);
        bt3.setOnClickListener(listener);
        bt4.setOnClickListener(listener);
        bt5.setOnClickListener(listener);
        bt6.setOnClickListener(listener);
        bt7.setOnClickListener(listener);
        bt8.setOnClickListener(listener);
        bt9.setOnClickListener(listener);
        btMult.setOnClickListener(listener);
        btDiv.setOnClickListener(listener);
        btSub.setOnClickListener(listener);
        btSoma.setOnClickListener(listener);

        btIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = etNumero.getText().toString();
                String result = String.format("Não sei calcular: %s", numero);

                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TESTE", "CLICK");
                String numero = etNumero.getText().toString();
                if (!numero.isEmpty()) {
                    numero = numero.substring(0, numero.length() - 1);
                    etNumero.setText(numero);
                }
            }
        });

        btC.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(getString(R.string.confirmacao))
                        .setMessage(getString(R.string.deseja_limpar))
                        .setNegativeButton(getString(R.string.nao), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton(getString(R.string.sim), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                etNumero.setText("");
                            }
                        })
                        .create();

                alert.show();

                return true;
            }
        });
    }

    private void alterarNumero(String numero) {
        etNumero.append(numero);
    }
}
