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

public class TodosProduto extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSO_LOADER_PRODUTOS = 0;
    private Button botaoinserirproduto;
    private Button botaoeditarproduto;
    private Button botaoeliminarproduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_PRODUTOS, null, this);

        botaoinserirproduto=(Button) findViewById(R.id.buttonInserirProduto);
        botaoeditarproduto=(Button) findViewById(R.id.buttonEditarProduto);
        botaoeliminarproduto=(Button) findViewById(R.id.buttonEliminarProduto);

        botaoinserirproduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outraactivity= new Intent(TodosProduto.this,InserirProduto.class);
                startActivity(outraactivity);
                Toast.makeText(getApplicationContext(), getString(R.string.carregou_para_adicionar_produtoo), Toast.LENGTH_SHORT).show();
            }
        });
        botaoeditarproduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outraactivity= new Intent(TodosProduto.this,EditarProduto2.class);
                startActivity(outraactivity);
                Toast.makeText(getApplicationContext(), getString(R.string.carregou_para_editar_produto), Toast.LENGTH_SHORT).show();
            }
        });
        botaoeliminarproduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outraactivity= new Intent(TodosProduto.this,EliminarProduto.class);
                startActivity(outraactivity);
                Toast.makeText(getApplicationContext(), getString(R.string.carregou_para_eliminar_produto), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(this, ListasContentProvider.ENDERECO_PRODUTOS, BdTableProdutos.TODAS_COLUNAS, null, null, BdTableProdutos.CAMPO_NOME_PRODUTO);


        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Toast.makeText(this, ("Contem" + ID_CURSO_LOADER_PRODUTOS + "produtos"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
