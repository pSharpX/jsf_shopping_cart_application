package pe.edu.cibertec.service;

import pe.edu.cibertec.model.CarritoModel;

import java.util.Collection;
import java.util.Date;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
public interface CarritoService {
    Collection<CarritoModel> listar();
    Collection<CarritoModel> listarPorFecha(Date fecha);
    Collection<CarritoModel> listarPorPeriodo(Date inicio, Date fin);
    CarritoModel obtener(Long id);
    CarritoModel crear(CarritoModel carritoModel);
    CarritoModel actualizar(CarritoModel carritoModel);
    boolean eliminar(CarritoModel carritoModel);
    default boolean eliminar(Long id){
        return eliminar(obtener(id));
    }
}
