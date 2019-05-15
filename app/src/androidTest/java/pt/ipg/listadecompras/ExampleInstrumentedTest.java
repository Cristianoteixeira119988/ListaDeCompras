package pt.ipg.listadecompras;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Before
    public void apagaBaseDados() {
        getAppContext().deleteDatabase(SqliteOpenHelper.NOME_BASE_DADOS);
    }

    @Test
    public void criaBdLivros() {
        Context appContext = getAppContext();

        SqliteOpenHelper openHelper = new SqliteOpenHelper(appContext);

        SQLiteDatabase db = openHelper.getReadableDatabase();

        assertTrue(db.isOpen());
    }

    private Context getAppContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testCRUD() {
        SqliteOpenHelper openHelper = new SqliteOpenHelper(getAppContext());
        SQLiteDatabase db = openHelper.getWritableDatabase();



        //TESTE CATEGORIAS

        BdTableCategoria tableCategorias = new BdTableCategoria(db);

        Categoria categoria = new Categoria();
        categoria.setNomecategoria("Bricolage");
        long idCategoria = tableCategorias.insert(categoria.getContentValues());
        assertNotEquals(-1, idCategoria);

        //TESTE PRODUTOS

        BdTableProdutos tableProdutos = new BdTableProdutos(db);

        Produtos produtos = new Produtos();
        produtos.setNomeproduto("Arroz");
        produtos.setCategoria("Mercearia");
        produtos.setQuantidade(5);
        produtos.setDataqueacabou("13-05-2019");
        produtos.setNomelista("Lista1");
        long idProdutos = tableProdutos.insert(produtos.getContentValues());
        assertNotEquals(-1, idProdutos);

        //TESTE LISTAS

        BdTableListas tabelaListas = new BdTableListas(db);

        Listas listas = new Listas();
        listas.setNomelista("Lista1");
        listas.setDatacriacao("13-05-2019");
        long idLista = tabelaListas.insert(listas.getContentValues());
        assertNotEquals(-1, idLista);

    }
}