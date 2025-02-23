package com.bancoDev.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.bancoDev.models.enums.EstadoPago;
import com.bancoDev.models.enums.TipoPago;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_pagos")
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fec_pag_est")
    private LocalDate fechaPagoEstimada;

    @Column(name = "fec_pag_rea")
    private LocalDateTime fechaPagoRealizada;

    @Column(name = "monto_pago")
    private BigDecimal montoPago;

    @Enumerated(EnumType.STRING)
    private EstadoPago estado;

    @Column(name = "tipo_pago")
    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;

    @Column(name = "empleado_id")
    private int empleadoId;

    @ManyToOne
    @JoinColumn(name = "prestamo_Id")
    private PrestamoEntity prestamoId;

}
