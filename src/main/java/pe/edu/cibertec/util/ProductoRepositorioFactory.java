package pe.edu.cibertec.util;

import pe.edu.cibertec.producer.MySqlDatabaseProducer;
import pe.edu.cibertec.producer.ProductoRepositorioProducer;
import pe.edu.cibertec.repositorio.ProductoRepositorio;
import pe.edu.cibertec.repositorio.impl.ProductoJpaRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class ProductoRepositorioFactory {

    @Inject
    @MySqlDatabaseProducer
    private EntityManager entityManager;

    @Produces
    @ProductoRepositorioProducer
    public ProductoRepositorio createProductoRepositorio(@MySqlDatabaseProducer EntityManager entityManager){
        return new ProductoJpaRepositorioImpl().setEntityManager(entityManager);
    }

}
