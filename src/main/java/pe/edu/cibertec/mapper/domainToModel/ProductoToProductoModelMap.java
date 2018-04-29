package pe.edu.cibertec.mapper.domainToModel;

import org.modelmapper.Condition;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.model.ProductoModel;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class ProductoToProductoModelMap extends PropertyMap<Producto, ProductoModel> {

    private Condition<Producto, ProductoModel> notNull = context -> context.getSource().getCategoria() != null;

    @Override
    protected void configure() {
        this.map().setId(source.getId());
        this.map().setNombre(source.getNombre());
        this.map().setDescripcion(source.getDescripcion());
        this.map().setImagen(source.getImagen());
        this.map().setPrecio(source.getPrecio().doubleValue());
        when(notNull).map().setCategoria(source.getCategoria().getNombre());
        when(notNull).map().setIdCategoria(source.getCategoria().getId());
    }
}
