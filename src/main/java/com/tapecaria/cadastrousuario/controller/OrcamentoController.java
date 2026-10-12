package com.tapecaria.cadastrousuario.controller;

import com.tapecaria.cadastrousuario.entity.Orcamento;
import com.tapecaria.cadastrousuario.entity.Usuario;
import com.tapecaria.cadastrousuario.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orcamentos")
@CrossOrigin(origins = "*")

public class OrcamentoController {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @PostMapping
    public Orcamento criarOrcamento(@RequestBody Orcamento orcamento){
        return orcamentoRepository.save(orcamento);
    }
    @GetMapping
    public List<Orcamento> listarOrcamentos(){
        return orcamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Orcamento buscarOrcamentoPorId(@PathVariable Long id){
        return orcamentoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id){
        orcamentoRepository.deleteById(id);
    }
}
