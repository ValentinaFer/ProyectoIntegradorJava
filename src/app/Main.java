package app;

import java.math.BigDecimal;
import java.util.Scanner;

import exceptions.AlmacenamientoLlenoException;
import exceptions.CapacidadExcedidaException;
import exceptions.EstadoInvalidoException;
import exceptions.PedidoInvalidoException;
import models.Bicicleta;
import models.Camioneta;
import models.Cliente;
import models.EstadoPedido;
import models.Moto;
import models.Pedido;
import models.PedidoFragil;
import models.PedidoNormal;
import models.PedidoUrgente;
import models.Repartidor;
import models.Validation;
import models.Vehiculo;
import models.Pedido.TipoPedido;
import models.Vehiculo.TipoVehiculo;

//"Contiene el menu y la lectura por teclado"
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CityLink gestor = new CityLink();

    public static void main(String[] args) {
        String option;
        try {
            Cliente c1, c2, c3, c4, c5;
            c1 = new Cliente("Valentina", "123123", "Barrio Alguno");
            c2 = new Cliente("John", "1231232", "Barrio EnAlgunaParte");
            c3 = new Cliente("Peter", "13654644", "Barrio Marvel");
            c4 = new Cliente("Miguel", "9999999", "Barrio Marvel-2099");
            c5 = new Cliente("Batman", "67676767", "Barrio DC");
            gestor.addNewClient(c1);
            gestor.addNewClient(c2);
            gestor.addNewClient(c3);
            gestor.addNewClient(c4);
            gestor.addNewClient(c5);
            gestor.addNewRepartidor(new Repartidor("11111111", "Robin", new Camioneta("BAT-123", 500, 30, new BigDecimal("300"))));
            gestor.addNewRepartidor(new Repartidor("22222222", "Hobbie", new Camioneta("PUNK-123", 30, 40, new BigDecimal("120"))));
            gestor.addNewRepartidor(new Repartidor("33333333", "Spider-man", new Camioneta("SPIDER-123", 10, 15, new BigDecimal("50"))));
            gestor.addNewPedido(new PedidoFragil(c1, "Destino algo Fragil", 50, 100));
            gestor.addNewPedido(new PedidoNormal(c2, "Destino algo normal", 20, 150));
            gestor.addNewPedido(new PedidoUrgente(c3, "Destino algo Urgente", 100, 100));
            gestor.addNewPedido(new PedidoFragil(c4, "DestinoFrag2", 5.5, 100));
            gestor.asignarPedido(1, "11111111");
        } catch (AlmacenamientoLlenoException e) {
            System.out.println("No deberia ingresar aca LOL");
        } catch (PedidoInvalidoException e) {
            System.out.println("No deberia ingresar aca LOL2");
        } catch (CapacidadExcedidaException e) {
            System.out.println("No deberia ingresar aca LOL3");
        }
        while (true) {
            System.out.println("---[MENU CITYLINK]---");
            System.out.println("[1]. Registrar Cliente;");
            System.out.println("[2]. Registrar Repartidor y su Vehiculo;");
            System.out.println("[3]. Registrar Pedido;");
            System.out.println("[4]. Asignar Pedido;");
            System.out.println("[5]. Cambiar estado de Pedido;");
            System.out.println("[6]. Buscar Cliente;");
            System.out.println("[7]. Listar Pedidos con su costo y tiempo;"); // polymorphism
            System.out.println("[8]. Consultar Estado de un Pedido o de un Repartidor;"); // Rastreable
            System.out.println("[9]. Ver importe total de los pedidos y la comision de cada repartidor;"); // facturable
            System.out.println("[10]. SALIR.");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    try {
                        registrarCliente();
                    } catch (AlmacenamientoLlenoException ex) {
                        System.out.println("[Error]. " + ex.getMessage());
                    }
                    break;
                case "2":
                    try {
                        registrarRepartidor();
                    } catch (AlmacenamientoLlenoException ex) {
                        System.out.println("[Error]. " + ex.getMessage());
                    }
                    break;
                case "3":
                    try {
                        registrarPedido();
                    } catch (AlmacenamientoLlenoException ex) {
                        System.out.println("[Error]. " + ex.getMessage());
                    }
                    break;
                case "4":
                    asignarPedido();
                    break;
                case "5":
                    try {
                        cambiarEstadoPedido();
                    } catch (EstadoInvalidoException e) {
                        System.out.println("[ERROR]. " + e.getMessage());
                    }
                    break;
                case "6":
                    buscarCliente();
                    break;
                case "7":
                    showPedidos();
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    System.out.println("BYE-BYE!");
                    return;
                default:
                    System.out.println("[UY!] Debe ingresar una opcion valida del menu.");
                    break;
            }
        }
    }

    public static Pedido askPedidoCambiable(){
        String option;
        Pedido[] pedidos = gestor.getPedidosCambiables();
        if (pedidos.length <= 0){
            System.out.println("[UY!]. No hay pedidos que puedan ser cambiados en este momento.");
            return null;
        }
        while (true) {
            for (int i = 0; i < pedidos.length; i++) {
            Pedido pedido = pedidos[i];
            System.out.println("["+i+"] Destino: "+ (pedido.getDestino()) + ", Estado: " + (pedido.getEstado().toString()));
            }
            System.out.println("Seleccione el pedido del que desea cambiar el estado: ");
            option = scanner.nextLine();
            try {
                int i = Integer.parseInt(option);
                if (i >= pedidos.length || i < 0) {
                    System.out.println("[UY!]. Debe seleccionar el numero del cliente de la lista.");
                } else {
                    return pedidos[i];
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor numerico de la lista.");
            }
        }
    }

    public static void cambiarEstadoPedido() throws EstadoInvalidoException{
        Pedido pedido = askPedidoCambiable();
        String option;
        if (pedido != null){
            switch (pedido.getEstado()) {
                case PENDIENTE:
                    while(true){
                        System.out.println("[1]. Cambiar a EN CAMINO;");
                        System.out.println("[2]. Cambiar a CANCELADO.");
                        System.out.println("[3]. Volver..");
                        System.out.println("Ingrese el cambio que desea hacer:");
                        option = scanner.nextLine();
                        switch (option) {
                            case "1":
                                if (gestor.cambiarEstadoPedido(pedido.getCodigo())){
                                System.out.println("PEDIDO MODIFICADO CON EXITO");
                                } else {
                                    System.out.println("[!]. Pedido no encontrado");
                                }
                                return;
                            case "2":
                                if (gestor.cancelarPedido(pedido.getCodigo())){
                                System.out.println("PEDIDO CANCELADO CON EXITO");
                                } else {
                                    System.out.println("[!]. Pedido no encontrado");
                                }
                                return;
                            case "3":
                                //should do this in another fun, this is clearly too messy!
                                System.out.println("Volviendo al menu");
                                return; 
                            default:
                                System.out.println("[UY!]. Debe ingresar una de las opciones del menu.");
                                break;
                        }
                    }
                case EN_CAMINO:
                    System.out.println("[1]. Cambiar a ENTREGADO");
                    System.out.println("[2]. Volver al menu...");
                    System.out.println("Ingrese una opcion:");
                    option = scanner.nextLine();
                    switch (option) {
                        case "1":
                            if (gestor.cambiarEstadoPedido(pedido.getCodigo())){
                            System.out.println("PEDIDO MODIFICADO CON EXITO");
                            } else {
                                System.out.println("[!]. Pedido no encontrado");
                            }
                            return;
                        case "2":
                            System.out.println("Volviendo al menu");
                            return; 
                        default:
                            System.out.println("[UY!]. Debe ingresar una de las opciones del menu.");
                            break;
                        }
                    break;
                case ENTREGADO:
                case CANCELADO:
                    throw new EstadoInvalidoException("No puede cambiar el estado de los Pedido ya ENTREGADOS o CANCELADOS");
                default:
                    break;
            }
        }
    }

    /*
     * public static boolean askSearchByCode(){
     * String option;
     * while (true) {
     * System.out.println("Buscar cliente por: ");
     * System.out.println("[1]. Por Codigo;");
     * System.out.println("[2]. Por Nombre;");
     * System.out.println("Ingrese el metodo de busqueda que desea usar:");
     * option = scanner.nextLine();
     * switch (option) {
     * case "1":
     * return true;
     * case "2":
     * return false;
     * default:
     * System.out.println("[UY!] Debe ingresar una opcion valida del menu.");
     * break;
     * }
     * }
     * }
     */

    public static void buscarCliente() {
        String option;
        // boolean busquedaPorCod = askSearchByCode();
        System.out.println("Ingrese el codigo o nombre del cliente: ");
        option = scanner.nextLine();
        Cliente cliente = null;
        try {
            int cod = Integer.parseInt(option);
            cliente = gestor.buscarCliente(cod);
        } catch (NumberFormatException e) {
            cliente = gestor.buscarCliente(option);
        } finally {
            if (cliente != null) {
                System.out.println(cliente);
            } else {
                System.out.println("No se encontro el cliente..");
            }
        }
        System.out.println("Presione cualquier tecla para volver al menu...");
        scanner.nextLine();
    }

    public static void showPedidos() {
        Pedido[] pedidos = gestor.getPedidos();
        if (pedidos.length <= 0){
            System.out.println("[UY!]. No hay pedidos");
        }
        for (int i = 0; i < pedidos.length; i++) {
            Pedido pedido = pedidos[i];
            Repartidor repartidor = gestor.getRepartidor(pedido.getCodigo());
            if (repartidor == null) {
                System.out.println("[" + i + "]. Pedido Cod. " + pedido.getCodigo() + ", " + "[Sin Asignar].");
            } else {
                System.out.println("[" + i + "]. Pedido Cod. " + pedido.getCodigo() + ", Costo: "
                        + pedido.calcularCosto(repartidor.getVehiculo()) + "$, Tiempo estimado: "
                        + repartidor.getVehiculo().calcularTiempoEntrega(pedido.getDistanciaKm()));
            }
        }
    }

    public static void asignarPedido() {
        String option;
        Repartidor repartidor = null;
        Pedido pedido = null;
        while (true) {
            System.out.println("---[MENU ASIGNAR PEDIDO]---");
            System.out.println("[1]. Repartidor: " + (repartidor != null ? repartidor.getNombre() : "Sin Repartidor"));
            System.out.println("[2]. Pedido: " + (pedido != null ? "Destino: " + pedido.getDestino() : "Sin pedido"));
            System.out.println("[3]. CANCELAR");
            System.out.println("[4]. GUARDAR Y ASIGNAR PEDIDO A REPARTIDOR");
            System.out.println("Seleccione el campo a modificar: ");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    repartidor = askRepartidor();
                    break;
                case "2":
                    pedido = askPedido();
                    break;
                case "3":
                    System.out.println("ASIGNACION DE PEDIDO CANCELADA");
                    return;
                case "4":
                    if (repartidor == null || pedido == null){
                        System.out.println("[UY!]. Debe rellenar todo los campos.");
                        break;
                    }
                    try {
                        if (gestor.asignarPedido(pedido.getCodigo(), repartidor.getLegajo())) {
                            System.out.println("PEDIDO ASIGNADO CON EXITO");
                            return;
                        } else {
                            System.out.println("REPARTIDOR NO DISPONIBLE");
                        }
                    } catch (CapacidadExcedidaException e) {
                        System.out.println("[UY!]. " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("[UY!] Debe ingresar una opcion valida del menu.");
                    break;
            }
        }
    }

    public static Pedido askPedido() {
        String option;
        Pedido[] pedidos = gestor.getPedidosSinAsignar();
        if (pedidos.length <= 0) {
            System.out.println("[UY!]. No hay Pedidos!");
            return null;
        }
        while (true) {
            for (int i = 0; i < pedidos.length; i++) {
                Pedido pedido = pedidos[i];
                System.out.println("[" + i + "]. Destino: " + pedido.getDestino() + ", peso: " + pedido.getPesoKg());
            }
            option = scanner.nextLine();
            try {
                int i = Integer.parseInt(option);
                if (i >= pedidos.length || i < 0) {
                    System.out.println("[UY!]. Debe seleccionar el numero del cliente de la lista.");
                } else {
                    return pedidos[i];
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor numerico de la lista.");
            }
        }
    }

    public static Repartidor askRepartidor() {
        String option;
        Repartidor[] repartidores = gestor.getRepartidorAvailable(); // should show only Repartidores available
        if (repartidores.length <= 0) {
            System.out.println("[UY!]. No hay repartidores disponibles!");
            return null;
        }
        while (true) {
            for (int i = 0; i < repartidores.length; i++) {
                Repartidor repartidor = repartidores[i];
                System.out.println("[" + i + "]. " + repartidor.getNombre());
            }
            System.out.println("Seleccione el cliente: ");
            option = scanner.nextLine();
            try {
                int i = Integer.parseInt(option);
                if (i >= repartidores.length || i < 0) {
                    System.out.println("[UY!]. Debe seleccionar el numero del cliente de la lista.");
                } else {
                    return repartidores[i];
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor numerico de la lista.");
            }
        }
    }

    public static void registrarPedido() throws AlmacenamientoLlenoException {
        String option, destino = "";
        double peso = 0, distancia = 0;
        Cliente cliente = null;
        TipoPedido tipo = TipoPedido.PEDIDO_NORMAL;
        while (true) {
            System.out.println("---[MENU PEDIDO NUEVO]---");
            System.out.println("[1]. Cliente: " + (cliente == null ? "sin Cliente" : cliente.getNombre()));
            System.out.println("[2]. Peso del Pedido(KG): " + (peso + "kg"));
            System.out.println("[3]. Distancia(KM): " + (distancia + "km"));
            System.out.println("[4]. Destino: " + (destino == "" ? "Sin destino" : destino));
            System.out.println("[5]. Tipo de Pedido: " + tipo.toString());
            System.out.println("[6]. CANCELAR;");
            System.out.println("[7]. GUARDAR.");
            System.out.println("Ingrese el campo a modificar: ");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    cliente = askCliente();
                    break;
                case "2":
                    peso = askPeso();
                    break;
                case "3":
                    distancia = askDistancia();
                    break;
                case "4":
                    destino = askDestino();
                    break;
                case "5":
                    tipo = askTipoPedido();
                    break;
                case "6":
                    System.out.println("CREACION DE PEDIDO CANCELADA");
                    return;
                case "7":
                    if (cliente == null || Validation.getDestinoStatus(destino) != "valid" || peso <= 0
                            || distancia <= 0) {
                        System.out.println("[UY!]. Debe rellenar todo los campos antes de guardar!");
                    } else {
                        try {
                            switch (tipo) {
                                case PEDIDO_NORMAL:
                                    gestor.addNewPedido(new PedidoNormal(cliente, destino, peso, distancia));
                                    break;
                                case PEDIDO_FRAGIL:
                                    gestor.addNewPedido(new PedidoFragil(cliente, destino, peso, distancia));
                                    break;
                                case PEDIDO_URGENTE:
                                    gestor.addNewPedido(new PedidoUrgente(cliente, destino, peso, distancia));
                                    break;
                            }
                            System.out.println("PEDIDO AGREGADO CON EXITO.");
                            return;
                        } catch (PedidoInvalidoException e) {
                            System.out.println("[UY!]." + e.getMessage());
                        }
                    }
                    break;
                default:
                    System.out.println("[UY!] Debe ingresar una opcion valida del menu.");
                    break;
            }
        }
    }

    public static TipoPedido askTipoPedido() {
        String option;
        while (true) {
            System.out.println("[1]. Pedido Norma;");
            System.out.println("[2]. Pedido Fragil;");
            System.out.println("[3]. Pedido Urgente;");
            System.out.println("Ingrese el tipo de pedido: ");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    return TipoPedido.PEDIDO_NORMAL;
                case "2":
                    return TipoPedido.PEDIDO_FRAGIL;
                case "3":
                    return TipoPedido.PEDIDO_URGENTE;
                default:
                    System.out.println("[UY!] Debe ingresar una opcion valida del menu.");
                    break;
            }
        }
    }

    public static Cliente askCliente() {
        String option;
        Cliente[] clientes = gestor.getClientes();
        System.out.println(clientes);
        if (clientes.length <= 0) {
            System.out.println("[UY!]. No hay clientes!");
            return null;
        }
        while (true) {
            for (int i = 0; i < clientes.length; i++) {
                Cliente cliente = clientes[i];
                System.out.println("[" + (i) + "]. " + cliente.getNombre());
            }
            System.out.println("Seleccione el codigo del cliente del Pedido: ");
            option = scanner.nextLine();
            try {
                int i = Integer.parseInt(option);
                if (i >= clientes.length || i < 0) {
                    System.out.println("[UY!]. Debe seleccionar el numero del cliente de la lista.");
                } else {
                    Cliente cliente = clientes[i];
                    return cliente;
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor numerico de la lista.");
            }
        }
    }

    public static String askDestino() {
        String option, status;
        while (true) {
            System.out.println("Ingrese el Destino del pedido:");
            option = scanner.nextLine();
            status = Validation.getDestinoStatus(option);
            if (status == "empty") {
                System.out.println("[UY!]. El destino del pedido no puede estar vacio.");
            } else if (status == "too-long") {
                System.out.println("[UY!]. El destino no puede superar los 100 caracteres de largo.");
            } else {
                return option;
            }
        }
    }

    public static double askDistancia() {
        String option;
        while (true) {
            System.out.println("Ingrese la distancia del Pedido(KM): ");
            option = scanner.nextLine();
            try {
                double distancia = Double.parseDouble(option);
                if (!Double.isFinite(distancia) || distancia <= 0) {
                    System.out.println("[UY!]. Debe ingresar un valor numerico positivo y valido");
                } else {
                    return distancia;
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor valido.");
            }
        }
    }

    public static double askPeso() {
        String option;
        while (true) {
            System.out.println("Ingrese el peso del Pedido(KG): ");
            option = scanner.nextLine();
            try {
                double peso = Double.parseDouble(option);
                if (!Double.isFinite(peso) || peso <= 0) {
                    System.out.println("[UY!]. Debe ingresar un valor numerico positivo y valido.");
                } else {
                    return peso;
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor valido.");
            }
        }
    }

    public static void registrarRepartidor() throws AlmacenamientoLlenoException {
        Repartidor repartidor = new Repartidor();
        String option;
        while (true) {
            System.out.println("---[MENU REPARTIDOR NUEVO]---");
            System.out.println(
                    "[1]. Nombre: " + (repartidor.getNombre() == null ? "Sin Nombre" : repartidor.getNombre()));
            System.out.println(
                    "[2]. Legajo: " + (repartidor.getLegajo() == null ? "Sin Legajo" : repartidor.getLegajo()));
            System.out.println("[3]. Vehiculo: " + (repartidor.getVehiculo() == null ? "Sin vehiculo"
                    : "Patente: " + repartidor.getVehiculo().getPatente()));
            System.out.println("[4]. CANCELAR;");
            System.out.println("[5]. GUARDAR");
            System.out.println("Ingrese el campo a modificar:");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    repartidor.setNombre(askNombre());
                    break;
                case "2":
                    repartidor.setLegajo(askLegajo());
                    break;
                case "3":
                    repartidor.setVehiculo(registrarVehiculo());
                    break;
                case "4":
                    System.out.println("REPARTIDOR CANCELADO");
                    return;
                case "5":
                    if (Validation.getNombreStatus(repartidor.getNombre()) != "valid"
                            || Validation.getLegajoStatus(repartidor.getLegajo()) != "valid"
                            || repartidor.getVehiculo() == null) {
                        System.out.println("[UY!]. Debe rellenar todo los campos antes de guardar!");
                    } else {
                        gestor.addNewRepartidor(repartidor);
                        System.out.println("NUEVO REPARTIDOR AGREGADO CON EXITO!");
                        return;
                    }
                    break;
                default:
                    System.out.println("[UY!] Debe ingresar una opcion valida del menu.");
                    break;
            }
        }
    }

    public static Vehiculo registrarVehiculo() {
        String patente = "", option;
        TipoVehiculo tipo = TipoVehiculo.CAMIONETA;
        double capacidad = 0, velocidad = 0;
        BigDecimal costoPorKm = BigDecimal.ZERO;
        while (true) {
            System.out.println("---[MENU VEHICULO DE REPARTIDOR]---");
            System.out.println("[1]. Tipo de vehiculo: " + tipo); // make this a little prettier
            System.out.println("[2]. Patente: " + patente);
            System.out.println("[3]. Capacidad(kg): " + capacidad);
            System.out.println("[4]. Velocidad(km/h): " + velocidad);
            System.out.println("[5]. Costo por Kilometro: " + costoPorKm);
            System.out.println("[6]. CANCELAR;");
            System.out.println("[7]. GUARDAR;");
            System.out.println("Ingrese el campo a modificar:");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    tipo = askTipoVehiculo();
                    break;
                case "2":
                    patente = askPatente();
                    break;
                case "3":
                    capacidad = askCapacidad();
                    break;
                case "4":
                    velocidad = askVelocidad();
                    break;
                case "5":
                    costoPorKm = askCostoPorKm();
                    break;
                case "6":
                    System.out.println("CREACION DE REPARTIDOR CANCELADA");
                    return null;
                case "7":
                    if (Validation.getPatenteStatus(patente) != "valid" || capacidad <= 0 || velocidad <= 0
                            || costoPorKm.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("[UY!]. Debe rellenar todo los campos antes de guardar!");
                    } else {
                        switch (tipo) {
                            case CAMIONETA:
                                return new Camioneta(patente, capacidad, velocidad, costoPorKm);
                            case MOTO:
                                return new Moto(patente, capacidad, velocidad, costoPorKm);
                            case BICICLETA:
                                return new Bicicleta(patente, capacidad, velocidad, costoPorKm);
                        }
                    }
                    break;
                default:
                    System.out.println("[UY!] Debe ingresar una opcion valida del menu.");
                    break;
            }

        }
    }

    public static BigDecimal askCostoPorKm() {
        String option;
        while (true) {
            System.out.println("Ingrese el costo por kilometro del vehiculo:");
            option = scanner.nextLine();
            try {
                BigDecimal costo = new BigDecimal(option);
                if (costo.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("[UY!]. Debe ingresar un valor numerico positivo y valido");
                } else {
                    return costo;
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor valido.");
            }
        }
    }

    public static double askVelocidad() {
        String option;
        while (true) {
            System.out.println("Ingrese la velocidad promedio del vehiculo(Km/h): ");
            option = scanner.nextLine();
            try {
                Double velocidad = Double.parseDouble(option);
                if (!Double.isFinite(velocidad) || velocidad <= 0) {
                    System.out.println("[UY!]. Debe ingresar un valor numerico positivo y valido.");
                } else {
                    return velocidad;
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor numerico valido.");
            }
        }
    }

    public static double askCapacidad() {
        String option;
        while (true) {
            System.out.println("Ingrese la capacidad de carga del vehiculo(en Kg):");
            option = scanner.nextLine();
            try {
                Double capacidad = Double.parseDouble(option);
                if (!Double.isFinite(capacidad) || capacidad <= 0) {
                    System.out.println("[UY!]. Debe ingresar un valor numerico positivo y valido.");
                } else {
                    return capacidad;
                }
            } catch (NumberFormatException e) {
                System.out.println("[UY!]. Debe ingresar un valor numerico valido.");
            }
        }
    }

    public static String askPatente() {
        String patente, status;
        while (true) {
            System.out.println("Ingrese la patente del vehiculo:");
            patente = scanner.nextLine();
            status = Validation.getPatenteStatus(patente);
            if (status == "empty") {
                System.out.println("[UY!]. La patente del vehiculo no puede estar vacia.");
            } else if (status == "too-long") {
                System.out.println("[UY!]. La patente del vehiculo no puede superar los 10 caracteres de largo.");
            } else {
                return patente;
            }
        }
    }

    public static TipoVehiculo askTipoVehiculo() {
        String option;
        while (true) {
            System.out.println("[1]. Camioneta;");
            System.out.println("[2]. Moto;");
            System.out.println("[3]. Bicicleta;");
            System.out.println("Ingrese el tipo de vehiculo: ");
            option = scanner.nextLine();
            switch (option) {
                case "1":
                    return TipoVehiculo.CAMIONETA;
                case "2":
                    return TipoVehiculo.MOTO;
                case "3":
                    return TipoVehiculo.BICICLETA;
                default:
                    System.out.println("[UY!]. Debe ingresar un tipo de vehiculo valido.");
                    break;
            }
        }
    }

    public static void registrarCliente() throws AlmacenamientoLlenoException {
        Cliente cliente = new Cliente();
        String option;
        while (true) {
            System.out.println("---[MENU CLIENTE NUEVO]---");
            System.out.println("[1]. Nombre:" + (cliente.getNombre() == null ? "Sin nombre" : cliente.getNombre()));
            System.out.println(
                    "[2]. Telefono:" + (cliente.getTelefono() == null ? "Sin telefono" : cliente.getTelefono()));
            System.out.println("[3]. Zona Ciudad:"
                    + (cliente.getZonaCiudad() == null ? "Sin Zona Ciudad" : cliente.getZonaCiudad()));
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
                    if (Validation.getNombreStatus(cliente.getNombre()) != "valid"
                            || Validation.getTelefonoStatus(cliente.getTelefono()) != "valid"
                            || Validation.getZonaCiudadStatus(cliente.getZonaCiudad()) != "valid") {
                        System.out.println("[UY!]. Debe rellenar todo los campos antes de guardar!");
                    } else {
                        gestor.addNewClient(cliente);
                        System.out.println("NUEVO CLIENTE AGREGADO CON EXITO!");
                        return;
                    }
                    break;
                default:
                    System.out.println("[UY!] Debe ingresar una opcion valida del menu.");
                    break;
            }
        }
    }

    public static String askLegajo() {
        String option, status;
        while (true) {
            System.out.println("Ingrese el legajo del repartidor: ");
            option = scanner.nextLine();
            status = Validation.getLegajoStatus(option);
            if (status == "empty") {
                System.out.println("[UY!] El legajo no puede estar vacio.");
            } else if (status == "too-long") {
                System.out.println("[UY!] El legajo no puede superar los 100 caracteres.");
            } else {
                return option;
            }
        }
    }

    public static String askNombre() {
        String option, status;
        while (true) {
            System.out.println("Ingrese el nombre:");
            option = scanner.nextLine();
            status = Validation.getNombreStatus(option);
            if (status == "empty") {
                System.out.println("[UY!] El nombre no puede estar vacio.");
            } else if (status == "too-long") {
                System.out.println("[UY!] El nombre no puede superar los 100 caracteres.");
            } else {
                return option;
            }
        }
    }

    public static String askTelefono() {
        String option, status;
        while (true) {
            System.out.println("Ingrese el numero de telefono del cliente:");
            option = scanner.nextLine();
            status = Validation.getTelefonoStatus(option);
            if (status == "empty") {
                System.out.println("[UY!] El numero de telefono del cliente no puede estar vacio.");
            } else if (status == "too-long") {
                System.out
                        .println("[UY!] El numero de telefono del cliente no debe superar los 15 caracteres de largo.");
            } else if (status == "nan") {
                System.out.println(
                        "[UY!] El numero de telefono del cliente debe contener caracteres numericos unicamente.");
            } else {
                return option;
            }
        }
    }

    public static String askZonaCiudad() {
        String option, status;
        while (true) {
            System.out.println("Ingrese la Zona de Ciudad del cliente:");
            option = scanner.nextLine();
            status = Validation.getZonaCiudadStatus(option);
            if (status == "empty") {
                System.out.println("[UY!] La zona de ciudad del cliente no puede estar vacia.");
            } else if (status == "too-long") {
                System.out.println("[UY!] La zona de ciudad del cliente no puede superar los 300 caracteres.");
            } else {
                return option;
            }
        }
    }
}
