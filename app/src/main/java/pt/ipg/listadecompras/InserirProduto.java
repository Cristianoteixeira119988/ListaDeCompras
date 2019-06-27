package pt.ipg.listadecompras;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class InserirProduto extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int ID_CURSO_LOADER_PRODUTOS = 0;


    private EditText edittextnomeproduto;
    private EditText edittextquantidadeproduto;
    private EditText edittextdataquefaltou;
    //Spinner------------------------------1
    private Spinner spinnercategorias;
    private Spinner spinnerlista;
    //-------------------------------------1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_PRODUTOS, null, this);


        edittextnomeproduto=(EditText) findViewById(R.id.EditTextNomeDoProduto);
        edittextquantidadeproduto=(EditText) findViewById(R.id.EditTextQuantidade);
        edittextdataquefaltou=(EditText) findViewById(R.id.EditTextDataQueFaltou);

        spinnercategorias=(Spinner) findViewById(R.id.spinnerCategoria);
        spinnerlista=(Spinner) findViewById(R.id.spinnerNomeDaLista);

    }


    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_PRODUTOS, null, this);


        super.onResume();
    }
    private void mostraCategoriasSpinner(Cursor cursorCategorias) {
        SimpleCursorAdapter adaptadorCategorias = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursorCategorias,
                new String[]{BdTableCategorias.CAMPO_DESCRICAO},
                new int[]{android.R.id.text1}
        );
        spinnercategorias.setAdapter(adaptadorCategorias);
    }
    private void mostraListasSpinner(Cursor cursorListas) {
        SimpleCursorAdapter adaptadorListas = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursorListas,
                new String[]{BdTableListas.CAMPO_NOME_LISTA},
                new int[]{android.R.id.text1}
        );
        spinnerlista.setAdapter(adaptadorListas);
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


        String nomeproduto = edittextnomeproduto.getText().toString();
        String quantidade= edittextquantidadeproduto.getText().toString();
        String data = edittextdataquefaltou.getText().toString();
        int numeroqt;
        numeroqt= Integer.parseInt(quantidade);
        String[] dataSeparada = data.split("/");

        int dia = Integer.parseInt(dataSeparada[0]);
        int mes = Integer.parseInt(dataSeparada[1]);
        int ano = Integer.parseInt(dataSeparada[2]);

        if(((dia <= 0) | ( dia > 31 ))  || ((mes <= 0) | (mes > 12)) || ((ano <= 2010) | (ano > 2019)) || data.length() != 10 || data.charAt(2) != '/' || data.charAt(5) != '/' ) {
            edittextdataquefaltou.setError(getString(R.string.formato_da_data_invalido));
            edittextdataquefaltou.requestFocus();
        }else if (nomeproduto.trim().length() == 0) {
            edittextnomeproduto.setError(getString(R.string.nome_obrigatorio_geral));
            edittextnomeproduto.requestFocus();
        } else if (nomeproduto.length() <= 3) {
            edittextnomeproduto.setError(getString(R.string.numero_minimo_de_caracters));
            edittextnomeproduto.requestFocus();
        } else if (nomeproduto.length() >= 25) {
            edittextnomeproduto.setError(getString(R.string.numero_maximo_de_caracters));
            edittextnomeproduto.requestFocus();
        } else if (quantidade.trim().length() == 0) {
            edittextquantidadeproduto.setError(getString(R.string.quantidade_obrigatoria));
            edittextquantidadeproduto.requestFocus();
        }else if (numeroqt<=0) {
            edittextquantidadeproduto.setError(getString(R.string.quantidade_invalida));
            edittextquantidadeproduto.requestFocus();
        } else {
            // guardar os dados
            Produtos produtos = new Produtos();
            long idCategoria = spinnercategorias.getSelectedItemId();
            long idLista = spinnerlista.getSelectedItemId();

            produtos.setNomeproduto(nomeproduto);
            produtos.setCategoria(idCategoria);
            produtos.setQuantidade(numeroqt);
            produtos.setDataqueacabou(data);
            produtos.setNomelista(idLista);

            getContentResolver().insert(ListasContentProvider.ENDERECO_PRODUTOS, produtos.getContentValues());
            Toast.makeText(this, getString(R.string.produto_inserido_com_sucesso), Toast.LENGTH_SHORT).show();

            finish();


        }

    }







    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

            android.support.v4.content.CursorLoader cursorLoader = new android.support.v4.content.CursorLoader(this, ListasContentProvider.ENDERECO_CATEGORIAS, BdTableCategorias.TODAS_COLUNAS, null, null, BdTableCategorias.CAMPO_DESCRICAO
            );


            android.support.v4.content.CursorLoader cursorLoader2 = new android.support.v4.content.CursorLoader(this, ListasContentProvider.ENDERECO_LISTAS, BdTableListas.TODAS_COLUNAS, null, null, BdTableListas.CAMPO_NOME_LISTA
            );

            return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        mostraCategoriasSpinner(data);
        mostraListasSpinner(data);



    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<Cursor> loader) {

        mostraCategoriasSpinner(null);
        mostraListasSpinner(null);

    }


}