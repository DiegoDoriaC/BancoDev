package com.bancoDev.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.bancoDev.models.enums.Estado;
import com.bancoDev.models.enums.PlazoMeses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_prestamos")
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private BigDecimal monto;

    @Column(name = "taza_interes")
    private int tazaInteres;

    @Enumerated(EnumType.STRING)
    private PlazoMeses plazoMeses;

    @CreatedDate
    @Column(name = "fecha_aprobacion")
    @Setter(AccessLevel.NONE)
    private LocalDateTime fechaAprobacion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "empleado_id")
    private int empleadoId;

    @Column(name = "cliente_id")
    private int clienteId;

}
