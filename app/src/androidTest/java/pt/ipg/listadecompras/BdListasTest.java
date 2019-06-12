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
public class BdListasTest {
    @Before
    public void apagaBaseDados() {
        getAppContext().deleteDatabase(SqliteOpenHelper.NOME_BASE_DADOS);
    }

    @Test
    public void criaBdListas() {
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

        //Teste categorias

        SqliteOpenHelper openHelper1 = new SqliteOpenHelper(getAppContext());
        SQLiteDatabase db = openHelper1.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(db);

        // Teste read categorias (cRud)
        Cursor cursorCategorias = getCategorias(tabelaCategorias);
        assertEquals(0, cursorCategorias.getCount());

        // Teste create/read categorias (CRud)
        String nome = "Mercearia";
        long idMercearia = criaCategoria(tabelaCategorias, nome);

        cursorCategorias = getCategorias(tabelaCategorias);
        assertEquals(1, cursorCategorias.getCount());

        Categoria categoria = getCategoriaComID(cursorCategorias, idMercearia);

        assertEquals(nome, categoria.getDescricao());

        nome = "Bricolage";
        long idBricolage = criaCategoria(tabelaCategorias, nome);

        cursorCategorias = getCategorias(tabelaCategorias);
        assertEquals(2, cursorCategorias.getCount());

        categoria = getCategoriaComID(cursorCategorias, idBricolage);

        assertEquals(nome, categoria.getDescricao());

        // Teste Update/Read categorias (cRUd)
        nome = "Vestuario / Bricolage";
        categoria.setDescricao(nome);

        int registosAlterados = tabelaCategorias.update(categoria.getContentValues(), BdTableCategorias._ID + "=?", new String[]{String.valueOf(idBricolage)});

        assertEquals(1, registosAlterados);

        cursorCategorias = getCategorias(tabelaCategorias);
        categoria = getCategoriaComID(cursorCategorias, idBricolage);

        assertEquals(nome, categoria.getDescricao());

        // Teste Create/Delete/Read categorias (CRuD)
        long id = criaCategoria(tabelaCategorias, "TESTE");
        cursorCategorias = getCategorias(tabelaCategorias);
        assertEquals(3, cursorCategorias.getCount());

        tabelaCategorias.delete(BdTableCategorias._ID + "=?", new String[]{String.valueOf(id)});
        cursorCategorias = getCategorias(tabelaCategorias);
        assertEquals(2, cursorCategorias.getCount());

        getCategoriaComID(cursorCategorias, idMercearia);
        getCategoriaComID(cursorCategorias, idBricolage);

        BdTableListas tabelaListas = new BdTableListas(db);

        //Teste produtos

        BdTableProdutos tableProdutos = new BdTableProdutos(db);

        Cursor cursorProdutos = getProduto(tableProdutos);
        assertEquals(0, cursorProdutos.getCount());

        // Teste create / read produtos (cRud)

        String nomeproduto ="Massa";
        String data = "12/05/2019";
        int quantidade = 1;
        String nomedalista = "Lista9";
        long idITS = criaProduto(tableProdutos, nomeproduto,(int) idBricolage, quantidade, data, nomedalista);

        cursorProdutos = getProduto(tableProdutos);
        assertEquals(1, cursorProdutos.getCount());

        Produtos produtos = getProdutoComID(cursorProdutos, idITS);

        assertEquals(nomeproduto, produtos.getNomeproduto());
        assertEquals(idBricolage, produtos.getCategoria());
        assertEquals(data, produtos.getDataqueacabou());
        assertEquals(quantidade,produtos.getQuantidade());
        assertEquals(nomedalista,produtos.getNomelista());

        nomeproduto ="Batatas";
        quantidade= 8;
        data = "15/05/2019";
        long idJW = criaProduto(tableProdutos, nomeproduto, (int) idMercearia, quantidade, data, nomedalista);

        cursorProdutos = getProduto(tableProdutos);
        assertEquals(2, cursorProdutos.getCount());

        produtos = getProdutoComID(cursorProdutos, idJW);

        assertEquals(nomeproduto, produtos.getNomeproduto());
        assertEquals(idMercearia, produtos.getCategoria());
        assertEquals(data, produtos.getDataqueacabou());
        assertEquals(quantidade,produtos.getQuantidade());
        assertEquals(nomedalista,produtos.getNomelista());

        //Update produtos

        nomeproduto ="cenouras";
        quantidade= 4;
        data = "18/05/2019";
        nomedalista= "lista4";

        produtos.setNomeproduto(nomeproduto);
        produtos.setCategoria(idBricolage);
        produtos.setQuantidade(quantidade);
        produtos.setDataqueacabou(data);
        produtos.setNomelista(Long.valueOf(nomedalista));

        int registosAlteradosProdutos = tableProdutos.update(produtos.getContentValues(), BdTableProdutos._ID + "=?", new String[]{String.valueOf(idITS)});

        assertEquals(1, registosAlteradosProdutos);

        cursorProdutos = getProduto(tableProdutos);
        produtos = getProdutoComID(cursorProdutos, idITS);

        assertEquals(nomeproduto, produtos.getNomeproduto());
        assertEquals(idBricolage, produtos.getCategoria());
        assertEquals(data, produtos.getDataqueacabou());
        assertEquals(quantidade,produtos.getQuantidade());
        assertEquals(nomedalista,produtos.getNomelista());

        //Crud produtos

        id = criaProduto(tableProdutos, "Pão", 3, 5, "13-04-2019", "Lista6");
        cursorProdutos = getProduto(tableProdutos);
        assertEquals(3, cursorProdutos.getCount());

        tableProdutos.delete(BdTableProdutos._ID + "=?", new String[]{String.valueOf(id)});
        cursorProdutos = getProduto(tableProdutos);
        assertEquals(2, cursorProdutos.getCount());

        getProdutoComID(cursorProdutos, idITS);
        getProdutoComID(cursorProdutos, idJW);

        //Teste listas

        BdTableListas tableListas = new BdTableListas(db);

        Cursor cursorListas = getListas(tableListas);
        assertEquals(0, cursorListas.getCount());

        //Teste create / read listas

        nome= "Lista8";
        data="19-05-2019";
        long idLista8 = criaLista(tableListas, nome, data);

        cursorListas = getListas(tableListas);
        assertEquals(1, cursorListas.getCount());

        Listas listas = getListaComID(cursorListas, idLista8);

        assertEquals(nome, listas.getNomelista());
        assertEquals(data, listas.getDatacriacao());

        nome= "Lista9";
        data="01-05-2019";
        long idLista9 = criaLista(tableListas, nome, data);

        cursorListas = getListas(tableListas);
        assertEquals(2, cursorListas.getCount());

        listas = getListaComID(cursorListas, idLista9);

        assertEquals(nome, listas.getNomelista());
        assertEquals(data, listas.getDatacriacao());

        //Update listas

        nome = "Lista10";
        data = "02-05-2019";

        listas.setNomelista(nome);
        listas.setDatacriacao(data);

        int registosAlteradosListas = tableListas.update(listas.getContentValues(), BdTableListas._ID + "=?", new String[]{String.valueOf(idLista9)});

        cursorListas = getListas(tableListas);
        listas = getListaComID(cursorListas, idITS);

        assertEquals(nome, listas.getNomelista());
        assertEquals(data, listas.getDatacriacao());


        //Crud listas

        long id2 = criaLista(tableListas,"Lista11", "03-05-2019");
        cursorListas = getListas(tableListas);
        assertEquals(3, cursorListas.getCount());

        tableListas.delete(BdTableListas._ID + "=?", new String[]{String.valueOf(id)});
        cursorListas = getListas(tableListas);
        assertEquals(2, cursorListas.getCount());

        getListaComID(cursorListas, idLista8);
        getListaComID(cursorListas, idLista9);




    }
    //Tabela listas

