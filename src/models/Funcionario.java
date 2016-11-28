package models;

import java.util.Date;

/**
 * Created by pedro on 28/11/16.
 */
public class Funcionario extends Pessoa {

    private double salario;
    private String funcao;
    private Funcionario supervisor;

    public Funcionario(String cpf, Nome nome, Date nascimento, String rg, Telefone telefones, double salario, String funcao, Funcionario supervisor) {
        super(cpf, nome, nascimento, rg, telefones);
        this.salario = salario;
        this.funcao = funcao;
        this.supervisor = supervisor;
    }
}
