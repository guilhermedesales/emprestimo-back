package com.guilherme.emprestimo.controller;

import com.guilherme.emprestimo.model.Parcela;
import com.guilherme.emprestimo.repository.ParcelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parcelas")
@CrossOrigin
public class ParcelaController {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @GetMapping
    public List<Parcela> listarParcelas() {
        return parcelaRepository.findAllByOrderByNumeroAsc();
    }

    @GetMapping("/{ano}")
    public List<Parcela> listarPorAno(@PathVariable Integer ano) {
        return parcelaRepository.findByAno(ano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> marcarComoPago(@PathVariable Long id, @RequestBody boolean pago) {
        Parcela parcela = parcelaRepository.findById(id).orElse(null);
        if (parcela != null) {
            parcela.setPago(pago);
            parcelaRepository.save(parcela);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/total-pago")
    public BigDecimal getTotalPago() {
        Long parcelasPagas = parcelaRepository.contarParcelasPagas();
        return BigDecimal.valueOf(parcelasPagas * 4021.00);
    }

    @GetMapping("/resumo")
    public ResponseEntity<Map<String, Object>> getResumoParcelas() {
        List<Parcela> parcelasPagas = parcelaRepository.findByPagoTrueOrderByNumeroAsc();

        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalPago", parcelasPagas.stream().mapToDouble(Parcela::getValor).sum());
        resumo.put("quantidadePagas", parcelasPagas.size());
        resumo.put("quantidadeFaltam", 47 - parcelasPagas.size());

        if (!parcelasPagas.isEmpty()) {
            Parcela ultima = parcelasPagas.get(parcelasPagas.size() - 1);
            resumo.put("ultimaParcelaMesAno", ultima.getMesAnoFormatado()); // ex: "outubro de 2025"
        } else {
            resumo.put("ultimaParcelaMesAno", "Nenhuma");
        }

        return ResponseEntity.ok(resumo);
    }

}
