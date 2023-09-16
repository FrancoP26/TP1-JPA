package com.desarrollo.tp1;

import com.desarrollo.tp1.entidades.*;
import com.desarrollo.tp1.enums.Estado;
import com.desarrollo.tp1.enums.FormaPago;
import com.desarrollo.tp1.enums.TipoEnvio;
import com.desarrollo.tp1.enums.TipoProducto;
import com.desarrollo.tp1.repositorios.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Tp1Application {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository, ProductoRepository productoRepository, RubroRepository rubroRepository) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			Rubro rubro1 = Rubro.builder()
					.denominacion("Comida")
					.build();
			Rubro rubro2 = Rubro.builder()
					.denominacion("Bebida")
					.build();

			Producto producto1 = Producto.builder()
					.tipo(TipoProducto.MANUFACTURADO)
					.tiempoEstimadoCocina(50)
					.denominacion("Lomo")
					.precioVenta(4000)
					.precioCompra(2000)
					.stockActual(20)
					.stockMinimo(10)
					.unidadMedida("cm")
					.receta("A")
					.build();
			Producto producto2 = Producto.builder()
					.tipo(TipoProducto.INSUMO)
					.tiempoEstimadoCocina(10)
					.denominacion("Gaseosa")
					.precioVenta(750)
					.precioCompra(300)
					.stockActual(20)
					.stockMinimo(10)
					.unidadMedida("L")
					.receta("B")
					.build();
			Producto producto3 = Producto.builder()
					.tipo(TipoProducto.INSUMO)
					.tiempoEstimadoCocina(10)
					.denominacion("Cerveza")
					.precioVenta(1000)
					.precioCompra(400)
					.stockActual(20)
					.stockMinimo(10)
					.unidadMedida("L")
					.receta("C")
					.build();

			rubro1.agregarProducto(producto1);

			rubro2.agregarProducto(producto2);
			rubro2.agregarProducto(producto3);

			rubroRepository.save(rubro1);
			rubroRepository.save(rubro2);

			Cliente cliente1 = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.telefono("2615542595")
					.email("juanperez@gmail.com")
					.build();
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Neuquén")
					.numero("344")
					.localidad("Las Heras")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Coronel Rodriguez")
					.numero("273")
					.localidad("Ciudad")
					.build();

			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);

			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(4000)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(1500)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(8000)
					.build();
			DetallePedido detallePedido4 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(2000)
					.build();

			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

			Pedido pedido1 = Pedido.builder()
					.estado(Estado.INICIADO)
					.fecha(formato.parse("2023-9-13"))
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(5500)
					.build();
			Pedido pedido2 = Pedido.builder()
					.estado(Estado.ENTREGADO)
					.fecha(formato.parse("2023-9-10"))
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(10000)
					.build();
			Factura factura1 = Factura.builder()
					.numero(123)
					.fecha(formato.parse("2023-9-20"))
					.descuento(1000)
					.formaPago(FormaPago.ETC)
					.total(4500)
					.build();
			Factura factura2 = Factura.builder()
					.numero(122)
					.fecha(formato.parse("2023-9-17"))
					.descuento(1500)
					.formaPago(FormaPago.EFECTIVO)
					.total(8500)
					.build();


			pedido1.agregarDetalle(detallePedido1);
			pedido1.agregarDetalle(detallePedido2);
			pedido2.agregarDetalle(detallePedido3);
			pedido2.agregarDetalle(detallePedido4);

			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);

			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
			detallePedido4.setProducto(producto3);

			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);



			productoRepository.save(producto1);
			productoRepository.save(producto2);
			productoRepository.save(producto3);
			clienteRepository.save(cliente1);


			Rubro rubroRecuperado1 = rubroRepository.findById(rubro1.getId()).orElse(null);
			Rubro rubroRecuperado2 = rubroRepository.findById(rubro2.getId()).orElse(null);
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);

			if (rubroRecuperado1 != null){
				System.out.println("----------------------------------------");
				System.out.println("Rubro 1\nDenominacion: " + rubroRecuperado1.getDenominacion());
				rubroRecuperado1.mostrarProductos();
			}

			if (rubroRecuperado2 != null){
				System.out.println("Rubro 2\nDenominacion: " + rubroRecuperado2.getDenominacion());
				rubroRecuperado2.mostrarProductos();
			}

			if (clienteRecuperado != null){
				System.out.println("Cliente 1");
				System.out.println("\nNombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Mail: " + clienteRecuperado.getEmail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarDomicilios();
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarPedidos();
				clienteRecuperado.mostrarFacturas();
			}
		};

	}

}
