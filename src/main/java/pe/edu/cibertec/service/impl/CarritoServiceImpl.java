package pe.edu.cibertec.service.impl;

import org.modelmapper.ModelMapper;
import pe.edu.cibertec.model.CarritoModel;
import pe.edu.cibertec.producer.CarritoRepositorioProducer;
import pe.edu.cibertec.producer.ModelMapperProducer;
import pe.edu.cibertec.repositorio.CarritoCompraRepositorio;
import pe.edu.cibertec.service.CarritoService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Date;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
@Named("carritoService")
public class CarritoServiceImpl implements CarritoService{

    @Inject
    @ModelMapperProducer
    private ModelMapper mapper;

    @Inject
    @CarritoRepositorioProducer
    private CarritoCompraRepositorio carritoCompraRepositorio;

    /*@Inject
    @PersistenceContext(unitName = "labjpa")
    private EntityManager entityManager;*/

    public CarritoServiceImpl(){
    }

    @PostConstruct
    public void init(){
    }

    @Override
    public Collection<CarritoModel> listar() {
        return null;
    }

    @Override
    public Collection<CarritoModel> listarPorFecha(Date fecha) {
        return null;
    }

    @Override
    public Collection<CarritoModel> listarPorPeriodo(Date inicio, Date fin) {
        return null;
    }

    @Override
    public CarritoModel obtener(Long id) {
        return null;
    }

    @Override
    public CarritoModel crear(CarritoModel carritoModel) {
        return null;
    }

    @Override
    public CarritoModel actualizar(CarritoModel carritoModel) {
        return null;
    }

    @Override
    public boolean eliminar(CarritoModel carritoModel) {
        return false;
    }
}
