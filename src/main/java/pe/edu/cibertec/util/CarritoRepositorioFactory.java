package pe.edu.cibertec.util;

import pe.edu.cibertec.producer.CarritoRepositorioProducer;
import pe.edu.cibertec.repositorio.CarritoCompraRepositorio;
import pe.edu.cibertec.repositorio.impl.CarritoCompraJpaRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by CHRISTIAN on 22/04/2018.
 */
public class CarritoRepositorioFactory {

    @Produces
    @CarritoRepositorioProducer
    public CarritoCompraRepositorio createCarritoRepositorio(){
        EntityManagerFactory emf = (EntityManagerFactory) FacesContext.getCurrentInstance().getExternalContext()
                .getApplicationMap().get("emf");
        EntityManager em = emf.createEntityManager();
        return new CarritoCompraJpaRepositorioImpl().setEntityManager(em);
    }
}
