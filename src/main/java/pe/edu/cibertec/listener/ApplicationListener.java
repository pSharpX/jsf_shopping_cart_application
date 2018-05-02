package pe.edu.cibertec.listener;

import org.modelmapper.ModelMapper;
import pe.edu.cibertec.mapper.DomainToModelMapper;
import pe.edu.cibertec.mapper.ModelToDomainMapper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ModelMapper mapper = this.getModelMapper();
        sce.getServletContext().setAttribute("mapper", mapper);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private ModelMapper getModelMapper(){
        ModelMapper mapper = new ModelMapper();
        DomainToModelMapper domainToModelMapper = new DomainToModelMapper();
        ModelToDomainMapper modelToDomainMapper = new ModelToDomainMapper();
        mapper.addMappings(domainToModelMapper.getFromProductoToProductoModelMap());
        mapper.addMappings(domainToModelMapper.getFromUsuarioToUsuarioModelMap());
        mapper.addMappings(modelToDomainMapper.getFromProductoModelToProductoMap());
        mapper.addMappings(modelToDomainMapper.getFromUsuarioModelToUsuarioMap());
        return mapper;
    }
}