    private long criaLista(BdTableListas tableListas, String nome,  String data) {

        Listas listas = new Listas();

        listas.setNomelista(nome);
        listas.setDatacriacao(data);


        long id = tableListas.insert(listas.getContentValues());
        assertNotEquals(-1, id);

        return id;
    }

    private Cursor getListas(BdTableListas tableListas) {
        return tableListas.query(BdTableListas.TODAS_COLUNAS, null, null, null, null, null);
    }

    private Listas getListaComID(Cursor cursor, long id) {
        Listas listas = null;

        while (cursor.moveToNext()) {
            listas= Listas.fromCursor(cursor);

            if (listas.getId() == id) {
                break;
            }
        }

        assertNotNull(listas);

        return listas;
    }

    //tabela produtos

    private long criaProduto(BdTableProdutos tableProdutos, String nome, int categoriaa, int quantidade, String data, String nomedalista) {

        Produtos produtos = new Produtos();

        produtos.setNomeproduto(nome);
        produtos.setCategoria(categoriaa);
        produtos.setQuantidade(quantidade);
        produtos.setDataqueacabou(data);
        produtos.setNomelista(Long.valueOf(nomedalista));


        long id = tableProdutos.insert(produtos.getContentValues());
        assertNotEquals(-1, id);

        return id;
    }

    private Cursor getProduto(BdTableProdutos tableProdutos) {
        return tableProdutos.query(BdTableProdutos.TODAS_COLUNAS, null, null, null, null, null);
    }

    private Produtos getProdutoComID(Cursor cursor, long id) {
        Produtos produtos = null;

        while (cursor.moveToNext()) {
            produtos= produtos.fromCursor(cursor);

            if (produtos.getId() == id) {
                break;
            }
        }

        assertNotNull(produtos);

        return produtos;
    }

    //TABELA CATEGORIAS

    private long criaCategoria(BdTableCategorias tabelaCategorias, String nome) {
        Categoria categoria = new Categoria();
        categoria.setDescricao(nome);

        long id = tabelaCategorias.insert(categoria.getContentValues());
        assertNotEquals(-1, id);

        return id;
    }

    private Cursor getCategorias(BdTableCategorias tabelaCategorias) {
        return tabelaCategorias.query(BdTableCategorias.TODAS_COLUNAS, null, null, null, null, null);
    }

    private Categoria getCategoriaComID(Cursor cursor, long id) {
        Categoria categoria = null;

        while (cursor.moveToNext()) {
            categoria = Categoria.fromCursor(cursor);

            if (categoria.getId() == id) {
                break;
            }
        }

        assertNotNull(categoria);

        return categoria;
    }

}