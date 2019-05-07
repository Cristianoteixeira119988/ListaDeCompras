package pt.ipg.listadecompras;

import java.util.Date;

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

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    private String nomelista;
    private Date datacriacao;
}
