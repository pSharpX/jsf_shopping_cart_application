package pe.edu.cibertec.mapper.modelToDomain;

import org.modelmapper.PropertyMap;
import pe.edu.cibertec.dominio.Usuario;
import pe.edu.cibertec.model.UsuarioModel;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class UsuarioModelToUsuarioMap extends PropertyMap<UsuarioModel, Usuario> {
    @Override
    protected void configure() {
        this.skip().setId(null);
        this.map().setNombre(source.getNombre());
        this.map().setApellido(source.getApellido());
        this.skip().setFechaNacimiento(null);
    }
}
