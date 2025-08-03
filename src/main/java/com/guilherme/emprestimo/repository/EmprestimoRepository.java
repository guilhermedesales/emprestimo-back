package com.guilherme.emprestimo.repository;

import com.guilherme.emprestimo.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    @Query("SELECT SUM(e.valor) FROM Emprestimo e")
    Double calcularTotal();

} // repository pra obj do tipo Emprestimo e Long como tipo de pk
