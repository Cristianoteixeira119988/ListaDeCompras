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

        // Crud categorias (CRuD)
        long id = criaCategoria(tabelaCategorias, "TESTE");
        cursorCategorias = getCategorias(tabelaCategorias);
        assertEquals(3, cursorCategorias.getCount());

        tabelaCategorias.delete(BdTableCategorias._ID + "=?", new String[]{String.valueOf(id)});
        cursorCategorias = getCategorias(tabelaCategorias);
        assertEquals(2, cursorCategorias.getCount());

        getCategoriaComID(cursorCategorias, idMercearia);
        getCategoriaComID(cursorCategorias, idBricolage);



        //Teste listas

        BdTableListas tableListas = new BdTableListas(db);

        Cursor cursorListas = getListas(tableListas);
        assertEquals(0, cursorListas.getCount());

        //Teste create / read listas

        String nome1= "Lista8";
        String data="19-05-2019";
        long idLista8 = criaLista(tableListas, nome1, data);

        cursorListas = getListas(tableListas);
        assertEquals(1, cursorListas.getCount());

        Listas listas = getListaComID(cursorListas, idLista8);

        assertEquals(nome1, listas.getNomelista());
        assertEquals(data, listas.getDatacriacao());

        nome1= "Lista9";
        data="01-05-2019";
        long idLista9 = criaLista(tableListas, nome1, data);

        cursorListas = getListas(tableListas);
        assertEquals(2, cursorListas.getCount());

        listas = getListaComID(cursorListas, idLista9);

        assertEquals(nome1, listas.getNomelista());
        assertEquals(data, listas.getDatacriacao());

        //Update listas

        nome1 = "Lista10";
        data = "02-05-2019";

        listas.setNomelista(nome1);
        listas.setDatacriacao(data);

        int registosAlteradosListas = tableListas.update(listas.getContentValues(), BdTableListas._ID + "=?", new String[]{String.valueOf(idLista8)});
        assertEquals(1, registosAlteradosListas);

        cursorListas = getListas(tableListas);
        listas = getListaComID(cursorListas, idLista8);

        assertEquals(nome1, listas.getNomelista());
        assertEquals(data, listas.getDatacriacao());


        //Crud listas

        long id2 = criaLista(tableListas,"Lista11", "03-05-2019");
        cursorListas = getListas(tableListas);
        assertEquals(3, cursorListas.getCount());

        tableListas.delete(BdTableListas._ID + "=?", new String[]{String.valueOf(id2)});
        cursorListas = getListas(tableListas);
        assertEquals(2, cursorListas.getCount());

        getListaComID(cursorListas, idLista8);
        getListaComID(cursorListas, idLista9);

        //Teste produtos

        BdTableProdutos tableProdutos = new BdTableProdutos(db);

        Cursor cursorProdutos = getProduto(tableProdutos);
        assertEquals(0, cursorProdutos.getCount());

        // Teste create / read produtos (cRud)

        String nomeproduto ="Massa";
        String data2 = "12/05/2019";
        int quantidade = 1;

        long idProduto1 = criaProduto(tableProdutos, nomeproduto,(int) idBricolage, quantidade, data2, idLista8);

        cursorProdutos = getProduto(tableProdutos);
        assertEquals(1, cursorProdutos.getCount());

        Produtos produtos = getProdutoComID(cursorProdutos, idProduto1);

        assertEquals(nomeproduto, produtos.getNomeproduto());
        assertEquals(idBricolage, produtos.getCategoria());
        assertEquals(data2, produtos.getDataqueacabou());
        assertEquals(quantidade,produtos.getQuantidade());
        assertEquals(idLista8,produtos.getNomelista());

        nomeproduto ="Batatas";
        quantidade= 8;
        data2 = "15/05/2019";
        long idProduto2 = criaProduto(tableProdutos, nomeproduto, (int) idMercearia, quantidade, data2, idLista9);

        cursorProdutos = getProduto(tableProdutos);
        assertEquals(2, cursorProdutos.getCount());

        produtos = getProdutoComID(cursorProdutos, idProduto2
        );

        assertEquals(nomeproduto, produtos.getNomeproduto());
        assertEquals(idMercearia, produtos.getCategoria());
        assertEquals(data2, produtos.getDataqueacabou());
        assertEquals(quantidade,produtos.getQuantidade());
        assertEquals(idLista9,produtos.getNomelista());

        //Update produtos

        nomeproduto ="cenouras";
        quantidade= 4;
        data2 = "18/05/2019";

        produtos.setNomeproduto(nomeproduto);
        produtos.setCategoria(idBricolage);
        produtos.setQuantidade(quantidade);
        produtos.setDataqueacabou(data2);
        produtos.setNomelista(idLista8);

        int registosAlteradosProdutos = tableProdutos.update(produtos.getContentValues(), BdTableProdutos._ID + "=?", new String[]{String.valueOf(idProduto1)});

        assertEquals(1, registosAlteradosProdutos);

        cursorProdutos = getProduto(tableProdutos);
        produtos = getProdutoComID(cursorProdutos, idProduto1);

        assertEquals(nomeproduto, produtos.getNomeproduto());
        assertEquals(idBricolage, produtos.getCategoria());
        assertEquals(data2, produtos.getDataqueacabou());
        assertEquals(quantidade,produtos.getQuantidade());
        assertEquals(idLista8,produtos.getNomelista());

        //Crud produtos

        long id3 = (long) criaProduto(tableProdutos, "Pão",idBricolage, 5, "13-04-2019", idLista8);
        cursorProdutos = getProduto(tableProdutos);
        assertEquals(3, cursorProdutos.getCount());

        tableProdutos.delete(BdTableProdutos._ID + "=?", new String[]{String.valueOf(id3)});
        cursorProdutos = getProduto(tableProdutos);
        assertEquals(2, cursorProdutos.getCount());

        getProdutoComID(cursorProdutos, idProduto1);
        getProdutoComID(cursorProdutos, idProduto2);



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

    private long criaProduto(BdTableProdutos tableProdutos, String nome, long categoria, int quantidade, String data, long nomedalista) {

        Produtos produtos = new Produtos();

        produtos.setNomeproduto(nome);
        produtos.setCategoria(categoria);
        produtos.setQuantidade(quantidade);
        produtos.setDataqueacabou(data);
        produtos.setNomelista(nomedalista);


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