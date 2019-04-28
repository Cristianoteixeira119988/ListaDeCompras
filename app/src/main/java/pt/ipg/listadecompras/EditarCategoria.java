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

public class EditarCategoria extends AppCompatActivity {
    private Button botaoguardarcategoria;
    private Button botaocancelar;
    private EditText edittextnovonomedacategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaocancelar=(Button) findViewById(R.id.buttonCancelar);
        botaoguardarcategoria=(Button) findViewById(R.id.buttonGuardarCategoria);
        edittextnovonomedacategoria=(EditText) findViewById(R.id.EditTextNovoNomeCategoria);



        botaoguardarcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mensagem = edittextnovonomedacategoria.getText().toString();
                if (mensagem.trim().length() == 0){
                    edittextnovonomedacategoria.setError(getString(R.string.nome_obrigatorio_geral));
                }else if (mensagem.length() <= 3){
                    edittextnovonomedacategoria.setError(getString(R.string.numero_minimo_de_caracters));
                }else if(mensagem.length() >= 25){
                    edittextnovonomedacategoria.setError(getString(R.string.numero_maximo_de_caracters));
                }else {
                    Toast.makeText(EditarCategoria.this, getString(R.string.categoria_guardada), Toast.LENGTH_SHORT).show();
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
