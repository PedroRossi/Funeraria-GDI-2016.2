package models;

import java.util.Date;

/**
 * Created by pedro on 28/11/16.
 */
public abstract class Pessoa {

    private String cpf;
    private Nome nome;
    private Date nascimento;
    private String rg;
    private Telefone telefones;

    public Pessoa(String cpf, Nome nome, Date nascimento, String rg, Telefone telefones) {
        this.cpf = cpf;
        this.nome = nome;
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

    public Nome getNome() {
        return nome;
    }

    public void setNome(Nome nome) {
        this.nome = nome;
    }

    public Date getnascimento() {
        return nascimento;
    }

    public void setnascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Telefone getTelefones() {
        return telefones;
    }

    public void setTelefones(Telefone telefones) {
        this.telefones = telefones;
    }
}
