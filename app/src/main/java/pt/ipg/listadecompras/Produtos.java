package pt.ipg.listadecompras;

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
}
