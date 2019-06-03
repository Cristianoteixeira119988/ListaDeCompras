package pt.ipg.listadecompras;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Categorias extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int ID_CURSO_LOADER_CATEGORIAS = 0;
    private Button inserircategoria;
    private Button editarcategoria;
    private Button eliminarcategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_CATEGORIAS, null, this);

        inserircategoria=(Button) findViewById(R.id.buttonInserirCategoria);
        editarcategoria=(Button) findViewById(R.id.buttonEditarCategoria);
        eliminarcategoria=(Button) findViewById(R.id.buttonEliminarCategoria);

        inserircategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Categorias.this, getString(R.string.carregou_para_inserir_categoria), Toast.LENGTH_SHORT).show();
                Intent outraactivity= new Intent(Categorias.this, InserirCategoria.class);
                startActivity(outraactivity);
            }
        });
        editarcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Categorias.this, getString(R.string.carregou_para_editar_categoria), Toast.LENGTH_SHORT).show();
                Intent outraactivity= new Intent(Categorias.this, EditarCategoria.class);
                startActivity(outraactivity);
            }
        });
        eliminarcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Categorias.this, getString(R.string.carregou_para_eliminar_categoria), Toast.LENGTH_SHORT).show();
                Intent outraactivity= new Intent(Categorias.this, EliminarCategoria.class);
                startActivity(outraactivity);
            }
        });

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {

        CursorLoader cursorLoader = new CursorLoader(this, ListasContentProvider.ENDERECO_CATEGORIAS, BdTableCategorias.TODAS_COLUNAS, null, null, BdTableCategorias.CAMPO_DESCRICAO);


        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Toast.makeText(this, ("Contem" + ID_CURSO_LOADER_CATEGORIAS + "produtos"), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
