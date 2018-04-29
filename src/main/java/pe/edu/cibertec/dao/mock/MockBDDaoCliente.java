package pe.edu.cibertec.dao.mock;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import pe.edu.cibertec.dao.DaoCliente;
import pe.edu.cibertec.model.Cliente;
import pe.edu.cibertec.model.builder.ClienteBuilder;

@Named("mockDaoCliente")
public class MockBDDaoCliente implements DaoCliente {

	@Override
	public List<Cliente> listarCliente() {
		// TODO Auto-generated method stub
		List<Cliente> clientes = new ArrayList<Cliente>();
		for (int i = 0; i < 20; i++) {
			clientes.add(new ClienteBuilder()
					.addCodigo(i + 1)
					.addNombre(String.format("Nombre %d", i))
					.addApellido(String.format("Apellido %d", i))
					.addGenero('M')
					.addCorreo(String.format("correo%d@gmail.com", i))
					.addNumeroMovil(String.format("9726157%d", i))
					.addOperadorMovil(String.format("Operador %d", i))
					.addProfesion(String.format("Profesion %d", i))
					.addPublicidad(true)
					.build());
		}		
		return clientes;
	}

	@Override
	public String insertarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String eliminarCliente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente obtenerCliente(Integer idCliente) {
		// TODO Auto-generated method stub
		return new ClienteBuilder().addCodigo(1).addNombre(String.format("Nombre 1"))
				.addApellido(String.format("Apellido 1")).addGenero('M').addCorreo(String.format("correo1@gmail.com"))
				.addNumeroMovil(String.format("97261571")).addOperadorMovil(String.format("Operador 1"))
				.addProfesion(String.format("Profesion 1")).addPublicidad(true).build();
	}

	@Override
	public String modificarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
