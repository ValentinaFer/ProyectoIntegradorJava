package app;

import models.Cliente;
import models.Pedido;

//"Contiene los arreglos y las operaciones del sistema"
public class CityLink {
    private Cliente[] clientes = new Cliente[100];
    public Pedido[] pedidos = new Pedido[100];
    private int clienteLength = 0;
    private int nextClienteId = 1;

    public boolean addNewClient(Cliente cliente){
        if (clienteLength >= clientes.length){
            return false;
        }
        cliente.setCodigo(nextClienteId++);
        clientes[clienteLength++] = cliente;
        return true; 
    }
}
