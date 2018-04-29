package pe.edu.cibertec.service;

import pe.edu.cibertec.model.ProductoModel;

import java.util.Collection;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
public interface ProductoService {
    Collection<ProductoModel> listar();
    Collection<ProductoModel> listarPorCategoria(Long idCategoria);
    ProductoModel obtener(Long id);
    ProductoModel crear(ProductoModel producto);
    ProductoModel actualizar(ProductoModel producto);
    boolean eliminar(ProductoModel producto);
    default boolean eliminar(Long id){
        return eliminar(obtener(id));
    }
}
