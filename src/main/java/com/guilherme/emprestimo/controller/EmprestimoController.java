package com.guilherme.emprestimo.controller;

import com.guilherme.emprestimo.model.Emprestimo;
import com.guilherme.emprestimo.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos") //caminho da API
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public Emprestimo salvar(@RequestBody Emprestimo emprestimo){
        return emprestimoService.salvar(emprestimo);
    }

    @GetMapping
    public List<Emprestimo> listarTodos(){
        return emprestimoService.listarTodos();
    }

    @GetMapping("/total")
    public double obterTotal(){
        return emprestimoService.calcularTotal();
    }

}
