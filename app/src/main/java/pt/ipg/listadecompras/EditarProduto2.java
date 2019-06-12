package pt.ipg.listadecompras;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.CursorLoader;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditarProduto2 extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor> {


    private TextView txtViewVerData;
    private Button botaoescolherdata;
    Calendar calendario;
    DatePickerDialog datapiker;
    private Spinner spinnercategorias;
    private EditText edittextnomeproduto;
    private EditText edittextquantidadeproduto;


    private static final int ID_CURSO_LOADER_PRODUTOS = 0;

    private Spinner spinnerNomeLista;


    private Produtos produtos = null;

    private boolean categoriasCarregadas = false;
    private boolean categoriaAtualizada = false;
    private boolean ListasCarregadas = false;
    private boolean ListasAtualizada = false;

    private Uri enderecoProdutoEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_produto2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edittextnomeproduto = (EditText) findViewById(R.id.EditTextNomeDoProdutoEd);
        spinnercategorias = (Spinner) findViewById(R.id.spinnerCategoriaEd);
        edittextquantidadeproduto = (EditText) findViewById(R.id.EditTextQuantidadeEd);

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_PRODUTOS, null, this);


        Intent intent = getIntent();

        long idProduto = intent.getLongExtra(TodosProduto.ID_PRODUTO, -1);

        if (idProduto == -1) {
            Toast.makeText(this, "Erro: não foi possível ler o Produto", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        enderecoProdutoEditar = Uri.withAppendedPath(ListasContentProvider.ENDERECO_PRODUTOS, String.valueOf(idProduto));

        Cursor cursor = getContentResolver().query(enderecoProdutoEditar, BdTableProdutos.TODAS_COLUNAS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Erro: não foi possível ler o Produto", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        produtos = Produtos.fromCursor(cursor);

        edittextnomeproduto.setText(produtos.getNomeproduto());
        edittextquantidadeproduto.setText(String.valueOf(produtos.getQuantidade()));

        actualizaCategoriaSelecionada();



        //calendario-----------------------------4
        txtViewVerData = (TextView) findViewById(R.id.textViewDataEd);
        botaoescolherdata = (Button) findViewById(R.id.buttonEscolherDataQueFaltou2);
        botaoescolherdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendario = Calendar.getInstance();
                int dia = calendario.get(Calendar.DAY_OF_MONTH);
                int mes = calendario.get(Calendar.MONTH);
                int ano = calendario.get(Calendar.YEAR);

                datapiker = new DatePickerDialog(EditarProduto2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mAno, int mMes, int mDia) {
                        txtViewVerData.setText(mDia + "-" + (mMes + 1) + "-" + mAno);
                    }
                }, dia, mes, ano);
                datapiker.show();
            }
        });
        //-----------------------------------------4

        edittextnomeproduto = (EditText) findViewById(R.id.EditTextNomeDoProdutoEd);
        edittextquantidadeproduto = (EditText) findViewById(R.id.EditTextQuantidadeEd);

        SimpleDateFormat formatadata = new SimpleDateFormat("dd-MM-yyyy");
        Date data = new Date();
        String dataFormatada = formatadata.format(data);

        txtViewVerData.setText(dataFormatada);

    }






    private void actualizaCategoriaSelecionada() {
        if (!categoriasCarregadas) return;
        if (categoriaAtualizada) return;

        for (int i = 0; i < spinnercategorias.getCount(); i++) {
            if (spinnercategorias.getItemIdAtPosition(i) == produtos.getCategoria()) {
                spinnercategorias.setSelection(i);
                break;
            }
        }

        categoriaAtualizada = true;
    }
    private void actualizaListaSelecionada() {
        if (!ListasCarregadas) return;
        if (ListasAtualizada) return;

        for (int i = 0; i < spinnerNomeLista.getCount(); i++) {
            if (spinnerNomeLista.getItemIdAtPosition(i) == produtos.getNomelista()) {
                spinnerNomeLista.setSelection(i);
                break;
            }
        }

        ListasAtualizada = true;
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
                android.R.layout.simple_list_item_2,
                cursorListas,
                new String[]{BdTableListas.CAMPO_NOME_LISTA},
                new int[]{android.R.id.text2}
        );
        spinnerNomeLista.setAdapter(adaptadorListas);
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
        String nomeproduto= edittextnomeproduto.getText().toString();
        String quantidade= edittextquantidadeproduto.getText().toString();
        int numeroqt;
        numeroqt= Integer.parseInt(quantidade);


        if (nomeproduto.trim().length() == 0) {
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
        }else if (numeroqt<=0){
            edittextquantidadeproduto.setError(getString(R.string.quantidade_invalida));
            edittextquantidadeproduto.requestFocus();
        } else {
            finish();
            Toast.makeText(EditarProduto2.this, getString(R.string.produto_inserido_com_sucesso), Toast.LENGTH_SHORT).show();
        }
        // guardar os dados
        produtos.setNomeproduto(nomeproduto);

        produtos.setQuantidade(numeroqt);
        try {
            getContentResolver().update(enderecoProdutoEditar, produtos.getContentValues(), null, null);

            Toast.makeText(this, "Produto alterado com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Snackbar.make(
                    edittextnomeproduto,
                    getString(R.string.erro),
                    Snackbar.LENGTH_LONG)
                    .show();

            e.printStackTrace();
        }
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
       return null;
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     *
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data   The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mostraCategoriasSpinner(data);
        categoriasCarregadas = true;
        actualizaCategoriaSelecionada();


        mostraListasSpinner(data);
        ListasCarregadas = true;
        actualizaListaSelecionada();
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        categoriasCarregadas = false;
        categoriaAtualizada = false;
        ListasAtualizada= false;
        ListasAtualizada = false;
        mostraListasSpinner(null);
        mostraCategoriasSpinner(null);
    }
}




