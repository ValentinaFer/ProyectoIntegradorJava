package app;

import java.util.Scanner;

import models.Cliente;
import models.Repartidor;
import models.Validation;

//"Contiene el menu y la lectura por teclado"
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CityLink gestor = new CityLink();
    public static void main(String[] args) {
        String option;
        while(true){
            System.out.println("[MENU CITYLINK]");
            System.out.println("[1]. Registrar Cliente;");            
            System.out.println("[2]. Registrar Repartidor y su Vehiculo;");           
            System.out.println("[3]. Registrar Pedido;");
            System.out.println("[4]. Asignar Pedido;");
            System.out.println("[5]. Cambiar estado de Pedido;");
            System.out.println("[6]. Buscar Cliente;");
            System.out.println("[7]. Listar Pedidos con su costo y tiempo;"); //polymorphism
            System.out.println("[8]. Consultar Estado de un Pedido o de un Repartidor;"); //Rastreable
            System.out.println("[9]. Ver importe total de los pedidos y la comision de cada repartidor;"); //facturable
            System.out.println("[10]. SALIR.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    registrarCliente();
                    break;
                case "9":
                    break;
                case "10":
                    System.out.println("Saliendo...");
                    return;
                default:
                    break;
            }
        }
    }

    public static void registrarRepartidor(){
        Repartidor repartidor = new Repartidor();
        String option;
        while(true){
            System.out.println("---[MENU REPARTIDOR NUEVO]---");
            System.out.println("[1]. Nombre: " + repartidor.getNombre() == null? "": repartidor.getNombre());
            System.out.println("[2]. Legajo: " + repartidor.getLegajo()==null?"":repartidor.getLegajo());
            System.out.println("[3]. Vehiculo: "+ repartidor.getVehiculo() == null? "Sin vehiculo": "Patente: "+repartidor.getVehiculo().getPatente());
            System.out.println("[4]. CANCELAR;");
            System.out.println("[5]. GUARDAR");
            System.out.println("Ingrese el campo a modificar:");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    repartidor.setNombre(askNombre());
                    break;
                case "2":

                    break;
                default:
                    break;
            }
        }
    }


    public static void registrarCliente(){
        Cliente cliente = new Cliente();
        String option;
        while (true){
            System.out.println("---[MENU CLIENTE NUEVO]---");
            System.out.println("[1]. Nombre:" + cliente.getNombre()== null?"":cliente.getNombre());
            System.out.println("[2]. Telefono:" + cliente.getTelefono()== null?"":cliente.getTelefono());
            System.out.println("[3]. Zona Ciudad:" + cliente.getZonaCiudad()== null?"":cliente.getZonaCiudad());
            System.out.println("[4]. CANCELAR;");
            System.out.println("[5]. GUARDAR.");
            System.out.println("Ingrese el campo a modificar:");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    cliente.setNombre(askNombre());
                    break;
                case "2":
                    cliente.setTelefono(askTelefono());
                    break;
                case "3":
                    cliente.setZonaCiudad(askZonaCiudad());
                    break;
                case "4":
                    System.out.println("CLIENTE CANCELADO");
                    return;
                case "5":
                    if (Validation.getNombreStatus(cliente.getNombre()) == "empty"){
                        System.out.println("[UY!] El cliente debe tener un nombre antes de guardar.");
                    } else if (Validation.getTelefonoStatus(cliente.getTelefono()) == "empty"){
                        System.out.println("[UY!] El cliente debe tener un numero de telefono antes de guardar.");
                    } else if (Validation.getZonaCiudadStatus(cliente.getZonaCiudad()) == "empty"){
                        System.out.println("[UY!] El cliente debe tener una zona de ciudad antes de guardar.");
                    } else {
                        gestor.addNewClient(cliente);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static String askLegajo(){
        String option, status;
        while (true) {
            System.out.println("Ingrese el legajo del repartidor: ");
            option = scanner.nextLine();
            status = Validation.getLegajoStatus(option);
            if (status == "empty"){
                System.out.println("[UY!] El legajo no puede estar vacio.");
            } else if (status == "too-long"){
                System.out.println("[UY!] El legajo no puede superar los 100 caracteres.");
            } else {
                return option;
            }
        }
    }

    public static String askNombre(){
        String option, status;
        while(true){
            System.out.println("Ingrese el nombre:");
            option = scanner.nextLine();
            status = Validation.getNombreStatus(option);
            if (status == "empty"){
                System.out.println("[UY!] El nombre no puede estar vacio.");
            } else if (status == "too-long"){
                System.out.println("[UY!] El nombre no puede superar los 100 caracteres.");
            } else {
                return option;
            }
        }
    }

    public static String askTelefono(){
        String option, status;
        while (true){
            System.out.println("Ingrese el numero de telefono del cliente:");
            option = scanner.nextLine();
            status = Validation.getTelefonoStatus(option);
            if (status == "empty"){
                System.out.println("[UY!] El numero de telefono del cliente no puede estar vacio.");
            } else if (status == "too-long"){
                System.out.println("[UY!] El numero de telefono del cliente no debe superar los 15 caracteres de largo.");
            } else if (status == "nan"){
                System.out.println("[UY!] El numero de telefono del cliente debe contener caracteres numericos unicamente.");
            } else {
                return option;
            }
        }
    }

    public static String askZonaCiudad(){
        String option, status;
        while (true) {
            System.out.println("Ingrese la Zona de Ciudad del cliente:");
            option = scanner.nextLine();
            status = Validation.getZonaCiudadStatus(option);
            if (status == "empty"){
                System.out.println("[UY!] La zona de ciudad del cliente no puede estar vacia.");
            } else if (status == "too-long"){
                System.out.println("[UY!] La zona de ciudad del cliente no puede superar los 300 caracteres.");
            } else {
                return option;
            }
        }
    }
}
