package com.tapecaria.cadastrousuario.controller;

import com.tapecaria.cadastrousuario.entity.Cliente;
import com.tapecaria.cadastrousuario.entity.Usuario;
import com.tapecaria.cadastrousuario.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }
}
