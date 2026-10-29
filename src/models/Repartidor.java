package models;
public class Repartidor {

    private String legajo;
    private String nombre;
    private Vehiculo vehiculo;
    private Pedido[] pedidosAsignados = new Pedido[10];

    public Repartidor(String legajo, String nombre, Vehiculo vehiculo, Pedido[] pedidosAsignados){
        
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        if (legajo.isBlank()){
            throw new IllegalArgumentException("El legajo del repartidor no puede estar vacio.");
        }
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.isBlank()){
            throw new IllegalArgumentException("El nombre del repartidor no puede estar vacio.");
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


}
