package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import exceptions.PedidoInvalidoException;

public class PedidoNormal extends Pedido{

    public PedidoNormal(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm,
            LocalDateTime fechaHora, EstadoPedido estado) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora, estado);
    }

    public PedidoNormal(Cliente cliente, String destino, double pesoKg, double distanciaKm)
            throws PedidoInvalidoException {
        super(cliente, destino, pesoKg, distanciaKm);
    }

    public PedidoNormal() {
    }

    @Override
    public BigDecimal calcularCosto(Vehiculo vehiculo) {
        return new BigDecimal("1000").add(BigDecimal.valueOf(this.getDistanciaKm()).multiply(vehiculo.getCostoPorKm()));
    }
    
}
