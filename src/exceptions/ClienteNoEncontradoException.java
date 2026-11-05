package exceptions;

//"Al buscar un cliente que no existe"
public class ClienteNoEncontradoException extends Exception {

    public ClienteNoEncontradoException() {
    }

    public ClienteNoEncontradoException(String message) {
        super(message);
    }
    
}
