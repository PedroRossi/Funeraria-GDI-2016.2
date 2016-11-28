package models;

import java.util.Date;

/**
 * Created by pedro on 28/11/16.
 */
public class Cliente extends Pessoa {

    public Cliente(String cpf, Nome nome, Date nascimento, String rg, Telefone telefones) {
        super(cpf, nome, nascimento, rg, telefones);
    }

}
