package com.tapecaria.cadastrousuario.service;

import com.tapecaria.cadastrousuario.entity.Orcamento;
import com.tapecaria.cadastrousuario.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository repository;

    public Orcamento salvar(Orcamento orcamento) {
        // Executa a lógica de negócio do cálculo
        orcamento.calcularValorTotal();

        // Salva a entidade com o valorTotal devidamente calculado
        return repository.save(orcamento);
    }
}
