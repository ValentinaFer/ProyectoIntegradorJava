package exceptions;

//"Peso o distancia no positivos, destino vacio o codigo duplicado"
public class PedidoInvalidoException extends Exception{

    public PedidoInvalidoException() {
    }

    public PedidoInvalidoException(String message) {
        super(message);
    }

    
}
