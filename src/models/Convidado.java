package models;

/**
 * Created by pedro on 28/11/16.
 */
public class Convidado {

    private int id;
    private Nome nome;

    public Convidado(int id, Nome nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public Nome getNome() {
        return nome;
    }

    public void setNome(Nome nome) {
        this.nome = nome;
    }
}
