package com.guilherme.emprestimo.repository;

import com.guilherme.emprestimo.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
} // repository pra obj do tipo Emprestimo e Long como tipo de pk
