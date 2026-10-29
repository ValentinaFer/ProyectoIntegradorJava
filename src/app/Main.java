package app;

import java.util.Scanner;

//"Contiene el menu y la lectura por teclado"
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
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
            System.out.println("[7]. Listar Pedidos;");
            System.out.println("[8]. Consultar Estado de un Pedido o de un Repartidor;");
            System.out.println("[9]. Ver importe total de los pedidos y la comision de cada repartidor;");
            System.out.println("[10]. SALIR.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    
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
        
    }

}
