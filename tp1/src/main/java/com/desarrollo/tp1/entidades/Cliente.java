package com.desarrollo.tp1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente extends BaseEntidad {

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Domicilio> domicilios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarDomicilio(Domicilio domicilio){
        domicilios.add(domicilio);
    }

    public void agregarPedido(Pedido pedido){
        pedidos.add(pedido);
    }

    public void mostrarDomicilios(){
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        int counter = 0;
        for (Domicilio domicilio : domicilios) {
            counter += 1;
            System.out.println("\nDomicilio "+counter+"\n\tCalle: " + domicilio.getCalle() + ", Número: " + domicilio.getNumero());
        }

    }

    public void mostrarPedidos() {
        System.out.println("Pedidos de " + nombre + " " + apellido + ":");
        int counter1 = 0;
        for (Pedido pedido : pedidos) {
            counter1 += 1;
            System.out.println("\nPedido "+counter1+"\nFecha: " + pedido.getFecha());
            int counter2 = 0;
            for (DetallePedido detalle: pedido.getDetallePedidos()){
                counter2 += 1;
                System.out.println("\tProducto "+counter2+": "+detalle.getProducto().getDenominacion()+"\n\t\tCantidad: "+detalle.getCantidad()+"\n\t\tSubtotal: "+detalle.getSubtotal());
            }
            System.out.println("Total: " + pedido.getTotal());
        }
        System.out.println("----------------------------------------");
    }

    public void mostrarFacturas(){
        System.out.println("Facturas de " + nombre + " " + apellido + ":");
        int counter1 = 0;
        for (Pedido pedido : pedidos) {
            counter1 += 1;
            System.out.println("\nPara pedido "+counter1+" realizado en " + pedido.getFecha()
                    +", su factura correspondiente es:");
            System.out.println("Factura N°"+pedido.getFactura().getNumero()+"\n\tFecha: "+pedido.getFactura().getFecha()+"\n\tDescuento: "+pedido.getFactura().getDescuento()+"\n\tTotal: "+pedido.getFactura().getTotal()+"\n\tMetodo de pago: "+pedido.getFactura().getFormaPago());
        }
        System.out.println("----------------------------------------");
    }

}
