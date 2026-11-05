package models;

import java.math.BigDecimal;
import java.util.Arrays;

public class Repartidor implements Facturable, Rastreable{

    private String legajo;
    private String nombre;
    private Vehiculo vehiculo;
    private Pedido[] pedidosAsignados = new Pedido[10]; //no assigning more pedidos without checking if the guy isn't already doing work lol(Rastreable: if the guy is available for more pedidos or not. not if array simply empty, must check Pedidos status)
    private int pedidosAsigLength = 0;

    public Repartidor(String legajo, String nombre, Vehiculo vehiculo, Pedido[] pedidosAsignados,
            int pedidosAsigLength) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.vehiculo = vehiculo;
        this.pedidosAsignados = pedidosAsignados;
        this.pedidosAsigLength = pedidosAsigLength;
    }

    public Repartidor(String legajo, String nombre, Vehiculo vehiculo) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.vehiculo = vehiculo;
    }

    public Repartidor() {
    }

    public int getPedidosAsigLength() {
        return pedidosAsigLength;
    }

    public void setPedidosAsigLength(int pedidosAsigLength) {
        this.pedidosAsigLength = pedidosAsigLength;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        String status = Validation.getLegajoStatus(legajo);
        if (status == "empty"){
            throw new IllegalArgumentException("El legajo del repartidor no puede estar vacio.");
        } else if (status == "too-long"){
            throw new IllegalArgumentException("El legajo del repartidor no puede superar los 100 caracteres.");
        }
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String status = Validation.getNombreStatus(nombre);
        if (status == "empty"){
            throw new IllegalArgumentException("El nombre del repartidor no puede estar vacio.");
        } else if (status == "too-long"){
            throw new IllegalArgumentException("El nombre del repartidor no puede exceder los 100 caracteres.");
        }
        this.nombre = nombre;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Pedido[] getPedidosAsignados() {
        return pedidosAsignados;
    }

    public void setPedidosAsignados(Pedido[] pedidosAsignados) {
        this.pedidosAsignados = pedidosAsignados;
    }

    @Override
    public String toString() { //check later; make it prettier
        return "Repartidor [legajo=" + legajo + ", nombre=" + nombre + ", vehiculo=" + vehiculo + ", pedidosAsignados="
                + Arrays.toString(pedidosAsignados) + "]";
    }

    @Override
    public BigDecimal CalcularImporte() {
        return null;
        //10% del importe de los pedidos entregados.
    }

    //Asumiendo que un Repartidor NO esta disponible si su lista de pedidos esta llena o si tiene alguno de sus pedidos en estado EN_CAMINO.
    public boolean isAvailable(){
        if (this.pedidosAsigLength >= pedidosAsignados.length){
            return false;
        } 
        for (int i = 0; i < pedidosAsigLength; i++) {
            if (pedidosAsignados[i].getEstado() == EstadoPedido.EN_CAMINO){
                return false;
            }
        }
        return true;
    }

    public boolean addPedido(Pedido pedido){
        if (pedidosAsigLength >= pedidosAsignados.length){
            return false;
        }
        pedidosAsignados[pedidosAsigLength++] = pedido;
        return true;
    }

    @Override
    public String obtenerEstadoActual() {
        return isAvailable() ? "Disponible":"No Disponible";
    }

}
