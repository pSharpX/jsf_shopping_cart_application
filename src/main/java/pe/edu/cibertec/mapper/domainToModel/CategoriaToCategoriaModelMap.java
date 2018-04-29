package pe.edu.cibertec.mapper.domainToModel;

import org.modelmapper.PropertyMap;
import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.model.CategoriaModel;

/**
 * Created by CHRISTIAN on 17/04/2018.
 */
public class CategoriaToCategoriaModelMap extends PropertyMap<Categoria, CategoriaModel> {
    @Override
    protected void configure() {
        this.map().setId(source.getId());
        this.map().setNombre(source.getNombre());
    }
}
