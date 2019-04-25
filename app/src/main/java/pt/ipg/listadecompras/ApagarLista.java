package pt.ipg.listadecompras;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ApagarLista extends AppCompatActivity {

    private Button eliminarlista;
    private Button cancelarapagamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    eliminarlista = (Button) findViewById(R.id.buttonEliminarLista);
    cancelarapagamento= (Button) findViewById(R.id.buttonCancelar);

    eliminarlista.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(ApagarLista.this, getString(R.string.lista_apagada), Toast.LENGTH_SHORT).show();
            finish();
        }
    });

    cancelarapagamento.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

    }

}
