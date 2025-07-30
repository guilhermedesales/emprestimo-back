package com.guilherme.emprestimo.model;

import jakarta.persistence.*; // mapeia a classe como uma tabela no banco (jpa)
import java.time.LocalDate;

@Entity
public class Emprestimo {

    @Id //chave primaria (pk)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment (gera o id automaticamente)
    private Long id;

    private LocalDate data;
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
