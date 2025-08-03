package com.guilherme.emprestimo.service;

import com.guilherme.emprestimo.model.Emprestimo;
import com.guilherme.emprestimo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmprestimoService {

    @Autowired // injeta dependencias automaticamente
    private EmprestimoRepository emprestimoRepository;

    public Emprestimo salvar(Emprestimo emprestimo){

        return emprestimoRepository.save(emprestimo);
    }

    // retorna a lista com todos os emprestimos cadastrados
    public List<Emprestimo> listarTodos(){

        return emprestimoRepository.findAll();
    }

    public double calcularTotal(){

        List<Emprestimo> emprestimos = listarTodos();

        // transforma em stream, extrai os valores e soma
        return emprestimos.stream()
                .mapToDouble(Emprestimo::getValor)
                .sum();

    }



}
