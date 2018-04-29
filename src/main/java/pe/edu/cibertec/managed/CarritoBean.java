package pe.edu.cibertec.managed;

import pe.edu.cibertec.model.CarritoModel;
import pe.edu.cibertec.model.DetalleCarritoModel;
import pe.edu.cibertec.model.ProductoModel;
import pe.edu.cibertec.service.CarritoService;
import pe.edu.cibertec.service.DetalleCarritoService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
@ManagedBean(name = "carritoBean")
@SessionScoped
public class CarritoBean {

    private String mensaje;
    private CarritoModel carritoModel = new CarritoModel();
    private DetalleCarritoModel detalleCarritoModel = new DetalleCarritoModel();
    private List<DetalleCarritoModel> detalleCarritoModels;

    @Inject
    @Named("carritoService")
    private CarritoService carritoService;

    @Inject
    @Named("detalleCarritoService")
    private DetalleCarritoService detalleCarritoService;

    /*@Inject
    @Named("productoService")*/
    @EJB(name = "productoService")
    private ProductoService productoService;

    @PostConstruct
    public void init() {
        this.carritoModel.setUsuario(this.getUsuario());
        detalleCarritoModels = new ArrayList<>();
    }

    public String listar(){
        try{
            if(detalleCarritoModels != null && detalleCarritoModels.size() > 0){
                this.carritoModel.setDetalleCarrito(this.detalleCarritoModels);
                ToDoubleFunction<DetalleCarritoModel> calcSubTotal = d -> d.getPrecioUnitario() * d.getCantidad();
                this.carritoModel.setTotal(this.carritoModel.getDetalleCarrito().stream().mapToDouble(calcSubTotal).sum());
            }
            return "cart_list";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String seleccionarItem(Long idProducto){
        try{
            ProductoModel productoModel = this.productoService.obtener(idProducto);
            if(productoModel == null)
                throw new Exception("Product not found");
            detalleCarritoModel.setIdProducto(productoModel.getId());
            detalleCarritoModel.setProducto(productoModel.getNombre());
            detalleCarritoModel.setCantidad(1);
            detalleCarritoModel.setPrecioUnitario(productoModel.getPrecio());
            return "cart_add";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String checkout(){
        try{
            return "cart_checkout";
        }catch (Exception ex){
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    ex.getMessage(),
                    ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }

    public String save(){
        try{
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

    public String agregarItem(DetalleCarritoModel detalleCarritoModel){
        try {
            addItemToShoppingCart(detalleCarritoModel);
            this.detalleCarritoModel = new DetalleCarritoModel();
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

    private void addItemToShoppingCart(DetalleCarritoModel detalleCarritoModel){
        Predicate<DetalleCarritoModel> predicate = (d) -> d.getIdProducto() == detalleCarritoModel.getIdProducto();
        if(this.detalleCarritoModels.stream().anyMatch(predicate)){
            Consumer<DetalleCarritoModel> consumer = (DetalleCarritoModel d) -> {
                if(d.getIdProducto() == detalleCarritoModel.getIdProducto()){
                    d.setCantidad(d.getCantidad() + detalleCarritoModel.getCantidad());
                }
            };
            this.detalleCarritoModels.forEach(consumer);
        }else{
            this.detalleCarritoModels.add(detalleCarritoModel);
        }
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public CarritoModel getCarritoModel() {
        return carritoModel;
    }

    public void setCarritoModel(CarritoModel carritoModel) {
        this.carritoModel = carritoModel;
    }

    public List<DetalleCarritoModel> getDetalleCarritoModels() {
        return detalleCarritoModels;
    }

    public void setDetalleCarritoModels(List<DetalleCarritoModel> detalleCarritoModels) {
        this.detalleCarritoModels = detalleCarritoModels;
    }

    public CarritoService getCarritoService() {
        return carritoService;
    }

    public void setCarritoService(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    public DetalleCarritoService getDetalleCarritoService() {
        return detalleCarritoService;
    }

    public void setDetalleCarritoService(DetalleCarritoService detalleCarritoService) {
        this.detalleCarritoService = detalleCarritoService;
    }

    public DetalleCarritoModel getDetalleCarritoModel() {
        return detalleCarritoModel;
    }

    public void setDetalleCarritoModel(DetalleCarritoModel detalleCarritoModel) {
        this.detalleCarritoModel = detalleCarritoModel;
    }

    public ProductoService getProductoService() {
        return productoService;
    }

    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    private String getUsuario(){
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        Object object = session.get("login");
        if(object == null)
            return null;
        LoginBean loginBean = (LoginBean)object;
        return  loginBean.getUsername();
    }

    public boolean isCantidadInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("cart_form:cantidad");
        return input.isValid();
    }

    public boolean isPrecioInputValid() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent("cart_form:precio");
        return input.isValid();
    }


}
