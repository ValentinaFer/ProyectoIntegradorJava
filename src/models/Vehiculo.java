package models;
import java.math.BigDecimal;

public abstract class Vehiculo {
    private String patente;
    private double capacidadKg;
    private double velocidadKmH;
    private BigDecimal costoPorKm;

    public enum TipoVehiculo {
        BICICLETA,
        MOTO,
        CAMIONETA      
    }

    public Vehiculo(String patente, double capacidadKg, double velocidadKmH, BigDecimal costoPorKm){
        setPatente(patente);
        setCapacidadKg(capacidadKg);
        setVelocidadKmH(velocidadKmH);
        setCostoPorKm(costoPorKm);
    }

    public void setPatente(String patente){
        String status = Validation.getPatenteStatus(patente);
        if (status == "empty"){
            throw new IllegalArgumentException("La patente del vehiculo no puede estar vacia.");
        } else if (status == "too-long"){
            throw new IllegalArgumentException("La patente del vehiculo no puede contener mas de 10 caracteres.");
        }
        this.patente = patente;
    }

    public void setCapacidadKg(double capacidadKg){
        if (!Double.isFinite(capacidadKg) || capacidadKg <= 0){
            throw new IllegalArgumentException("La capacidad del vehiculo no puede ser negativa ni cero.");
        }
        this.capacidadKg = capacidadKg;
    }

    public void setVelocidadKmH(double velocidadKmH){
        if (!Double.isFinite(velocidadKmH) || velocidadKmH <= 0){
            throw new IllegalArgumentException("La velocidad media del vehiculo no puede ser negativa ni cero.");
        }
        this.velocidadKmH = velocidadKmH;
    }

    public void setCostoPorKm(BigDecimal costoPorKm){
        if (costoPorKm == null || costoPorKm.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("La velocidad media del vehiculo no puede ser negativa ni cero.");
        }
        this.costoPorKm = costoPorKm;
    }

    public String getPatente(){
        return this.patente;
    }

    public double getCapacidadKg(){
        return this.capacidadKg;
    }

    public double getVelocidadKmH(){
        return this.velocidadKmH;
    }

    public BigDecimal getCostoPorKm(){
        return this.costoPorKm;
    }

    public abstract double calcularTiempoEntrega(double km);

    public boolean puedeTransportar(Pedido pedido){
        return pedido.getPesoKg() <= this.getCapacidadKg();
    };
}
