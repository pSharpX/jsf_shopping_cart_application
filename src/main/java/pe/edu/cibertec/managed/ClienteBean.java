/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import pe.edu.cibertec.dao.DaoCliente;
import pe.edu.cibertec.model.Cliente;

/**
 *
 * @author Java-LM
 */
// @Named("clienteBean")
@ManagedBean(name = "clienteBean")
@SessionScoped
public class ClienteBean {

	private static final String HTTP_GET = "GET";
	private static final String HTTP_POST = "POST";
	
	private static final String ERROR_TITLE_KEY = "operacion.resultado.error";
	private static final String ERROR_MESSAGE_KEY = "operacion.resultado.error.mensaje";
	
	private Cliente cliente = new Cliente();
	private String mensaje;
	private List<Cliente> clientes;

	@Inject
	@Named("mockDaoCliente")
	private DaoCliente dao;

	@PostConstruct
	public void init() {
	}

	public String principal() {
		return "home";
	}

	public String mostrar(Integer codigo) {
		try {
			cliente = dao.obtenerCliente(codigo);
		} catch (Exception ex) {
		}
		return "customer_detail";
	}

	public String crear() {
		cliente = new Cliente();
		return "customer_create";
	}

	public String guardar(Cliente cliente) {
		try {
			if (cliente == null) {
				throw new Exception("error");
			}
			String errorMessage = this.dao.insertarCliente(cliente);
			if (errorMessage != null && !"".equals(errorMessage)) {
				throw new Exception(errorMessage);
			}
			return "result";			
		} catch (Exception ex) {
			ResourceBundle rb = ResourceBundle.getBundle("pe.edu.cibertec.recursos.mensajes",
	                FacesContext.getCurrentInstance().getViewRoot().getLocale());
			
			FacesMessage fm = new FacesMessage(
	                FacesMessage.SEVERITY_ERROR,
	                rb.getString(ERROR_MESSAGE_KEY),
	                rb.getString(ERROR_MESSAGE_KEY));
	        FacesContext.getCurrentInstance().addMessage(null, fm);
	        return null;
		}		
	}

	public String listar() {
		try {
			clientes = dao.listarCliente();
		} catch (Exception ex) {
		}
		return "customer_list";
	}

	public String editar(Integer codigo) {
		try {
			cliente = dao.obtenerCliente(codigo);
		} catch (Exception ex) {
		}
		return "customer_edit";
	}

	public String actualizar(Cliente cliente) {
		try {
			if (cliente == null || cliente.getCodigo() == null) {
				throw new Exception("error");
			}
			String errorMessage = this.dao.modificarCliente(cliente);
			if (errorMessage != null && !"".equals(errorMessage)) {
				throw new Exception(errorMessage);
			}
		} catch (Exception ex) {
		}
		return "result";
	}

	public String eliminar(Integer codigo) {
		String action = "customer_delete";
		try {
			cliente = dao.obtenerCliente(codigo);
			action = "customer_delete";
		} catch (Exception ex) {
			action = "customer_delete";
		}
		return action;
	}

	public String confirmarEliminar(Integer codigo) {
		String action = "customer_list";
		try {
			String request_method = this.getRequest().getMethod();
			if (HTTP_POST.equalsIgnoreCase(request_method)) {
				String errorMessage = this.dao.eliminarCliente(codigo);
				if (errorMessage != null && "".equals(errorMessage)) {
					throw new Exception(errorMessage);
				}
				action = "customer_list";
			}
		} catch (Exception ex) {
			cliente = dao.obtenerCliente(codigo);
			action = "customer_delete";
		}
		return action;
	}

	public void mensajeProfesion(ValueChangeEvent e) {
		String valor = (String) e.getNewValue();
		if ("001".equals(valor)) {
			setMensaje("Tenemos los mejores cursos de arquitectura");
		}
		if ("002".equals(valor)) {
			setMensaje("Grandes eventos esperan por ti");
		}
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public DaoCliente getDao() {
		return dao;
	}

	public void setDao(DaoCliente dao) {
		this.dao = dao;
	}

	public boolean isNombreInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:nombre");
		return input.isValid();
	}

	public boolean isApellidoInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:apellido");
		return input.isValid();
	}

	public boolean isNumeroMovilInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:numeroMovil");
		return input.isValid();
	}

	public boolean isNumeroDocumentoInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:numeroDocumento");
		return input.isValid();
	}

	public boolean isFechaNacimientoInputValid() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIInput input = (UIInput) context.getViewRoot().findComponent("customer_form:fechaNacimiento");
		return input.isValid();
	}

	private HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		if (request == null) {
			throw new RuntimeException("Sorry. Got a null request from faces context");
		}
		return request;
	}
}
