package pt.ipg.listadecompras;

import android.database.Cursor;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class InserirCategoria extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int ID_CURSO_LOADER_CATEGORIAS = 0;

    private EditText edittextnomedacategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_CATEGORIAS, null, this);

        edittextnomedacategoria=(EditText) findViewById(R.id.EditTextNomeDaCategoria);





    }
    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_CATEGORIAS, null, this);


        super.onResume();
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

    private void guardar(){

        String nomecategoria = edittextnomedacategoria.getText().toString();
        if (nomecategoria.trim().length() == 0){
            edittextnomedacategoria.setError(getString(R.string.nome_obrigatorio_geral));
        }else if (nomecategoria.length() <= 3){
            edittextnomedacategoria.setError(getString(R.string.numero_minimo_de_caracters));
        }else if(nomecategoria.length() >= 25){
            edittextnomedacategoria.setError(getString(R.string.numero_maximo_de_caracters));
        }else {
            Categoria categoria = new Categoria();

            categoria.setDescricao(nomecategoria);
            getContentResolver().insert(ListasContentProvider.ENDERECO_CATEGORIAS, categoria.getContentValues());

            Toast.makeText(InserirCategoria.this, getString(R.string.categoria_criada), Toast.LENGTH_SHORT).show();
            finish();
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



