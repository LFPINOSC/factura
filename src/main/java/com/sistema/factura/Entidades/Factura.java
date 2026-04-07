package com.sistema.facturasc.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de factura es obligatorio")
    @Size(max = 50, message = "El número de factura no puede superar los 50 caracteres")
    @Column(unique = true)
    private String numeroFactura;

    @NotNull(message = "La fecha de la factura es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.00", message = "El total no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El total debe tener máximo 2 decimales")
    private BigDecimal total;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
    private String estado = "PENDIENTE"; // Ejemplo: PENDIENTE, PAGADA, ANULADA

    @Column(nullable = false)
    private boolean activo = true;

    // Relación: Muchas facturas pueden pertenecer a un solo Cliente
    @NotNull(message = "El cliente es obligatorio para la factura")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
