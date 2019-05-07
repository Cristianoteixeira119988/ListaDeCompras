package pt.ipg.listadecompras;

import android.content.ContentValues;
import android.database.Cursor;

public class Categoria {
    private long id;
    private String nomecategoria;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomecategoria() {
        return nomecategoria;
    }

    public void setNomecategoria(String nomecategoria) {
        this.nomecategoria = nomecategoria;
    }
    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(BdTableCategoria.CAMPO_CATEGORIA, nomecategoria);

        return valores;
    }
    public static Categoria fromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(BdTableCategoria._ID)
        );

        String nomecaterogia = cursor.getString(
                cursor.getColumnIndex(BdTableCategoria.CAMPO_CATEGORIA)
        );

        Categoria categoria = new Categoria();

        categoria.setId(id);
        categoria.setNomecategoria(nomecaterogia);

        return categoria;
    }
}
