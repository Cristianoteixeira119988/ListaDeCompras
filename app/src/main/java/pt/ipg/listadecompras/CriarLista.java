package pt.ipg.listadecompras;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CriarLista extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private EditText TextEditNomelista;

    private TextView textviewdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_lista_mercearia);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        TextEditNomelista= (EditText) findViewById(R.id.EditTextNomeDalista);

        textviewdata = (TextView) findViewById(R.id.textViewData);
        SimpleDateFormat formatadata= new SimpleDateFormat("dd-MM-yyyy");
        Date data =  new Date();
        String dataFormatada =  formatadata.format(data);

        textviewdata.setText(dataFormatada);






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

        SimpleDateFormat formatadata= new SimpleDateFormat("dd-MM-yyyy");
        Date data =  new Date();
        String dataFormatada =  formatadata.format(data);

        textviewdata.setText(dataFormatada);

        String nomelista = TextEditNomelista.getText().toString();
        if (nomelista.trim().length() == 0){
            TextEditNomelista.setError(getString(R.string.nome_obrigatorio));
        }else if (nomelista.length() <= 3){
            TextEditNomelista.setError(getString(R.string.numero_minimo_de_caracters));
        }else if(nomelista.length() >= 25){
            TextEditNomelista.setError(getString(R.string.numero_maximo_de_caracters));
        }else {
            Toast.makeText(CriarLista.this, getString(R.string.lista_criada), Toast.LENGTH_SHORT).show();
            finish();
        }

        Listas listas = new Listas();

        listas.setNomelista(nomelista);
        listas.setDatacriacao(dataFormatada);

        try {
            getContentResolver().insert(ListasContentProvider.ENDERECO_LISTAS, listas.getContentValues());

            Toast.makeText(this, getString(R.string.lista_criada), Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Snackbar.make(
                    TextEditNomelista,
                    getString(R.string.Erro),
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
