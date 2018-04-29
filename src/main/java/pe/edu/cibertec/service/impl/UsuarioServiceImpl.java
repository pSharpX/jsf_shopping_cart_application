package pe.edu.cibertec.service.impl;

import org.modelmapper.ModelMapper;
import pe.edu.cibertec.dominio.Usuario;
import pe.edu.cibertec.mapper.domainToModel.UsuarioToUsuarioModelMap;
import pe.edu.cibertec.mapper.modelToDomain.UsuarioModelToUsuarioMap;
import pe.edu.cibertec.producer.ModelMapperProducer;
import pe.edu.cibertec.producer.UsuarioRepositorioProducer;
import pe.edu.cibertec.repositorio.UsuarioRepositorio;
import pe.edu.cibertec.service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 * Created by CHRISTIAN on 13/04/2018.
 */
@Named("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    @ModelMapperProducer
    private ModelMapper mapper;

    @Inject
    @UsuarioRepositorioProducer
    private UsuarioRepositorio usuarioRepositorio;

    /*@Inject
    @PersistenceContext(unitName = "labjpa")
    private EntityManager entityManager;*/

    public UsuarioServiceImpl(){
    }

    @PostConstruct
    public void init(){
        this.mapper.addMappings(new UsuarioModelToUsuarioMap());
        this.mapper.addMappings(new UsuarioToUsuarioModelMap());
    }

    @Override
    public boolean existe(String username, String password) {
        try {
            Usuario usuario = this.usuarioRepositorio.buscar(username, password);
            if(usuario == null)
                return false;
            return true;
        }catch (NoResultException ex){
            return false;
        }
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UsuarioRepositorio getUsuarioRepositorio() {
        return usuarioRepositorio;
    }

    public void setUsuarioRepositorio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }
}
