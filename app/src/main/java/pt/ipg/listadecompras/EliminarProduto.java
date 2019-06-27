package pt.ipg.listadecompras;

import android.arch.lifecycle.ViewModelProvider;
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

public class EliminarProduto extends AppCompatActivity {

    private Uri enderecoProdutoApagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_produto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            TextView textViewNomeDoProduto = (TextView) findViewById(R.id.textViewNomeDoProdutoEl);
            TextView textViewCategoria = (TextView) findViewById(R.id.textViewCategoriaProdutoEl);
            TextView textViewQuantidade = (TextView) findViewById(R.id.textViewQuantidadeEl);
            TextView textViewData = (TextView) findViewById(R.id.textViewDataQueFaltouEl);
            TextView textViewNomeDaLista = (TextView) findViewById(R.id.textViewNomeDaListaEL);

            Intent intent = getIntent();
            long idProduto = intent.getLongExtra(TodosProduto.ID_PRODUTO, -1);
            if (idProduto == -1) {
                Toast.makeText(this, getString(R.string.erro), Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            enderecoProdutoApagar = Uri.withAppendedPath(ListasContentProvider.ENDERECO_PRODUTOS, String.valueOf(idProduto));

            Cursor cursor = getContentResolver().query(enderecoProdutoApagar, BdTableProdutos.TODAS_COLUNAS, null, null, null);

            if (!cursor.moveToNext()) {
                Toast.makeText(this, getString(R.string.erro), Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            Produtos produtos = Produtos.fromCursor(cursor);

            textViewNomeDoProduto.setText(produtos.getNomeproduto());
            textViewCategoria.setText((int) produtos.getCategoria());
            textViewQuantidade.setText(produtos.getQuantidade());
            textViewData.setText(produtos.getDataqueacabou());
            textViewNomeDaLista.setText((int) produtos.getNomelista());
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
            int ProdutosApagados = getContentResolver().delete(enderecoProdutoApagar, null, null);

            if (ProdutosApagados == 1) {
                Toast.makeText(this, getString(R.string.produto_eliminado_toast), Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, getString(R.string.erro), Toast.LENGTH_LONG).show();
            }
        }
    }


