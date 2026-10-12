package com.tapecaria.cadastrousuario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name= "tb_orcamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    private String servico;
    private String material;
    private String cliente;
    private BigDecimal metragemTecido;
    private BigDecimal metragemManta;
    private BigDecimal metragemEspuma;

    private String servico;         // Mapeia para "descricao" do React
    private String material;
    private String cliente;

    private BigDecimal metragemTecido;
    private BigDecimal metragemManta;
    private BigDecimal metragemEspuma;

    private BigDecimal precoMetroTecido;
    private BigDecimal precoMetroManta;
    private BigDecimal precoMetroEspuma;

    private BigDecimal frete;        
    private BigDecimal valorTotal;

    // Método para calcular o valor total com validação defensiva contra nulls
    public void calcularValorTotal() {
        BigDecimal totalTecido = (metragemTecido != null && precoMetroTecido != null)
                ? metragemTecido.multiply(precoMetroTecido) : BigDecimal.ZERO;

        BigDecimal totalManta = (metragemManta != null && precoMetroManta != null)
                ? metragemManta.multiply(precoMetroManta) : BigDecimal.ZERO;

        BigDecimal totalEspuma = (metragemEspuma != null && precoMetroEspuma != null)
                ? metragemEspuma.multiply(precoMetroEspuma) : BigDecimal.ZERO;

        BigDecimal valorDiversos = (frete != null) ? frete : BigDecimal.ZERO;

        this.valorTotal = totalTecido
                .add(totalManta)
                .add(totalEspuma)
                .add(valorDiversos);
    }
}
