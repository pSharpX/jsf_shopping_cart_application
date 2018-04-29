package pe.edu.cibertec.util;

import pe.edu.cibertec.producer.DetalleCarritoRepositorioProducer;
import pe.edu.cibertec.repositorio.DetalleCarritoRepositorio;
import pe.edu.cibertec.repositorio.impl.DetalleCarritoJpaRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by CHRISTIAN on 22/04/2018.
 */
public class DetalleCarritoRepositorioFactory {

    @Produces
    @DetalleCarritoRepositorioProducer
    public DetalleCarritoRepositorio createDetalleCarritoRepositorio(){
        EntityManagerFactory emf = (EntityManagerFactory) FacesContext.getCurrentInstance().getExternalContext()
                .getApplicationMap().get("emf");
        EntityManager em = emf.createEntityManager();
        return new DetalleCarritoJpaRepositorioImpl().setEntityManager(em);
    }
}
