package pt.ipg.listadecompras;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarLista extends AppCompatActivity {


    private EditText edittextnomemudar;
    private EditText edittextnomenovo;
    private Button botaoguardar;
    private Button botaocancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edittextnomemudar = (EditText) findViewById(R.id.EditTextEditListaNome);
        edittextnomenovo = (EditText) findViewById(R.id.EditTextEditListaNomeNovo);
        botaocancelar = (Button) findViewById(R.id.buttonCancelarEditarLista);
        botaoguardar = (Button) findViewById(R.id.buttonGuardarEditarLista);

        botaoguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = edittextnomemudar.getText().toString();
                String mensagem2 = edittextnomenovo.getText().toString();
                if (mensagem.trim().length() == 0){
                    edittextnomemudar.setError(getString(R.string.nome_obrigatorio));
                }else if (mensagem.length() <= 3){
                    edittextnomemudar.setError(getString(R.string.numero_minimo_de_caracters));
                }else if(mensagem.length() >= 25){
                    edittextnomemudar.setError(getString(R.string.numero_maximo_de_caracters));
                }else if (mensagem.trim().length() == 0){
                    edittextnomenovo.setError(getString(R.string.nome_obrigatorio));
                }else if (mensagem.length() <= 3){
                    edittextnomenovo.setError(getString(R.string.numero_minimo_de_caracters));
                }else if(mensagem.length() >= 25){
                    edittextnomenovo.setError(getString(R.string.numero_maximo_de_caracters));
                }else {
                    finish();
                    Toast.makeText(EditarLista.this, getString(R.string.lista_alterada), Toast.LENGTH_SHORT).show();
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
