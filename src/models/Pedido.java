package models;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import exceptions.PedidoInvalidoException;

public abstract class Pedido implements Rastreable{
    private int codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHoraIngreso;
    private EstadoPedido estado;

    public enum TipoPedido{
        PEDIDO_FRAGIL,
        PEDIDO_NORMAL,
        PEDIDO_URGENTE;

        @Override
        public String toString() {
            switch (this) {
                case PEDIDO_FRAGIL:
                    return "Pedido Fragil";
                case PEDIDO_NORMAL:
                    return "Pedido Normal";
                case PEDIDO_URGENTE:
                    return "Pedido Urgente";
                default:
                    return null;
            }
        }
    }

    public Pedido(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, LocalDateTime fechaHoraIngreso, EstadoPedido estado) throws PedidoInvalidoException{
        setCodigo(codigo);
        setCliente(cliente);
        setDestino(destino);
        setPesoKg(pesoKg);
        setDistanciaKm(distanciaKm);
        setfechaHoraIngreso(fechaHoraIngreso);
        setEstado(estado);
    }

    public Pedido(Cliente cliente, String destino, double pesoKg, double distanciaKm) throws PedidoInvalidoException{
        setCliente(cliente);
        setDestino(destino);
        setDistanciaKm(distanciaKm);
        setPesoKg(pesoKg);
    }

    public Pedido(){
        
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

    public void setDestino(String destino) throws PedidoInvalidoException{
        if (destino.isBlank()){
            throw new PedidoInvalidoException("El destino del pedido no puede estar vacio.");
        }
        this.destino = destino;
    }

    public void setPesoKg(double pesoKg) throws PedidoInvalidoException{
        if (pesoKg <= 0){
            throw new PedidoInvalidoException("El peso del pedido no puede ser negativo o cero.");
        }
        this.pesoKg = pesoKg;
    }

    public void setDistanciaKm(double distanciaKm) throws PedidoInvalidoException{
        if (distanciaKm <= 0){
            throw new PedidoInvalidoException("La distancia del pedido no puede ser negativa o cero.");
        }
        this.distanciaKm = distanciaKm;
    }

    public void setfechaHoraIngreso(LocalDateTime fechaHoraIngreso){
        this.fechaHoraIngreso = fechaHoraIngreso;
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

    public LocalDateTime getfechaHoraIngreso(){
        return this.fechaHoraIngreso;
    }

    public EstadoPedido getEstado(){
        return this.estado;
    }
    
    @Override
    public String obtenerEstadoActual() {
        return this.getEstado().toString();
    }

    public abstract BigDecimal calcularCosto(Vehiculo vehiculo);

}


