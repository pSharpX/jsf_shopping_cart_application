package pe.edu.cibertec.mapper.modelToDomain;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.model.ProductoModel;

import java.math.BigDecimal;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class ProductoModelToProductoMap extends PropertyMap<ProductoModel, Producto> {

    private Converter<ProductoModel, Categoria> toCategoria = context -> {
        Categoria categoria = new Categoria();
        categoria.setId(context.getSource().getIdCategoria());
        categoria.setNombre(context.getSource().getCategoria());
        return categoria;
    };

    @Override
    protected void configure() {
        this.map().setId(source.getId());
        this.map().setNombre(source.getNombre());
        this.map().setDescripcion(source.getDescripcion());
        this.map().setImagen(source.getImagen());
        this.map().setPrecio(new BigDecimal(source.getPrecio()));
        using(toCategoria).map(source).setCategoria(null);
    }
}
