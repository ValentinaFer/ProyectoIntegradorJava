package models;

public final class Validation {

    private Validation(){

    }

    public static String getDestinoStatus(String destino){
        if (destino == null || destino.isBlank()){
            return "empty";
        } else if (destino.length() > 100){
            return "too-long";
        }
        return "valid";
    }
    
    public static String getNombreStatus(String nombre){
        if (nombre == null || nombre.isBlank()){
            return "empty";
        } else if (nombre.length() > 100){
            return "too-long";
        }
        return "valid";
    }

    public static String getTelefonoStatus(String telefono){
        if (telefono == null || telefono.isBlank()){
            return "empty";
        } else if (telefono.length() > 15){
            return "too-long";
        } else if (!telefono.matches("[0-9]+")){
            return "nan"; 
        }
        return "valid";
    }

    public static String getZonaCiudadStatus(String zonaCiudad){
        if (zonaCiudad == null || zonaCiudad.isBlank()){
            return "empty";
        } else if (zonaCiudad.length() > 300){
            return "too-long";
        }
        return "valid";
    }

    public static String getLegajoStatus(String legajo){
        if (legajo == null || legajo.isBlank()){
            return "empty";
        } else if (legajo.length() > 100){
            return "too-long";
        }
        return "valid";
    }

    public static String getPatenteStatus(String patente){
        if (patente == null || patente.isBlank()){
            return "empty";
        } else if (patente.length() > 10){
            return "too-long";
        } 
        return "valid";
    }
}
