package models;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import exceptions.PedidoInvalidoException;

public class PedidoUrgente extends Pedido{

    public PedidoUrgente(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm,
            LocalDateTime fechaHora, EstadoPedido estado) throws PedidoInvalidoException {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora, estado);
    }

    public PedidoUrgente(Cliente cliente, String destino, double pesoKg, double distanciaKm)
            throws PedidoInvalidoException {
        super(cliente, destino, pesoKg, distanciaKm);
    }

    public PedidoUrgente(){
        
    }

    @Override
    public BigDecimal calcularCosto(Vehiculo vehiculo) {
        return (new BigDecimal("1000").add(BigDecimal.valueOf(this.getDistanciaKm()).multiply(vehiculo.getCostoPorKm()))).multiply(new BigDecimal("1.5"));
    }
    
}
