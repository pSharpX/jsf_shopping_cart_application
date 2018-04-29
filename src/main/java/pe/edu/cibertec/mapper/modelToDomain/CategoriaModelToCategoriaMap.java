package pe.edu.cibertec.mapper.modelToDomain;

import org.modelmapper.PropertyMap;
import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.model.CategoriaModel;

/**
 * Created by CHRISTIAN on 17/04/2018.
 */
public class CategoriaModelToCategoriaMap extends PropertyMap<CategoriaModel, Categoria> {
    @Override
    protected void configure() {
        this.map().setId(source.getId());
        this.map().setNombre(source.getNombre());
    }
}
