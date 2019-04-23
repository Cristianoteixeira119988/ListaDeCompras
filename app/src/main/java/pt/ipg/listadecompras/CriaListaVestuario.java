package pt.ipg.listadecompras;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class CriaListaVestuario extends AppCompatActivity {

    private Button botaoguardar;
    private EditText TextEditNomelista;
    private Button botaocancelar;
    private TextView textviewdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_lista_vestuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaoguardar = (Button) findViewById(R.id.buttonGuardar3);
        TextEditNomelista= (EditText) findViewById(R.id.EditTextNomeDalista3);
        botaocancelar = (Button) findViewById(R.id.buttonCancelar3);
        textviewdata = (TextView) findViewById(R.id.textViewData3);


        SimpleDateFormat formatadata= new SimpleDateFormat("dd--mm--yyyy");
        Date data =  new Date();
        String dataFormatada =  formatadata.format(data);

        textviewdata.setText(dataFormatada);

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
                    Toast.makeText(CriaListaVestuario.this, getString(R.string.lista_criada), Toast.LENGTH_SHORT).show();
                    Intent paguinainicial = new Intent(CriaListaVestuario.this, AdicionarProduto.class);
                    startActivity(paguinainicial);
                }
            }

        });

        botaocancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paguinainicial = new Intent(CriaListaVestuario.this, MainActivity.class);
                startActivity(paguinainicial);
            }
        });




    }

}