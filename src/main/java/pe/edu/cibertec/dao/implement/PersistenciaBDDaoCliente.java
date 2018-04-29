/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import pe.edu.cibertec.dao.DaoCliente;
import pe.edu.cibertec.model.Cliente;
import pe.edu.cibertec.util.SqlConecta;

/**
 *
 * @author DENNYZ
 */
@Named("mysqlDaoCliente")
public class PersistenciaBDDaoCliente implements DaoCliente {

    private final SqlConecta conecta;

    public PersistenciaBDDaoCliente() {
        this.conecta = new SqlConecta();
    }

    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = null;

        String sql = "SELECT "
                + "codigo, "
                + "nombre, "
                + "apellido, "
                + "genero, "
                + "profesion, "
                + "numeroMovil, "
                + "publicidad, "
                + "correo "
                + "FROM cliente";
        Connection cn = conecta.connection();
        if (cn != null) {
            System.out.println("CONEXION NO NULL");
            try {
                Statement st = cn.createStatement();
                System.out.println("1 " + sql);
                ResultSet rs = st.executeQuery(sql);
                System.out.println("HAY RESULTADOS");
                clientes = new ArrayList<Cliente>();

                while (rs.next()) {
                    Cliente c = new Cliente();

                    c.setCodigo(rs.getInt(1));
                    c.setNombre(rs.getString(2));
                    c.setApellido(rs.getString(3));
                    c.setGenero(rs.getString(4).charAt(0));
                    c.setProfesion(rs.getString(5));
                    c.setNumeroMovil(rs.getString(6));
                    c.setPublicidad(rs.getBoolean(7));
                    c.setCorreo(rs.getString(8));

                    System.out.println("AGREGANDO CLIENTE");
                    clientes.add(c);
                }

            } catch (SQLException e) {
                System.out.println("mensaje de error: " + e.getMessage());
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                }
            }
        }
        System.out.println("tamaño de lista " + clientes.size());
        return clientes;
    }

    @Override
    public String insertarCliente(Cliente cliente) {
        String result = null;
        String sql = "INSERT INTO cliente("
                + "nombre,"
                + "apellido,"
                + "genero,"
                + "profesion,"
                + "numeromovil,"
                + "publicidad,"
                + "correo"
                + ") VALUES(?,?,?,?,?,?,?)";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getGenero().toString());
                ps.setString(4, cliente.getProfesion());
                ps.setString(5, cliente.getNumeroMovil());
                ps.setBoolean(6, cliente.getPublicidad());
                ps.setString(7, cliente.getCorreo());

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    throw new SQLException("0 filas afectadas");
                }

            } catch (SQLException e) {
                result = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    result = e.getMessage();
                }
            }
        }

        return result;
    }

    @Override
    public String eliminarCliente(int id) {
        String result = null;
        String sql = "DELETE FROM cliente WHERE codigo=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, id);
                int ctos = ps.executeUpdate();
            } catch (SQLException e) {
                result = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    result = e.getMessage();
                }
            }
        }
        return result;
    }

    @Override
    public Cliente obtenerCliente(Integer idCliente) {
        Cliente cliente = null;
        String sql = "SELECT "
                + "codigo,"
                + "nombre,"
                + "apellido,"
                + "genero,"
                + "profesion,"
                + "numeroMovil,"
                + "publicidad,"
                + "correo "
                + "FROM cliente "
                + "WHERE codigo=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, idCliente);
                System.out.println("sentencia " + sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    cliente = new Cliente();

                    cliente.setCodigo(rs.getInt(1));
                    cliente.setNombre(rs.getString(2));
                    cliente.setApellido(rs.getString(3));
                    cliente.setGenero(rs.getString(4).charAt(0));
                    cliente.setProfesion(rs.getString(5));
                    cliente.setNumeroMovil(rs.getString(6));
                    cliente.setPublicidad(rs.getBoolean(7));
                    cliente.setCorreo(rs.getString(8));
                }

            } catch (SQLException e) {
                System.out.println("Mensaje de error: " + e.getMessage());
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    System.out.println("Mensaje de error: " + e.getMessage());
                }
            }
        }

        return cliente;
    }

    @Override
    public String modificarCliente(Cliente cliente) {
        String result = null;
        String sql = "UPDATE cliente SET "
                + "nombre=?,"
                + "apellido=?,"
                + "genero=?,"
                + "profesion=?,"
                + "numeroMovil=?,"
                + "publicidad=?,"
                + "correo=?"
                + "WHERE codigo=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getGenero().toString());
                ps.setString(4, cliente.getProfesion());
                ps.setString(5, cliente.getNumeroMovil());
                ps.setBoolean(6, cliente.getPublicidad());
                ps.setString(7, cliente.getCorreo());
                ps.setInt(8, cliente.getCodigo());

                int ctos = ps.executeUpdate();
                
            } catch (SQLException e) {
                result = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    result = e.getMessage();
                }
            }
        }
        return result;
    }

}
