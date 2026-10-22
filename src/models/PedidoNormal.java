import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoNormal extends Pedido{

    public PedidoNormal(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm,
            LocalDateTime fechaHora, EstadoPedido estado) {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora, estado);
    }

    @Override
    public BigDecimal calcularCosto(Vehiculo vehiculo) {
        return new BigDecimal("1000").add(BigDecimal.valueOf(this.getDistanciaKm()).multiply(vehiculo.getCostoPorKm()));
    }
    
}
