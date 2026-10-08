class Cliente {
    private static int nextCodigo = 1;
    private int codigo;
    private String nombre;
    private String telefono;
    private String zonaCiudad;

    public Cliente(String nombre, String telefono, String zoneCiudad){
        this.codigo = nextCodigo++;
        this.setNombre(nombre);
    }

    public Cliente(){

    }

    public void setCodigo(){
        if (this.codigo > 0){
            
        }
    }

    public void setNombre(String nombre){
        if (nombre.isBlank()){
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio.");
        } else if (nombre.length() > 100){
            throw new IllegalArgumentException("El nombre del cliente no puede exceder los 100 caracteres.");
        }
        this.nombre = nombre;
    }

    public void setTelefono(String telefono){
        if (telefono.isBlank()){
            throw new IllegalArgumentException("El telefono del cliente no puede estar vacio.");
        } else if (telefono.length() > 15){
            throw new IllegalArgumentException("El telefono no puede exceder los 15 caracteres.");
        } else if (telefono.matches("\\d+")){ //TODO: TRY THIS
            throw new IllegalArgumentException("El telefono debe contener solo caracteres numericos.");
        }
    }

    public void setZonaCiudad(String zonaCiudad){
        if (zonaCiudad.isBlank()){
            throw new IllegalArgumentException("La zona de ciudad del cliente no puede estar vacia.");
        } else if (zonaCiudad.length() > 300){
            throw new IllegalArgumentException("La zona de ciudad del cliente no puede exceder los 300 caracteres.");
        }
    }

    public int getCodigo(){
        return this.codigo;
    }
}