package models;

import java.sql.Date;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public class Cliente extends Pessoa {

    public Cliente(String cpf, String primeiroNome, String nomeFamilia, Date nascimento, String rg, List<Telefone> telefones) {
        super(cpf, primeiroNome, nomeFamilia, nascimento, rg, telefones);
    }

}
