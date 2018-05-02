package pe.edu.cibertec.util;

import org.apache.ibatis.session.SqlSession;
import pe.edu.cibertec.producer.MySqlDatabaseProducer;
import pe.edu.cibertec.producer.ProductoRepositorioProducer;
import pe.edu.cibertec.repositorio.ProductoRepositorio;
import pe.edu.cibertec.repositorio.impl.MyBatisProductoRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class ProductoRepositorioFactory {

    @Inject
    @MySqlDatabaseProducer
    private SqlSession sqlSession;

    @Produces
    @ProductoRepositorioProducer
    public ProductoRepositorio createProductoRepositorio(@MySqlDatabaseProducer SqlSession sqlSession){
        return new MyBatisProductoRepositorioImpl(sqlSession);
    }

}
