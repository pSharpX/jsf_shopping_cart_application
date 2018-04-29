package pe.edu.cibertec.mapper;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.dominio.Usuario;
import pe.edu.cibertec.model.ProductoModel;
import pe.edu.cibertec.model.UsuarioModel;

import java.math.BigDecimal;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
public class ModelToDomainMapper {

    private PropertyMap<ProductoModel, Producto> fromProductoModelToProductoMap;
    private PropertyMap<UsuarioModel, Usuario> fromUsuarioModelToUsuarioMap;

    private Converter<ProductoModel, Categoria> toCategoria = context -> {
        Categoria categoria = new Categoria();
        categoria.setId(context.getSource().getIdCategoria());
        categoria.setNombre(context.getSource().getCategoria());
        return categoria;
    };

    public ModelToDomainMapper(){
        this.fromProductoModelToProductoMap = new PropertyMap<ProductoModel, Producto>() {
            @Override
            protected void configure() {
                this.map().setId(source.getId());
                this.map().setNombre(source.getNombre());
                this.map().setDescripcion(source.getDescripcion());
                this.map().setImagen(source.getImagen());
                this.map().setPrecio(new BigDecimal(source.getPrecio()));
                using(toCategoria).map(source).setCategoria(null);
            }
        };
        this.fromUsuarioModelToUsuarioMap = new PropertyMap<UsuarioModel, Usuario>() {
            @Override
            protected void configure() {
                this.skip().setId(null);
                this.map().setNombre(source.getNombre());
                this.map().setApellido(source.getApellido());
                this.skip().setFechaNacimiento(null);
            }
        };
    }

    public PropertyMap<ProductoModel, Producto> getFromProductoModelToProductoMap() {
        return fromProductoModelToProductoMap;
    }

    public PropertyMap<UsuarioModel, Usuario> getFromUsuarioModelToUsuarioMap() {
        return fromUsuarioModelToUsuarioMap;
    }
}
