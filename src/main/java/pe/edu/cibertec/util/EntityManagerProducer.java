package pe.edu.cibertec.util;

import pe.edu.cibertec.producer.MySqlDatabaseProducer;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * Created by CHRISTIAN on 22/04/2018.
 */
public class EntityManagerProducer {

    @Produces
    @MySqlDatabaseProducer
    @PersistenceContext(unitName = "labjpa")
    public EntityManager entityManager;

    /*@Produces
    @MySqlDatabaseProducer
    public EntityManager createEntityManager(){
        EntityManagerFactory emf = (EntityManagerFactory) FacesContext.getCurrentInstance().getExternalContext()
                .getApplicationMap().get("emf");
        EntityManager em = emf.createEntityManager();
        return em;
    }*/

    /*public void close(@Disposes @MySqlDatabaseProducer EntityManager entityManager){
        entityManager.close();
    }*/

}
