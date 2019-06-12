package pt.ipg.listadecompras;

import android.content.ContentValues;
import android.database.Cursor;

public class Listas {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomelista() {
        return nomelista;
    }

    public void setNomelista(String nomelista) {
        this.nomelista = nomelista;
    }

    public String getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(String datacriacao) {
        this.datacriacao = datacriacao;
    }

    private String nomelista;
    private String datacriacao;

    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(BdTableListas.CAMPO_NOME_LISTA, nomelista);
        valores.put(BdTableListas.CAMPO_DATA_CRIACAO, datacriacao);


        return valores;
    }

    public static Listas fromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(BdTableListas._ID)
        );

        String nomelista = cursor.getString(
                cursor.getColumnIndex(BdTableListas.CAMPO_NOME_LISTA)
        );

        String datacriacao = cursor.getString(
                cursor.getColumnIndex(BdTableListas.CAMPO_DATA_CRIACAO)
        );



        Listas listas = new Listas();

        listas.setId(id);
        listas.setNomelista(nomelista);
        listas.setDatacriacao(datacriacao);


        return listas;
    }
}
