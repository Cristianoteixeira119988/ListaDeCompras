package pt.ipg.listadecompras;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EditarLista extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{


    private EditText edittextnomenovo;
    private EditText editetextdata;

    private Listas listas= null;

    private Uri enderecoListaEditar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edittextnomenovo = (EditText) findViewById(R.id.EditTextEditListaNomeNovo);
        editetextdata= (EditText) findViewById(R.id.EditTextDataCriacaoEd);

        Intent intent = getIntent();

        long idListas = intent.getLongExtra(MinhasListas.ID_LISTA, -1);

        if(idListas==-1){
            Toast.makeText(this, "Erro: não foi possível ler a lista", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        enderecoListaEditar = Uri.withAppendedPath(ListasContentProvider.ENDERECO_LISTAS, String.valueOf(idListas));

        Cursor cursor = getContentResolver().query(enderecoListaEditar, BdTableListas.TODAS_COLUNAS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Erro: não foi possível ler a lista", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        listas = Listas.fromCursor(cursor);

        edittextnomenovo.setText(listas.getNomelista());
        editetextdata.setText(String.valueOf(listas.getDatacriacao()));
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



        String nomenovo = edittextnomenovo.getText().toString();
        String data = editetextdata.getText().toString();
        if (nomenovo.trim().length() == 0) {
            edittextnomenovo.setError(getString(R.string.nome_obrigatorio));
            edittextnomenovo.requestFocus();
        } else if (nomenovo.length() <= 3) {
            edittextnomenovo.setError(getString(R.string.numero_minimo_de_caracters));
            edittextnomenovo.requestFocus();
        }  else if (data.trim().length()==0) {
            editetextdata.setError(getString(R.string.data_obrigatoria));
            editetextdata.requestFocus();
        }else if (nomenovo.length() >= 25) {
            edittextnomenovo.setError(getString(R.string.numero_maximo_de_caracters));
            edittextnomenovo.requestFocus();
        } else {
            finish();
            Toast.makeText(EditarLista.this, getString(R.string.lista_alterada), Toast.LENGTH_SHORT).show();
        }

        // guardar os dados
        listas.setNomelista(nomenovo);
        listas.setDatacriacao(data);

        try {
            getContentResolver().update(enderecoListaEditar, listas.getContentValues(), null, null);

            Toast.makeText(this, getString(R.string.lista_alterada), Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Snackbar.make(
                    edittextnomenovo,
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


