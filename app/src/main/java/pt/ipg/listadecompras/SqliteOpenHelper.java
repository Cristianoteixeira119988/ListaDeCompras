package pt.ipg.listadecompras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class SqliteOpenHelper extends SQLiteOpenHelper {
    public static final String NOME_BASE_DADOS = "Listadecompras.db";
    private static final int VERSAO_BASE_DADOS = 1;

    public SqliteOpenHelper(@Nullable Context context) {
        super(context, NOME_BASE_DADOS, null, VERSAO_BASE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new BdTableCategoria(db).cria();
        new BdTableListas(db).cria();
        new BdTableProdutos(db).cria();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
