package pt.ipg.listadecompras;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Categorias extends AppCompatActivity {

    private Button inserircategoria;
    private Button editarcategoria;
    private Button eliminarcategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inserircategoria=(Button) findViewById(R.id.buttonInserirCategoria);
        editarcategoria=(Button) findViewById(R.id.buttonEditarCategoria);
        eliminarcategoria=(Button) findViewById(R.id.buttonEliminarCategoria);

        inserircategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Categorias.this, getString(R.string.carregou_para_inserir_categoria), Toast.LENGTH_SHORT).show();
                Intent outraactivity= new Intent(Categorias.this, InserirCategoria.class);
            }
        });
        editarcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Categorias.this, getString(R.string.carregou_para_editar_categoria), Toast.LENGTH_SHORT).show();
                Intent outraactivity= new Intent(Categorias.this, EditarCategoria.class);
            }
        });
        eliminarcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Categorias.this, getString(R.string.carregou_para_eliminar_categoria), Toast.LENGTH_SHORT).show();
                Intent outraactivity= new Intent(Categorias.this, EliminarCategoria.class);
            }
        });

    }

}
