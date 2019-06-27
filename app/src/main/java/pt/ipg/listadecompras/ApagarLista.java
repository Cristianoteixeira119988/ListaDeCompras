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

public class ApagarLista extends AppCompatActivity {

    private Uri enderecoListaApagar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            TextView textViewNomeLista = (TextView) findViewById(R.id.textViewNomeDaListaAApagar);
            TextView textViewData = (TextView) findViewById(R.id.textViewData);


            Intent intent = getIntent();
            long idLista = intent.getLongExtra(MinhasListas.ID_LISTA, -1);
            if (idLista == -1) {
                Toast.makeText(this, getString(R.string.erro), Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            enderecoListaApagar = Uri.withAppendedPath(ListasContentProvider.ENDERECO_LISTAS, String.valueOf(idLista));

            Cursor cursor = getContentResolver().query(enderecoListaApagar, BdTableListas.TODAS_COLUNAS, null, null, null);

            if (!cursor.moveToNext()) {
                Toast.makeText(this, getString(R.string.erro), Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            Listas listas = Listas.fromCursor(cursor);

            textViewNomeLista.setText(listas.getNomelista());
            textViewData.setText(listas.getDatacriacao());

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_eliminar, menu);
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
            } else if (id == R.id.action_eliminar) {
                eliminar();
                return true;
            } else if (id == R.id.action_cancelar) {
                finish();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        private void eliminar() {
            // todo: perguntar ao utilizador se tem a certeza
            int listasApagadas = getContentResolver().delete(enderecoListaApagar, null, null);

            if (listasApagadas == 1) {
                Toast.makeText(this, getString(R.string.lista_apagada), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, getString(R.string.erro), Toast.LENGTH_LONG).show();
            }
    }
}
