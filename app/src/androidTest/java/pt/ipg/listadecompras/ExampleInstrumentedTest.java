package pt.ipg.listadecompras;

import android.content.Context;
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
        // Context of the app under test.
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

        BdTableCategoria tabelaCategorias = new BdTableCategoria(db);

        Categoria categoria = new Categoria();
        categoria.setNomecategoria("Mercearia");
        long idMercearia = tabelaCategorias.insert(categoria.getContentValues());
        assertNotEquals(-1, idMercearia);

        categoria = new Categoria();
        categoria.setNomecategoria("Bricolage");
        long idBricolage = tabelaCategorias.insert(categoria.getContentValues());
        assertNotEquals(-1, idBricolage);
    }
}