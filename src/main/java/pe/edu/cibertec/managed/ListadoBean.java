/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.managed;

import pe.edu.cibertec.model.CategoriaModel;
import pe.edu.cibertec.service.CategoriaService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Java-LM
 */
@ManagedBean(name = "listados")
@ApplicationScoped
public class ListadoBean {
    
    private List<SelectItem> generos;
    private List<SelectItem> profesiones;
    private Collection<SelectItem> categorias;

    @Inject
    @Named("categoriaService")
    private CategoriaService categoriaService;

    @PostConstruct
    public void init() {
        Collection<CategoriaModel> categoriaModels = this.categoriaService.listar();
        if(categoriaModels != null && categoriaModels.size() > 0){
            categorias = categoriaModels.stream().map((categoriaModel) -> {
                SelectItem selectItem = new SelectItem();
                selectItem.setValue(categoriaModel.getId());
                selectItem.setLabel(categoriaModel.getNombre());
                return  selectItem;
            }).collect(Collectors.toList());
        }
    }

    public ListadoBean() {
        generos = new ArrayList<>();
        generos.add(new SelectItem("M", "Masculino"));
        generos.add(new SelectItem("F", "Femenino"));
        
        profesiones = new ArrayList<>();
        profesiones.add(new SelectItem("001", "Arquitecto"));
        profesiones.add(new SelectItem("002", "Ingeniero"));
    }

    public List<SelectItem> getGeneros() {
        return generos;
    }

    public void setGeneros(List<SelectItem> generos) {
        this.generos = generos;
    }

    public List<SelectItem> getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(List<SelectItem> profesiones) {
        this.profesiones = profesiones;
    }

    public Collection<SelectItem> getCategorias() {
        return categorias;
    }

    public void setCategorias(Collection<SelectItem> categorias) {
        this.categorias = categorias;
    }

    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
}
