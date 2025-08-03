package com.guilherme.emprestimo.service;

import com.guilherme.emprestimo.model.Emprestimo;
import com.guilherme.emprestimo.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean atualizar(Long id, Emprestimo novo) {
        Optional<Emprestimo> existente = emprestimoRepository.findById(id);
        if (existente.isEmpty()) return false;

        Emprestimo e = existente.get();
        e.setData(novo.getData());
        e.setValor(novo.getValor());
        emprestimoRepository.save(e);
        return true;
    }

}
