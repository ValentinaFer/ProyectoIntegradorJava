package models;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Pedido implements Facturable{
    private int codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHora;
    private EstadoPedido estado;

    public Pedido(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHora, EstadoPedido estado){
        setCodigo(codigo);
        setCliente(cliente);
        setDestino(destino);
        setPesoKg(pesoKg);
        setDistanciaKm(distanciaKm);
        setFechaHora(fechaHora);
        setEstado(estado);
    }

    public void setCodigo(int codigo){
        if (codigo <= 0){
            throw new IllegalArgumentException("El codigo del pedido no puede ser negativo o cero.");
        }
        this.codigo = codigo;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void setDestino(String destino){
        if (destino.isBlank()){
            throw new IllegalArgumentException("El destino del pedido no puede estar vacio.");
        }
        this.destino = destino;
    }

    public void setPesoKg(double pesoKg){
        if (pesoKg <= 0){
            throw new IllegalArgumentException("El peso del pedido no puede ser negativo o cero.");
        }
        this.pesoKg = pesoKg;
    }

    public void setDistanciaKm(double distanciaKm){
        if (distanciaKm <= 0){
            throw new IllegalArgumentException("La distancia del pedido no puede ser negativa o cero.");
        }
        this.distanciaKm = distanciaKm;
    }

    public void setFechaHora(LocalDateTime fechaHora){
        this.fechaHora = fechaHora;
    }

    public void setEstado(EstadoPedido estadoPedido){
        this.estado = estadoPedido;
    }

    public int getCodigo(){
        return this.codigo;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public String getDestino(){
        return this.destino;
    }

    public double getPesoKg(){
        return this.pesoKg;
    }

    public double getDistanciaKm(){
        return this.distanciaKm;
    }

    public LocalDateTime getFechaHora(){
        return this.fechaHora;
    }

    public EstadoPedido getEstado(){
        return this.estado;
    }

    public abstract BigDecimal calcularCosto(Vehiculo vehiculo);

}


