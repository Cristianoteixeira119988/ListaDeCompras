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
    public static final String CAMPO_DATA_FALTOU = "DataQueFaltou";
    private SQLiteDatabase db;

    public BdTableProdutos(SQLiteDatabase db) {
        this.db = db;
    }

    public void cria() {
        db.execSQL(
                "CREATE TABLE " + NOME_TABELA + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        CAMPO_NOME_PRODUTO + " TEXT NOT NULL," +
                        CAMPO_QUANTIDADE + " INTEGER NOT NULL," +
                        CAMPO_CATEGORIA + " TEXT NOT NULL," +
                        CAMPO_DATA_FALTOU + " DATE NOT NULL," +
                        "FOREIGN KEY (" + CAMPO_CATEGORIA + ") REFERENCES " + BdTableCategoria.NOME_TABELA + "(" + BdTableCategoria._ID + ")" +
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
