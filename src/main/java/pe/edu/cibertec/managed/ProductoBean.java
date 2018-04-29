package pe.edu.cibertec.managed;

import pe.edu.cibertec.model.ProductoModel;
import pe.edu.cibertec.service.ProductoService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
@ManagedBean(name = "productoBean")
@SessionScoped
@Transactional
public class ProductoBean {

    private ProductoModel producto = new ProductoModel();
    private String mensaje;
    private Collection<ProductoModel> productos;

    /*@Inject
    @Named("productoService")*/
    @EJB(name = "productoService")
    private ProductoService productoService;

    @PostConstruct
    public void init() {
    }

    public String principal() {
        return "home";
    }

    public String mostrar(Long codigo) {
        try {
            producto = productoService.obtener(codigo);
        } catch (Exception ex) {
        }
        return "product_detail";
    }

    public String listar(){
        try {
            productos = productoService.listar();
        } catch (Exception ex) {
        }
        return "product_list";
    }

    public  String crear(){
        try{
            producto = new ProductoModel();
        }catch (Exception ex){
        }
        return "product_create";
    }

    public String guardar(ProductoModel producto){
        try{
            producto = this.productoService.crear(producto);
            return "product_list";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String editar(Long codigo){
        try{
            producto = this.productoService.obtener(codigo);
            return "product_edit";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String actualizar(ProductoModel producto){
        try{
            if (producto == null || producto.getId() == null) {
                throw new Exception("error");
            }
            producto = this.productoService.actualizar(producto);
            if (producto != null) {
                throw new Exception("not found");
            }
            return "product_list";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public ProductoService getProductoService() {
        return productoService;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Collection<ProductoModel> getProductos() {
        return productos;
    }

    public void setProductos(Collection<ProductoModel> productos) {
        this.productos = productos;
    }

    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    public boolean isNombreInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("product_form:nombre");
        return input.isValid();
    }

    public boolean isDescripcionInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("product_form:descripcion");
        return input.isValid();
    }

    public boolean isPrecioInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("product_form:precio");
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
