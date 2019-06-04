package pt.ipg.listadecompras;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

public class Produtos {
    private long id;
    private String nomeproduto;
    private long categoria;
    private int quantidade;
    private String dataqueacabou;
    private Long nomelista;

    public long getNomelista() {
        return nomelista;
    }

    public void setNomelista(long nomelista) {
        this.nomelista= nomelista;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeproduto() {
        return nomeproduto;
    }

    public void setNomeproduto(String nomeproduto) {
        this.nomeproduto = nomeproduto;
    }

    public long getCategoria() {
        return categoria;
    }

    public void setCategoria(long categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataqueacabou() {
        return dataqueacabou;
    }

    public void setDataqueacabou (String dataqueacabou) {
        this.dataqueacabou = dataqueacabou;
    }



    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(BdTableProdutos.CAMPO_NOME_PRODUTO, nomeproduto);
        valores.put(BdTableProdutos.CAMPO_QUANTIDADE, quantidade);
        valores.put(BdTableProdutos.CAMPO_CATEGORIA, categoria);
        valores.put(BdTableProdutos.CAMPO_DATA_FALTOU,  dataqueacabou);
        valores.put(BdTableProdutos.CAMPO_NOME_LISTA,  nomelista);
        return valores;
    }
    public static Produtos fromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(BdTableProdutos._ID)
        );

        String nomeproduto = cursor.getString(
                cursor.getColumnIndex(BdTableProdutos.CAMPO_NOME_PRODUTO)
        );

        int quantidade = cursor.getInt(
                cursor.getColumnIndex(BdTableProdutos.CAMPO_QUANTIDADE)
        );

        long categoria = cursor.getLong(
                cursor.getColumnIndex(BdTableProdutos.CAMPO_CATEGORIA)
        );
        String dataquefaltou = cursor.getString(
                cursor.getColumnIndex(BdTableProdutos.CAMPO_DATA_FALTOU)
        );
        long nomelista= cursor.getLong(
                cursor.getColumnIndex(BdTableProdutos.CAMPO_NOME_LISTA)
        );

        Produtos produtos = new Produtos();

        produtos.setId(id);
        produtos.setNomeproduto(nomeproduto);
        produtos.setQuantidade(quantidade);
        produtos.setCategoria(categoria);
        produtos.setDataqueacabou(dataquefaltou);
        produtos.setNomelista(nomelista);

        return produtos;
    }
}
