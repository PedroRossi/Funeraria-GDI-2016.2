package models;

import java.sql.Date;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public abstract class Pessoa {

    private String cpf;
    private String primeiroNome;
    private String nomeFamilia;
    private Date nascimento;
    private String rg;
    private List<Telefone> telefones;

    public Pessoa(String cpf, String primeiroNome, String nomeFamilia, Date nascimento, String rg, List<Telefone> telefones) {
        this.cpf = cpf;
        this.primeiroNome = primeiroNome;
        this.nomeFamilia = nomeFamilia;
        this.nascimento = nascimento;
        this.rg = rg;
        this.telefones = telefones;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
}
