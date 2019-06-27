package pt.ipg.listadecompras;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EliminarCategoria extends AppCompatActivity {

    private Uri enderecoCategoriaApagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            TextView textViewCategoria = (TextView) findViewById(R.id.textViewEleminarCategoria);



            Intent intent = getIntent();
            long idCategoria = intent.getLongExtra(Categorias.ID_CATEGORIA, -1);
            if (idCategoria == -1) {
                Toast.makeText(this, getString(R.string.nao_possivel_eleminar_categoria), Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            enderecoCategoriaApagar = Uri.withAppendedPath(ListasContentProvider.ENDERECO_CATEGORIAS, String.valueOf(idCategoria));

            Cursor cursor = getContentResolver().query(enderecoCategoriaApagar, BdTableCategorias.TODAS_COLUNAS, null, null, null);

            if (!cursor.moveToNext()) {
                Toast.makeText(this, getString(R.string.nao_possivel_eleminar_categoria), Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            Categoria categoria = Categoria.fromCursor(cursor);

            textViewCategoria.setText(categoria.getDescricao());

        }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_eliminar, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            } else if (id == R.id.action_eliminar) {
                eliminar();
                return true;
            } else if (id == R.id.action_cancelar) {
                finish();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        private void eliminar () {
            // todo: perguntar ao utilizador se tem a certeza
            int CateogriasApagadas = getContentResolver().delete(enderecoCategoriaApagar, null, null);

            if (CateogriasApagadas == 1) {
                Toast.makeText(this, getString(R.string.categoria_eleminada), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, getString(R.string.nao_possivel_eleminar_categoria), Toast.LENGTH_LONG).show();
            }
        }
    }

