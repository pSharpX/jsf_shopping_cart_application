package pe.edu.cibertec.util;

import org.apache.ibatis.session.SqlSession;
import pe.edu.cibertec.producer.CarritoRepositorioProducer;
import pe.edu.cibertec.producer.MySqlDatabaseProducer;
import pe.edu.cibertec.repositorio.CarritoCompraRepositorio;
import pe.edu.cibertec.repositorio.impl.MyBatisCarritoRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by CHRISTIAN on 22/04/2018.
 */
public class CarritoRepositorioFactory {

    @Inject
    @MySqlDatabaseProducer
    private SqlSession sqlSession;

    @Produces
    @CarritoRepositorioProducer
    public CarritoCompraRepositorio createCarritoRepositorio(@MySqlDatabaseProducer SqlSession sqlSession){
        return new MyBatisCarritoRepositorioImpl(sqlSession);
    }
}
