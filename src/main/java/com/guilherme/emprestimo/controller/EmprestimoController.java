package com.guilherme.emprestimo.controller;

import com.guilherme.emprestimo.model.Emprestimo;
import com.guilherme.emprestimo.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Double obterTotal() {
        return emprestimoService.calcularTotal();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Emprestimo novo) {
        boolean atualizado = emprestimoService.atualizar(id, novo);
        if (!atualizado) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }




}
