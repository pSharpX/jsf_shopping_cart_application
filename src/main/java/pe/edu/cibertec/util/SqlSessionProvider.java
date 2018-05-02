package pe.edu.cibertec.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;
import pe.edu.cibertec.producer.MySqlDatabaseProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by CHRISTIAN on 22/04/2018.
 */
public class SqlSessionProvider {

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    @MySqlDatabaseProducer
    public SqlSessionFactory createSqlSessionFactory()throws IOException{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
