package pt.ipg.listadecompras;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CriarListaMercearia extends AppCompatActivity {

    private Button botaoguardar;
    private EditText TextEditNomelista;
    private Button botaocancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_lista_mercearia);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaoguardar = (Button) findViewById(R.id.buttonGuardar);
        TextEditNomelista= (EditText) findViewById(R.id.EditTextNomeDalista);
        botaocancelar = (Button) findViewById(R.id.buttonCancelar);



        botaoguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mensagem = TextEditNomelista.getText().toString();
                if (mensagem.trim().length() == 0){
                    TextEditNomelista.setError(getString(R.string.nome_obrigatorio));
                }else if (mensagem.length() <= 3){
                    TextEditNomelista.setError(getString(R.string.numero_minimo_de_caracters));
                }else if(mensagem.length() >= 25){
                    TextEditNomelista.setError(getString(R.string.numero_maximo_de_caracters));
                }else {
                        finish();
                        Toast.makeText(CriarListaMercearia.this, getString(R.string.lista_criada), Toast.LENGTH_SHORT).show();
                    }
                }

        });

        botaocancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }

}
