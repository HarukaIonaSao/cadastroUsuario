package com.tapecaria.cadastrousuario.repository;

import com.tapecaria.cadastrousuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

}
