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

public class InserirCategoria extends AppCompatActivity {

    private Button botaoinserircategoria;
    private Button botaocancelar;
    private EditText edittextnomedacategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaocancelar=(Button) findViewById(R.id.buttonCancelar);
        botaoinserircategoria=(Button) findViewById(R.id.buttonInserirCaategoria);
        edittextnomedacategoria=(EditText) findViewById(R.id.EditTextNomeDaCategoria);

        botaoinserircategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mensagem = edittextnomedacategoria.getText().toString();
                if (mensagem.trim().length() == 0){
                    edittextnomedacategoria.setError(getString(R.string.nome_obrigatorio));
                }else if (mensagem.length() <= 3){
                    edittextnomedacategoria.setError(getString(R.string.numero_minimo_de_caracters));
                }else if(mensagem.length() >= 25){
                    edittextnomedacategoria.setError(getString(R.string.numero_maximo_de_caracters));
                }else {
                    Toast.makeText(InserirCategoria.this, getString(R.string.categoria_criada), Toast.LENGTH_SHORT).show();
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
