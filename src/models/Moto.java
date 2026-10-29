package models;
import java.math.BigDecimal;

public class Moto extends Vehiculo{

    private static final int TIEMPO_EXTRA = 5;

    public Moto(String patente, double capacidadKg, double velocidadKmH, BigDecimal costoPorKm) {
        super(patente, capacidadKg, velocidadKmH, costoPorKm);
    }

    @Override
    public double calcularTiempoEntrega(double km) {
        return (km/this.getVelocidadKmH())*60+Moto.TIEMPO_EXTRA;
    }
    
}
