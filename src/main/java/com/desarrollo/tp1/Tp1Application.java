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
import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class Tp1Application {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tp1Application.class, args);
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository, DetallePedidoRepository detallePedidoRepository, DomicilioRepository domicilioRepository, FacturaRepository facturaRepository, PedidoRepository pedidoRepository, ProductoRepository productoRepository, RubroRepository rubroRepository) {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

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
			Rubro rubro1 = Rubro.builder()
					.denominacion("Comida")
					.build();
			Rubro rubro2 = Rubro.builder()
					.denominacion("Bebida")
					.build();


			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);

			pedido1.setFactura(factura1);
			pedido1.agregarDetalle(detallePedido1);
			pedido1.agregarDetalle(detallePedido2);

			pedido2.setFactura(factura2);
			pedido2.agregarDetalle(detallePedido3);
			pedido2.agregarDetalle(detallePedido4);

			rubro1.agregarProducto(producto1);

			rubro2.agregarProducto(producto2);
			rubro2.agregarProducto(producto3);

			clienteRepository.save(cliente1);
			detallePedidoRepository.save(detallePedido1);
			detallePedidoRepository.save(detallePedido2);
			detallePedidoRepository.save(detallePedido3);
			detallePedidoRepository.save(detallePedido4);
			domicilioRepository.save(domicilio1);
			domicilioRepository.save(domicilio2);
			facturaRepository.save(factura1);
			facturaRepository.save(factura2);
			pedidoRepository.save(pedido1);
			pedidoRepository.save(pedido2);
			productoRepository.save(producto1);
			productoRepository.save(producto2);
			productoRepository.save(producto3);
			rubroRepository.save(rubro1);
			rubroRepository.save(rubro2);


		};

	}

}
