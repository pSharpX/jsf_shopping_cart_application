package pe.edu.cibertec.mapper.domainToModel;

import org.modelmapper.Condition;
import org.modelmapper.PropertyMap;
import pe.edu.cibertec.dominio.Categoria;

import javax.faces.model.SelectItem;

/**
 * Created by CHRISTIAN on 20/04/2018.
 */
public class CategoriaToSelectItemMap extends PropertyMap<Categoria, SelectItem> {

    private Condition<Categoria, SelectItem> notNull = context -> context.getSource() != null;

    @Override
    protected void configure() {
        this.when(notNull).map().setLabel(source.getNombre());
        this.when(notNull).map().setValue(source.getId());
    }
}
