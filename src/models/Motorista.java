package models;

import java.util.Date;

/**
 * Created by pedro on 28/11/16.
 */
public class Motorista extends Pessoa {

    private String cnh;

    public Motorista(String cpf, Nome nome, Date nascimento, String rg, Telefone telefones, String cnh) {
        super(cpf, nome, nascimento, rg, telefones);
        this.cnh = cnh;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
}
