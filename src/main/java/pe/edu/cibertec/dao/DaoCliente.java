/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.dao;

import java.util.List;
import pe.edu.cibertec.model.Cliente;

/**
 *
 * @author DENNYZ
 */
public interface DaoCliente {

    public List<Cliente> listarCliente();

    public String insertarCliente(Cliente cliente);

    public String eliminarCliente(int id);

    public Cliente obtenerCliente(Integer idCliente);

    public String modificarCliente(Cliente cliente);
}
