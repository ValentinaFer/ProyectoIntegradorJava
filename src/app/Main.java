package app;

import java.util.Scanner;

import models.Cliente;

//"Contiene el menu y la lectura por teclado"
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        CityLink gestor = new CityLink();
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

    public static void registrarCliente(){
        Cliente cliente = new Cliente();
        String option;
        while (true){
            System.out.println("---[MENU CLIENTE NUEVO]---");
            System.out.println("[1]. Nombre:" + cliente.getNombre()!= null?"":cliente.getNombre());
            System.out.println("[2]. Telefono:" + cliente.getTelefono()!= null?"":cliente.getTelefono());
            System.out.println("[3]. Zona Ciudad:" + cliente.getZonaCiudad()!= null?"":cliente.getZonaCiudad());
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
                    
                default:
                    break;
            }
        }
    }

    public static String askNombre(){
        String option;
        while(true){
            System.out.println("Ingrese el nombre del cliente:");
            option = scanner.nextLine();
            if (option.isBlank()){
                System.out.println("El nombre del cliente no puede estar vacio.");
            } else if (option.length() > 100){
                System.out.println("El nombre del cliente no puede superar los 100 caracteres.");
            } else {
                return option;
            }
        }
    }

    public static String askTelefono(){
        String option;
        while (true){
            System.out.println("Ingrese el numero de telefono del cliente:");
            option = scanner.nextLine();
            if (option.isBlank()){
                System.out.println("El numero de telefono del cliente no puede estar vacio.");
            } else if (option.matches("")){ //Look for some common regex for phone numbers. or maybe just try parsing?
                System.out.println("El numero de telefono del cliente debe seguir un formato valido");
            } else {
                return option;
            }
        }
    }

}
