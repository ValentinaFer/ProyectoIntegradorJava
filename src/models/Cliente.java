package models;

public class Cliente {
    private int codigo;
    private String nombre;
    private String telefono;
    private String zonaCiudad;

    public Cliente(String nombre, String telefono, String zoneCiudad){
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setTelefono(telefono);
        this.setZonaCiudad(zoneCiudad);
    }

    public Cliente(){

    }

    public void setCodigo(int codigo){
        if (this.codigo <= 0){
            throw new IllegalArgumentException("El codigo del cliente no puede ser negativo ni cero.");
        }
        this.codigo = codigo;
    }

    public void setNombre(String nombre){
        String status = Validation.getNombreStatus(nombre);
        if (status == "empty"){
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio.");
        } else if (status == "too-long"){
            throw new IllegalArgumentException("El nombre del cliente no puede exceder los 100 caracteres.");
        }
        this.nombre = nombre;
    }

    public void setTelefono(String telefono){
        String status = Validation.getTelefonoStatus(telefono);
        if (status == "empty"){
            throw new IllegalArgumentException("El telefono del cliente no puede estar vacio.");
        } else if (status == "too-long"){
            throw new IllegalArgumentException("El telefono no puede exceder los 15 caracteres.");
        } else if (status == "nan"){
            throw new IllegalArgumentException("El telefono debe contener caracteres numericos unicamente.");
        }
    }

    public void setZonaCiudad(String zonaCiudad){
        String status = Validation.getZonaCiudadStatus(zonaCiudad);
        if (status == "empty"){
            throw new IllegalArgumentException("La zona de ciudad del cliente no puede estar vacia.");
        } else if (status == "too-long"){
            throw new IllegalArgumentException("La zona de ciudad del cliente no puede exceder los 300 caracteres.");
        }
    }

    public int getCodigo(){
        return this.codigo;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public String getZonaCiudad(){
        return this.zonaCiudad;
    }

    @Override
    public String toString() {
        return "[Cliente nro "+getCodigo()+"]\nNombre: "+getNombre()+",\nTelefono: "+getTelefono()+",\nZona ciudad: "+getZonaCiudad();
    }
}