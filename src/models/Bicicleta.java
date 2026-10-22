import java.math.BigDecimal;

public class Bicicleta extends Vehiculo{

    public Bicicleta(String patente, double capacidadKg, double velocidadKmH, BigDecimal costoPorKm) {
        super(patente, capacidadKg, velocidadKmH, costoPorKm);
    }

    @Override
    public double calcularTiempoEntrega(double km) {
        return (km/this.getVelocidadKmH())*60;
    }
    
    @Override 
    public boolean puedeTransportar(Pedido pedido){
        if (pedido instanceof PedidoFragil){
            return false;
        }
        return super.puedeTransportar(pedido);
    };

}
