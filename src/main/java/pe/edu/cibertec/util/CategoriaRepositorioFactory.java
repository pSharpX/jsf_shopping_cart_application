package pe.edu.cibertec.util;

import pe.edu.cibertec.producer.CategoriaRepositorioProducer;
import pe.edu.cibertec.repositorio.CategoriaRepositorio;
import pe.edu.cibertec.repositorio.impl.CategoriaJpaRepositorioImpl;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by CHRISTIAN on 17/04/2018.
 */
public class CategoriaRepositorioFactory {

    @Produces
    @CategoriaRepositorioProducer
    public CategoriaRepositorio createCategoriaRepositorio(){
        EntityManagerFactory emf = (EntityManagerFactory) FacesContext.getCurrentInstance().getExternalContext()
                .getApplicationMap().get("emf");
        EntityManager em = emf.createEntityManager();
        return new CategoriaJpaRepositorioImpl().setEntityManager(em);
    }
}
