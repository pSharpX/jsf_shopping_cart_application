package pe.edu.cibertec.mapper.domainToModel;

import org.modelmapper.PropertyMap;
import pe.edu.cibertec.dominio.Usuario;
import pe.edu.cibertec.model.UsuarioModel;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class UsuarioToUsuarioModelMap extends PropertyMap<Usuario, UsuarioModel> {
    @Override
    protected void configure() {
        this.map().setNombre(source.getNombre());
        this.map().setApellido(source.getApellido());
    }
}
