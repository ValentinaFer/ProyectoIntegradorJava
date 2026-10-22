import java.math.BigDecimal;

public class Camioneta extends Vehiculo{

    private static final int TIEMPO_EXTRA = 10;

    public Camioneta(String patente, double capacidadKg, double velocidadKmH, BigDecimal costoPorKm) {
        super(patente, capacidadKg, velocidadKmH, costoPorKm);
    }

    @Override
    public double calcularTiempoEntrega(double km) {
        return (km/this.getVelocidadKmH())*60 + Camioneta.TIEMPO_EXTRA;
    }
    
}
