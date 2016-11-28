package models;

import java.util.Date;

/**
 * Created by pedro on 28/11/16.
 */
public class Carro {

    private String modelo;
    private String fabricante;
    private String placa;
    private Date anoFabricacao;
    private String cor;

    public Carro(String modelo, String fabricante, String placa, Date anoFabricacao, String cor) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Date anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
