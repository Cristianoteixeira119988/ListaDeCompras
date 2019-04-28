package pt.ipg.listadecompras;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EliminarCategoria extends AppCompatActivity {

    private Button botaocancelar;
    private Button botaoeliminarcategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaocancelar=(Button) findViewById(R.id.buttonCancelar);
        botaoeliminarcategoria=(Button) findViewById(R.id.buttonEliminarCategoria);

        botaocancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        botaoeliminarcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EliminarCategoria.this, getString(R.string.categoria_eliminada_com_sucesso), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}
