package pt.ipg.listadecompras;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EliminarProduto extends AppCompatActivity {

    private Button botaocancelar;
    private Button botaoeliminarproduto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botaocancelar= (Button) findViewById(R.id.buttonCancelar);
        botaoeliminarproduto=(Button) findViewById(R.id.buttonEliminarProduto);

        botaoeliminarproduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EliminarProduto.this, getString(R.string.produto_eliminado_toast), Toast.LENGTH_SHORT).show();
                finish();
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
