package com.tapecaria.cadastrousuario.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "tb_usuario")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private String nome;
    private int idade;
    private String cidade;
    private String telefone;
    private String email;
    private String senha;


}
