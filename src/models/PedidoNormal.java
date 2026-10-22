import java.time.LocalDateTime;

public class PedidoNormal extends Pedido{

    public PedidoNormal(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm,
            LocalDateTime fechaHora, EstadoPedido estado) {
        super(codigo, cliente, destino, pesoKg, distanciaKm, fechaHora, estado);
    }

    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        return 1000 + (this.getDistanciaKm()*vehiculo.getCostoPorKm());
    }
    
}
