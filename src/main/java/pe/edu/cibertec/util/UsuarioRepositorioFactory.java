package pe.edu.cibertec.util;

import org.apache.ibatis.session.SqlSession;
import pe.edu.cibertec.producer.MySqlDatabaseProducer;
import pe.edu.cibertec.producer.UsuarioRepositorioProducer;
import pe.edu.cibertec.repositorio.UsuarioRepositorio;
import pe.edu.cibertec.repositorio.impl.MyBatisUsuarioRepositorioImpl;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class UsuarioRepositorioFactory {

    @Inject
    @MySqlDatabaseProducer
    private SqlSession sqlSession;

    @Produces
    @UsuarioRepositorioProducer
    public UsuarioRepositorio createUsuarioRepositorio(@MySqlDatabaseProducer SqlSession sqlSession){
        return new MyBatisUsuarioRepositorioImpl(sqlSession);
    }
}
