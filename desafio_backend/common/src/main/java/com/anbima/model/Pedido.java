package com.anbima.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_lanche", length = 20)
    private String tipoLanche;

    @Column(length = 20)
    private String proteina;

    @Column(length = 20)
    private String acompanhamento;

    private int quantidade;

    @Column(length = 20)
    private String bebida;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

}