import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoFragil extends Pedido{

    public PedidoFragil(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm,
            LocalDateTime fechaHora, EstadoPedido estado) {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora, estado);
    }

    @Override
    public BigDecimal calcularCosto(Vehiculo vehiculo) {
        return new BigDecimal("1800").add(BigDecimal.valueOf(this.getDistanciaKm()).multiply(vehiculo.getCostoPorKm()));
    }
    
}
