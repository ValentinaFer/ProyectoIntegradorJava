public abstract class Vehiculo {
    private String patente;
    private double capacidadKg;
    private double velocidadKmH;
    private double costoPorKm;

    public Vehiculo(String patente, double capacidadKg, double velocidadKmH, double costoPorKm){
        setPatente(patente);
        setCapacidadKg(capacidadKg);
        setVelocidadKmH(velocidadKmH);
        setCostoPorKm(costoPorKm);
    }

    public void setPatente(String patente){
        if (patente.isBlank()){
            throw new IllegalArgumentException("La patente del vehiculo no puede estar vacia.");
        }
        this.patente = patente;
    }

    public void setCapacidadKg(double capacidadKg){
        if (capacidadKg <= 0){
            throw new IllegalArgumentException("La capacidad del vehiculo no puede ser negativa ni cero.");
        }
        this.capacidadKg = capacidadKg;
    }

    public void setVelocidadKmH(double velocidadKmH){
        if (velocidadKmH <= 0){
            throw new IllegalArgumentException("La velocidad media del vehiculo no puede ser negativa ni cero.");
        }
        this.velocidadKmH = velocidadKmH;
    }

    public void setCostoPorKm(double costoPorKm){
        if (costoPorKm <= 0){
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

    public double getCostoPorKm(){
        return this.costoPorKm;
    }

    public abstract double calcularTiempoEntrega(double km);

    public boolean puedeTransportar(Pedido pedido){
        return pedido.getPesoKg() <= this.getCapacidadKg();
    };
}
