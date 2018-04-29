package pe.edu.cibertec.service.impl;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.mapper.domainToModel.CategoriaToCategoriaModelMap;
import pe.edu.cibertec.mapper.domainToModel.CategoriaToSelectItemMap;
import pe.edu.cibertec.mapper.modelToDomain.CategoriaModelToCategoriaMap;
import pe.edu.cibertec.mapper.modelToDomain.SelectItemToCategoriaMap;
import pe.edu.cibertec.model.CategoriaModel;
import pe.edu.cibertec.producer.CategoriaRepositorioProducer;
import pe.edu.cibertec.producer.ModelMapperProducer;
import pe.edu.cibertec.repositorio.CategoriaRepositorio;
import pe.edu.cibertec.service.CategoriaService;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by CHRISTIAN on 17/04/2018.
 */
@Named("categoriaService")
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    @ModelMapperProducer
    private ModelMapper mapper;

    @Inject
    @CategoriaRepositorioProducer
    private CategoriaRepositorio categoriaRepositorio;

    /*@Inject
    @PersistenceContext(unitName = "labjpa")
    private EntityManager entityManager;*/

    public CategoriaServiceImpl(){
    }

    @PostConstruct
    public void init(){
        this.mapper.addMappings(new CategoriaModelToCategoriaMap());
        this.mapper.addMappings(new CategoriaToCategoriaModelMap());
        //this.mapper.addMappings(new SelectItemToCategoriaMap());
        //this.mapper.addMappings(new CategoriaToSelectItemMap());
        this.mapper.validate();
    }

    @Override
    public Collection<CategoriaModel> listar() {
        List<Categoria> _categorias = this.categoriaRepositorio.obtenerTodos();
        if(_categorias == null || _categorias.size() == 0)
            return null;
        Type type = new TypeToken<List<CategoriaModel>>(){}.getType();
        List<CategoriaModel> _categoriaModels = this.mapper.map(_categorias, type);
        return _categoriaModels;
    }

    @Override
    public CategoriaModel obtener(Long id) {
        Categoria categoria = this.categoriaRepositorio.buscar(id);
        return this.mapper.map(categoria, CategoriaModel.class);
    }

    @Override
    public Collection<SelectItem> combo() {
        List<Categoria> _categorias = this.categoriaRepositorio.obtenerTodos();
        if(_categorias == null || _categorias.size() == 0)
            return null;
        Type type = new TypeToken<List<SelectItem>>(){}.getType();
        List<SelectItem> items = this.mapper.map(_categorias, type);
        return  items;
    }
}
