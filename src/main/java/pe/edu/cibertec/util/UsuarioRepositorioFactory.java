package pe.edu.cibertec.util;

import pe.edu.cibertec.producer.MySqlDatabaseProducer;
import pe.edu.cibertec.producer.UsuarioRepositorioProducer;
import pe.edu.cibertec.repositorio.UsuarioRepositorio;
import pe.edu.cibertec.repositorio.impl.UsuarioJpaRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class UsuarioRepositorioFactory {

    @Inject
    @MySqlDatabaseProducer
    private EntityManager entityManager;

    @Produces
    @UsuarioRepositorioProducer
    public UsuarioRepositorio createUsuarioRepositorio(@MySqlDatabaseProducer EntityManager entityManager){
        return new UsuarioJpaRepositorioImpl().setEntityManager(entityManager);
    }

    /*@Produces
    @UsuarioRepositorioProducer
    public UsuarioRepositorio createUsuarioRepositorio(){
        EntityManagerFactory emf = (EntityManagerFactory) FacesContext.getCurrentInstance().getExternalContext()
                .getApplicationMap().get("emf");
        EntityManager em = emf.createEntityManager();
        return new UsuarioJpaRepositorioImpl().setEntityManager(em);
    }*/
}
