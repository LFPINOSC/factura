package com.sistema.factura.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DetallesFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El codigo no puede estar vacía")
    @Size(min = 6, max = 6, message = "El codigo debe ser siempre de 6 caracteres")
    private String codigo;

    @NotBlank(message = "La cantidad no debe estar vacia")
    private int cantidad;

    @NotBlank(message = "El precio no debe estar vacio")
    private double precio;

    @NotBlank(message = "El total no debe estar vacio")
    private double total;
}

    
