package pt.ipg.listadecompras;

import android.content.ContentValues;
import android.database.Cursor;

public class Categoria {
    private long id;
    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(BdTableCategorias.CAMPO_DESCRICAO, descricao);

        return valores;
    }

    public static Categoria fromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(BdTableCategorias._ID)
        );

        String descricao = cursor.getString(
                cursor.getColumnIndex(BdTableCategorias.CAMPO_DESCRICAO)
        );

        Categoria categoria = new Categoria();

        categoria.setId(id);
        categoria.setDescricao(descricao);

        return categoria;
    }
}