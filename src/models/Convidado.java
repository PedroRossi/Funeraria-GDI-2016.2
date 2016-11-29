package models;

/**
 * Created by pedro on 28/11/16.
 */
public class Convidado {

    private int id;
    private String primeiroNome;
    private String nomeFamilia;

    public Convidado(int id, String primeiroNome, String nomeFamilia) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.nomeFamilia = nomeFamilia;
    }

    public int getId() {
        return id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getNomeFamilia() {
        return nomeFamilia;
    }

    public void setNomeFamilia(String nomeFamilia) {
        this.nomeFamilia = nomeFamilia;
    }
}
