package exceptions;

//"El peso supera la capacidad del vehiculo o se intenta asignar un pedido fragil a una bicicleta"
public class CapacidadExcedidaException extends Exception{

    public CapacidadExcedidaException() {
    }

    public CapacidadExcedidaException(String message) {
        super(message);
    }
    
}
