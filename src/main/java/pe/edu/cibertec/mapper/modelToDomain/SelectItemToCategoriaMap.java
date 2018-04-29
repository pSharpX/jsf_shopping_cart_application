package pe.edu.cibertec.mapper.modelToDomain;

import org.modelmapper.Condition;
import org.modelmapper.PropertyMap;
import pe.edu.cibertec.dominio.Categoria;

import javax.faces.model.SelectItem;

/**
 * Created by CHRISTIAN on 20/04/2018.
 */
public class SelectItemToCategoriaMap extends PropertyMap<SelectItem, Categoria> {

    private Condition<SelectItem, Categoria> notNull = context -> context.getSource() != null;

    @Override
    protected void configure() {
        this.when(notNull).map().setNombre(source.getLabel());
        this.when(notNull).map().setId(Long.parseLong(source.getValue().toString()));
    }
}
