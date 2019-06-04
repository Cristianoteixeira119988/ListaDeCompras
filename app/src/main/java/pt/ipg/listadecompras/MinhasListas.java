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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MinhasListas extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSO_LOADER_LISTAS = 0;
    public static final String ID_LISTA = "ID_LISTA";


    private AdaptadorListas adaptadorListas;
    private RecyclerView recyclerViewListas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_listas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewListas = (RecyclerView) findViewById(R.id.recyclerViewListas);
        adaptadorListas = new AdaptadorListas(this);
        recyclerViewListas.setAdapter(adaptadorListas);
        recyclerViewListas.setLayoutManager(new LinearLayoutManager(this));

        getSupportLoaderManager().initLoader(ID_CURSO_LOADER_LISTAS, null, this);



    }

    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSO_LOADER_LISTAS, null, this);

        super.onResume();
    }
    private Menu menu;

    public void atualizaOpcoesMenu(){
        Listas listas = adaptadorListas.getListaSelecionada();

        boolean mostraAlterarEliminar = (listas != null);

        menu.findItem(R.id.action_alterar).setVisible(mostraAlterarEliminar);
        menu.findItem(R.id.action_eliminar).setVisible(mostraAlterarEliminar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        this.menu = menu;

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_inserir) {
            Intent intent = new Intent(this, CriarLista.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.action_alterar) {
            Intent intent = new Intent(this, EditarLista.class);
            intent.putExtra(ID_LISTA, adaptadorListas.getListaSelecionada().getId());

            startActivity(intent);

            return true;
        } else if (id == R.id.action_eliminar) {
            Intent intent = new Intent(this, ApagarLista.class);
            intent.putExtra(ID_LISTA, adaptadorListas.getListaSelecionada().getId());

            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(this, ListasContentProvider.ENDERECO_LISTAS, BdTableListas.TODAS_COLUNAS, null, null, BdTableListas.CAMPO_NOME_LISTA
        );

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adaptadorListas.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adaptadorListas.setCursor(null);
    }
}
