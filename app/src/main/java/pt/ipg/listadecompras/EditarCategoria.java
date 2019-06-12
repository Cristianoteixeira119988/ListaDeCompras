package pt.ipg.listadecompras;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarCategoria extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText edittextnovonomedacategoria;
    private Categoria categorias= null;

    private Uri enderecoCategoriaEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edittextnovonomedacategoria=(EditText) findViewById(R.id.EditTextNovoNomeCategoria);

        Intent intent = getIntent();

        long idCategorias = intent.getLongExtra(Categorias.ID_CATEGORIA, -1);

        if(idCategorias==-1){
            Toast.makeText(this, "Erro: não foi possível ler a categoria", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        enderecoCategoriaEditar = Uri.withAppendedPath(ListasContentProvider.ENDERECO_CATEGORIAS, String.valueOf(idCategorias));

        Cursor cursor = getContentResolver().query(enderecoCategoriaEditar, BdTableCategorias.TODAS_COLUNAS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Erro: não foi possível ler a categoria", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        categorias = Categoria.fromCursor(cursor);

        edittextnovonomedacategoria.setText(categorias.getDescricao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guardar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_guardar) {
            guardar();
            return true;
        } else if (id == R.id.action_cancelar) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void guardar() {

        String novonomecategoria = edittextnovonomedacategoria.getText().toString();
        if (novonomecategoria.trim().length() == 0){
            edittextnovonomedacategoria.setError(getString(R.string.nome_obrigatorio_geral));
        }else if (novonomecategoria.length() <= 3){
            edittextnovonomedacategoria.setError(getString(R.string.numero_minimo_de_caracters));
        }else if(novonomecategoria.length() >= 25){
            edittextnovonomedacategoria.setError(getString(R.string.numero_maximo_de_caracters));
        }else {
            Toast.makeText(EditarCategoria.this, getString(R.string.categoria_guardada), Toast.LENGTH_SHORT).show();
            finish();
        }

        // guardar os dados
        categorias.setDescricao(novonomecategoria);


        try {
            getContentResolver().update(enderecoCategoriaEditar, categorias.getContentValues(), null, null);

            Toast.makeText(this, getString(R.string.lista_alterada), Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Snackbar.make(
                    edittextnovonomedacategoria,
                    getString(R.string.erro),
                    Snackbar.LENGTH_LONG)
                    .show();

            e.printStackTrace();
        }
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
