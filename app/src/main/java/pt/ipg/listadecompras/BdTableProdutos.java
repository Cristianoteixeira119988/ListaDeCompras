package pt.ipg.listadecompras;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTableProdutos implements BaseColumns {

    public static final String NOME_TABELA = "Produtos";
    public static final String CAMPO_NOME_PRODUTO = "NomeProduto";
    public static final String CAMPO_QUANTIDADE = "Quantidade";
    public static final String CAMPO_CATEGORIA = "Categoria";
    public static final String CAMPO_DATA_FALTOU = "Data";
    public static final String CAMPO_NOME_LISTA = "Lista";
    private SQLiteDatabase db;
    public static final String[] TODAS_COLUNAS = new String[] { _ID, CAMPO_NOME_PRODUTO, CAMPO_QUANTIDADE, CAMPO_CATEGORIA, CAMPO_DATA_FALTOU, CAMPO_NOME_LISTA };


    public BdTableProdutos(SQLiteDatabase db) {
        this.db = db;
    }

    public void cria() {
        db.execSQL(
                "CREATE TABLE " + NOME_TABELA + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CAMPO_NOME_PRODUTO + " TEXT NOT NULL," +
                        CAMPO_CATEGORIA + " TEXT NOT NULL," +
                        CAMPO_QUANTIDADE + " INTEGER NOT NULL," +
                        CAMPO_DATA_FALTOU + " DATE NOT NULL," +
                        CAMPO_NOME_LISTA + " TEXT NOT NULL," +
                        "FOREIGN KEY (" + CAMPO_CATEGORIA + ") REFERENCES " + BdTableCategorias.NOME_TABELA + "(" + BdTableCategorias._ID + ")" +
                        ")"
        );
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public long insert(ContentValues values) {
        return db.insert(NOME_TABELA, null, values);
    }

    public int update(ContentValues values, String whereClause, String [] whereArgs) {
        return db.update(NOME_TABELA, values, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(NOME_TABELA, whereClause, whereArgs);
    }
}
