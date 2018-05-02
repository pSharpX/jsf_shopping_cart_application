package pe.edu.cibertec.util;

import org.apache.ibatis.session.SqlSession;
import pe.edu.cibertec.producer.DetalleCarritoRepositorioProducer;
import pe.edu.cibertec.producer.MySqlDatabaseProducer;
import pe.edu.cibertec.repositorio.DetalleCarritoRepositorio;
import pe.edu.cibertec.repositorio.impl.MyBatisDetalleCarritoRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by CHRISTIAN on 22/04/2018.
 */
public class DetalleCarritoRepositorioFactory {

    @Inject
    @MySqlDatabaseProducer
    private SqlSession sqlSession;

    @Produces
    @DetalleCarritoRepositorioProducer
    public DetalleCarritoRepositorio createDetalleCarritoRepositorio(@MySqlDatabaseProducer SqlSession sqlSession){
        return new MyBatisDetalleCarritoRepositorioImpl(sqlSession);
    }
}
