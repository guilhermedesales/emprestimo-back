package com.guilherme.emprestimo.repository;

import com.guilherme.emprestimo.model.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
    List<Parcela> findByAno(Integer ano);

    @Query("SELECT COUNT(p) FROM Parcela p WHERE p.pago = true")
    Long contarParcelasPagas();

    List<Parcela> findByPagoTrueOrderByNumeroAsc();
    List<Parcela> findAllByOrderByNumeroAsc();

}
