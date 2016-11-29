package models;

import java.sql.Date;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public class Motorista extends Pessoa {

    private String cnh;

    public Motorista(String cpf, String primeiroNome, String nomeFamilia, Date nascimento, String rg, List<Telefone> telefones, String cnh) {
        super(cpf, primeiroNome, nomeFamilia, nascimento, rg, telefones);
        this.cnh = cnh;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
}
