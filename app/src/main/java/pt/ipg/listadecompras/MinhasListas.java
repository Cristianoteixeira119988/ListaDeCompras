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
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MinhasListas extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSO_LOADER_LISTAS = 0;
    private Button botaoeditarlista;
    private Button botaoapagarlista;
    private Button botaocriarlistaminhaslistas;
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

        botaoeditarlista = (Button) findViewById(R.id.buttonEditarLista);
        botaoapagarlista = (Button) findViewById(R.id.buttonApagarLista);
        botaocriarlistaminhaslistas=(Button) findViewById(R.id.buttonCriarlistaML);

        botaoapagarlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.carregar_botao_apagar), Toast.LENGTH_SHORT).show();
                Intent outraactivity= new Intent(MinhasListas.this,ApagarLista.class );
                startActivity(outraactivity);


            }
        });

        botaoeditarlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.carregar_botao_editar_lista), Toast.LENGTH_SHORT).show();
                Intent outraactivity = new Intent(MinhasListas.this, EditarLista.class);
                startActivity(outraactivity);
            }
        });
        botaocriarlistaminhaslistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), getString(R.string.carregou_para_criar_lista), Toast.LENGTH_SHORT).show();
                Intent outraactivity = new Intent(MinhasListas.this, CriarLista.class);
                startActivity(outraactivity);
            }
        });

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        CursorLoader cursorLoader = new CursorLoader(this, ListasContentProvider.ENDERECO_LISTAS, BdTableListas.TODAS_COLUNAS, null, null, BdTableListas.CAMPO_NOME_LISTA
        );

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        Toast.makeText(this, ("Contem" + ID_CURSO_LOADER_LISTAS + "listas"), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
