package pt.ipg.listadecompras;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.Date;

public class Produtos {
    private long id;
    private String nomeproduto;
    private String categoria;
    private int quantidade;

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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataqueacabou() {
        return dataqueacabou;
    }

    public void setDataqueacabou(Date dataqueacabou) {
        this.dataqueacabou = dataqueacabou;
    }

    private Date dataqueacabou;

    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();

        valores.put(BdTableProdutos.CAMPO_NOME_PRODUTO, nomeproduto);
        valores.put(BdTableProdutos.CAMPO_QUANTIDADE, quantidade);
        valores.put(BdTableProdutos.CAMPO_CATEGORIA, categoria);

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

        String categoria = cursor.getString(
                cursor.getColumnIndex(BdTableProdutos.CAMPO_CATEGORIA)
        );

        Produtos produtos = new Produtos();

        produtos.setId(id);
        produtos.setNomeproduto(nomeproduto);
        produtos.setQuantidade(quantidade);
        produtos.setCategoria(categoria);

        return produtos;
    }
}
