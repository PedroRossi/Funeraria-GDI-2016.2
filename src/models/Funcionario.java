package models;

import java.sql.Date;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public class Funcionario extends Pessoa {

    private double salario;
    private String funcao;
    private String cpfSupervisor;

    public Funcionario(String cpf, String primeiroNome, String nomeFamilia, Date nascimento, String rg, List<Telefone> telefones, double salario, String funcao, String cpfSupervisor) {
        super(cpf, primeiroNome, nomeFamilia, nascimento, rg, telefones);
        this.salario = salario;
        this.funcao = funcao;
        this.cpfSupervisor = cpfSupervisor;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getCpfSupervisor() {
        return cpfSupervisor;
    }

    public void setCpfSupervisor(String cpfSupervisor) {
        this.cpfSupervisor = cpfSupervisor;
    }
}
