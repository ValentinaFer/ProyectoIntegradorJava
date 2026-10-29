package app;

import models.Cliente;

//"Contiene los arreglos y las operaciones del sistema"
public class CityLink {
    private Cliente[] clientes = new Cliente[100];
    private int clienteLength = 0;


    public boolean addNewClient(Cliente cliente){
        if (clienteLength >= clientes.length){
            return false;
        }
        clientes[clienteLength++] = cliente;
        return true; 
    }
}
