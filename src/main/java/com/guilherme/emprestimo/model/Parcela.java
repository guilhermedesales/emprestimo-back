package com.guilherme.emprestimo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero; // Ex: 1 a 47
    private Integer ano; // 2024 até 2028
    private Integer mes; // 1 a 12
    private Boolean pago = false;

    private Double valor = 4021.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getMesAnoFormatado() {
        if (this.mes == null || this.ano == null) {
            return "Indefinido";
        }
        Month monthEnum = Month.of(this.mes);
        String nomeMes = monthEnum.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
        return nomeMes + " de " + this.ano;
    }

}
