package pt.ipg.listadecompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CriarLista extends AppCompatActivity {

    private Button botaoguardar;
    private EditText TextEditNomelista;
    private Button botaocancelar;
    private TextView textviewdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_lista_mercearia);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaoguardar = (Button) findViewById(R.id.buttonGuardar);
        TextEditNomelista= (EditText) findViewById(R.id.EditTextNomeDalista);
        botaocancelar = (Button) findViewById(R.id.buttonCancelar);
        textviewdata = (TextView) findViewById(R.id.textViewData);


        SimpleDateFormat formatadata= new SimpleDateFormat("dd-MM-yyyy");
        Date data =  new Date();
        String dataFormatada =  formatadata.format(data);

        textviewdata.setText(dataFormatada);

        botaoguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomelista = TextEditNomelista.getText().toString();
                if (nomelista.trim().length() == 0){
                    TextEditNomelista.setError(getString(R.string.nome_obrigatorio));
                }else if (nomelista.length() <= 3){
                    TextEditNomelista.setError(getString(R.string.numero_minimo_de_caracters));
                }else if(nomelista.length() >= 25){
                    TextEditNomelista.setError(getString(R.string.numero_maximo_de_caracters));
                }else {
                        Toast.makeText(CriarLista.this, getString(R.string.lista_criada), Toast.LENGTH_SHORT).show();
                        finish();
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
