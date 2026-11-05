package exceptions;

//"Se intenta agregar a un arreglo que ya esta lleno"
public class AlmacenamientoLlenoException extends Exception{

    public AlmacenamientoLlenoException() {
    }

    public AlmacenamientoLlenoException(String message) {
        super(message);
    }
    
}
