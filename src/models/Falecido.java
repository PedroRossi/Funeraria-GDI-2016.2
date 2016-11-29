package models;

import java.sql.Date;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public class Falecido extends Pessoa {

    private Date obito;
    private double largura;
    private double altura;
    private Carro transporte;
    private Motorista motorista;
    private Date horaTransporte;

    public Falecido(String cpf, String primeiroNome, String nomeFamilia, Date nascimento, String rg, List<Telefone> telefones, Date obito, double largura, double altura, Carro transporte, Motorista motorista, Date horaTransporte) {
        super(cpf, primeiroNome, nomeFamilia, nascimento, rg, telefones);
        this.obito = obito;
        this.largura = largura;
        this.altura = altura;
        this.transporte = transporte;
        this.motorista = motorista;
        this.horaTransporte = horaTransporte;
    }

    public Date getObito() {
        return obito;
    }

    public void setObito(Date obito) {
        this.obito = obito;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Carro getTransporte() {
        return transporte;
    }

    public void setTransporte(Carro transporte) {
        this.transporte = transporte;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Date getHoraTransporte() {
        return horaTransporte;
    }

    public void setHoraTransporte(Date horaTransporte) {
        this.horaTransporte = horaTransporte;
    }
}
