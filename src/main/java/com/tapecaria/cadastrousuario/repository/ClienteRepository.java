package com.tapecaria.cadastrousuario.repository;

import com.tapecaria.cadastrousuario.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
