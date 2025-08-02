package com.guilherme.emprestimo.config;

import com.guilherme.emprestimo.model.Parcela;
import com.guilherme.emprestimo.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ParcelaInitializer {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @PostConstruct
    public void initParcelas() {
        if (parcelaRepository.count() == 0) {
            int numero = 1;
            for (int ano = 2024; ano <= 2028; ano++) {
                int mesInicial = (ano == 2024) ? 12 : 1;
                for (int mes = mesInicial; mes <= 12; mes++) {
                    Parcela p = new Parcela();
                    p.setNumero(numero++);
                    p.setAno(ano);
                    p.setMes(mes);
                    p.setPago(false);
                    p.setValor(4021.0);
                    parcelaRepository.save(p);
                }
            }
            System.out.println("Parcelas iniciais criadas com sucesso!");
        }
    }
}
