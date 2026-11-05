package models;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import exceptions.PedidoInvalidoException;

public class PedidoFragil extends Pedido{

    public PedidoFragil(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm,
            LocalDateTime fechaHora, EstadoPedido estado) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora, estado);
    }

    public PedidoFragil(Cliente cliente, String destino, double pesoKg, double distanciaKm)
            throws PedidoInvalidoException {
        super(cliente, destino, pesoKg, distanciaKm);
    }

    public PedidoFragil(){
    }

    @Override
    public BigDecimal calcularCosto(Vehiculo vehiculo) {
        return new BigDecimal("1800").add(BigDecimal.valueOf(this.getDistanciaKm()).multiply(vehiculo.getCostoPorKm()));
    }
    
}
