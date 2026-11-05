package exceptions;

public class EstadoInvalidoException extends RuntimeException{

    public EstadoInvalidoException() {
    }

    public EstadoInvalidoException(String message) {
        super(message);
    }
    
}
