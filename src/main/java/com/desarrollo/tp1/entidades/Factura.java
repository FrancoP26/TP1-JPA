package com.desarrollo.tp1.entidades;

import com.desarrollo.tp1.enums.FormaPago;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends BaseEntidad {

    private int numero;
    private Date fecha;
    private double descuento;
    private int total;
    private FormaPago formaPago;
}
