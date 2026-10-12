package com.tapecaria.cadastrousuario.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "tb_clientes")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;
    private String cpf;
    private String rg;
    private String telefone;
    private String endereco;
    private String nome;
}
