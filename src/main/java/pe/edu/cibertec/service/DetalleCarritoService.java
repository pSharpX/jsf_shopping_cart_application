package pe.edu.cibertec.service;

import pe.edu.cibertec.model.DetalleCarritoModel;

import java.util.Collection;
import java.util.Date;

/**
 * Created by CHRISTIAN on 22/04/2018.
 */
public interface DetalleCarritoService {
    Collection<DetalleCarritoModel> listar();
    Collection<DetalleCarritoModel> listarPorCarrito(Long idCarrito);
    DetalleCarritoModel obtener(Long id);
    DetalleCarritoModel crear(DetalleCarritoModel carritoModel);
    DetalleCarritoModel actualizar(DetalleCarritoModel carritoModel);
    boolean eliminar(DetalleCarritoModel carritoModel);
    default boolean eliminar(Long id){
        return eliminar(obtener(id));
    }
}
