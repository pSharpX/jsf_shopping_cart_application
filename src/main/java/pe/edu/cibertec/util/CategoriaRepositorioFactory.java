package pe.edu.cibertec.util;

import org.apache.ibatis.session.SqlSession;
import pe.edu.cibertec.producer.CategoriaRepositorioProducer;
import pe.edu.cibertec.producer.MySqlDatabaseProducer;
import pe.edu.cibertec.repositorio.CategoriaRepositorio;
import pe.edu.cibertec.repositorio.impl.MyBatisCategoriaRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by CHRISTIAN on 17/04/2018.
 */
public class CategoriaRepositorioFactory {

    @Inject
    @MySqlDatabaseProducer
    private SqlSession sqlSession;

    @Produces
    @CategoriaRepositorioProducer
    public CategoriaRepositorio createCategoriaRepositorio(@MySqlDatabaseProducer SqlSession sqlSession){
        return new MyBatisCategoriaRepositorioImpl(sqlSession);
    }
}
