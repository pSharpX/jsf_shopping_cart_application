package pe.edu.cibertec.service;

import pe.edu.cibertec.model.CategoriaModel;

import javax.faces.model.SelectItem;
import java.util.Collection;

/**
 * Created by CHRISTIAN on 17/04/2018.
 */
public interface CategoriaService {
    Collection<CategoriaModel> listar();
    Collection<SelectItem> combo();
    CategoriaModel obtener(Long id);
}
