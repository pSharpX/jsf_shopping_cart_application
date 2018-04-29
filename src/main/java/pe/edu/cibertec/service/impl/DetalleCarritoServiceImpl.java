package pe.edu.cibertec.service.impl;

import org.modelmapper.ModelMapper;
import pe.edu.cibertec.model.DetalleCarritoModel;
import pe.edu.cibertec.producer.DetalleCarritoRepositorioProducer;
import pe.edu.cibertec.producer.ModelMapperProducer;
import pe.edu.cibertec.repositorio.DetalleCarritoRepositorio;
import pe.edu.cibertec.service.DetalleCarritoService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by CHRISTIAN on 22/04/2018.
 */
@Named("detalleCarritoService")
public class DetalleCarritoServiceImpl implements DetalleCarritoService {

    @Inject
    @ModelMapperProducer
    private ModelMapper mapper;

    @Inject
    @DetalleCarritoRepositorioProducer
    private DetalleCarritoRepositorio detalleCarritoRepositorio;

    /*@Inject
    @PersistenceContext(unitName = "labjpa")
    private EntityManager entityManager;*/

    public DetalleCarritoServiceImpl(){
    }

    @PostConstruct
    public void init(){
    }
    @Override
    public Collection<DetalleCarritoModel> listar() {
        return null;
    }

    @Override
    public Collection<DetalleCarritoModel> listarPorCarrito(Long idCarrito) {
        return null;
    }

    @Override
    public DetalleCarritoModel obtener(Long id) {
        return null;
    }

    @Override
    public DetalleCarritoModel crear(DetalleCarritoModel carritoModel) {
        return null;
    }

    @Override
    public DetalleCarritoModel actualizar(DetalleCarritoModel carritoModel) {
        return null;
    }

    @Override
    public boolean eliminar(DetalleCarritoModel carritoModel) {
        return false;
    }
}
