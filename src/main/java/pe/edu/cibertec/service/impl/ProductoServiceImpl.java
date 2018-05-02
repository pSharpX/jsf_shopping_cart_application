package pe.edu.cibertec.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.mapper.domainToModel.ProductoToProductoModelMap;
import pe.edu.cibertec.mapper.modelToDomain.ProductoModelToProductoMap;
import pe.edu.cibertec.model.ProductoModel;
import pe.edu.cibertec.producer.ModelMapperProducer;
import pe.edu.cibertec.producer.ProductoRepositorioProducer;
import pe.edu.cibertec.repositorio.ProductoRepositorio;
import pe.edu.cibertec.service.ProductoService;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by CHRISTIAN on 14/04/2018.
 */
//@Named("productoService")
@Stateless(name = "productoService")
public class ProductoServiceImpl implements ProductoService {

    @Inject
    @ModelMapperProducer
    private ModelMapper mapper;

    @Inject
    @ProductoRepositorioProducer
    private ProductoRepositorio productoRepositorio;

    public ProductoServiceImpl(){
    }

    @PostConstruct
    public void init(){
        this.mapper.addMappings(new ProductoModelToProductoMap());
        this.mapper.addMappings(new ProductoToProductoModelMap());
        this.mapper.validate();
    }

    @Override
    public Collection<ProductoModel> listar() {
        List<Producto> _productos = this.productoRepositorio.obtenerTodos();
        if(_productos == null || _productos.size() == 0)
            return null;
        //Type type = new TypeToken<List<ProductoModel>>(){}.getType();
        //List<ProductoModel> _productoModels = this.mapper.map(_productos, type);
        Function<Producto,ProductoModel> mapper = (p) -> fromProductoToProductoModel(p);
        List<ProductoModel> _productoModels = _productos.stream().map(mapper).collect(Collectors.toList());
        return _productoModels;
    }

    @Override
    public Collection<ProductoModel> listarPorCategoria(Long idCategoria) {
        List<Producto> _productos = this.productoRepositorio.obtenerPorCategoriaCriteriaApi(idCategoria);
        if(_productos == null || _productos.size() == 0)
            return null;
        Type type = new TypeToken<List<ProductoModel>>(){}.getType();
        List<ProductoModel> _productoModels = this.mapper.map(_productos, type);
        return _productoModels;
    }

    @Override
    public ProductoModel obtener(Long id) {
        Producto producto = this.productoRepositorio.buscar(id);
        if(producto == null)
            return null;
        return fromProductoToProductoModel(producto);
    }

    @Override
    @org.mybatis.cdi.Transactional
    public ProductoModel crear(ProductoModel productoModel) {
        Producto producto = fromProductoModelToProducto(productoModel);
        this.productoRepositorio.crear(producto);
        return  productoModel;
    }

    @Override
    public ProductoModel actualizar(ProductoModel productoModel) {
        Producto producto = fromProductoModelToProducto(productoModel);
        this.productoRepositorio.actualizar(producto);
        return productoModel;
    }

    @Override
    public boolean eliminar(ProductoModel producto) {
        return false;
    }

    public ModelMapper getMapper() {
        return mapper;
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProductoRepositorio getProductoRepositorio() {
        return productoRepositorio;
    }

    public void setProductoRepositorio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    private ProductoModel fromProductoToProductoModel(Producto p){
        ProductoModel productoModel = new ProductoModel();
        productoModel.setId(p.getId());
        productoModel.setNombre(p.getNombre());
        productoModel.setDescripcion(p.getDescripcion());
        productoModel.setPrecio(p.getPrecio().doubleValue());
        productoModel.setImagen(p.getImagen());
        if(p.getCategoria() != null){
            productoModel.setCategoria(p.getCategoria().getNombre());
            productoModel.setIdCategoria(p.getCategoria().getId());
        }
        return productoModel;
    }

    private Producto fromProductoModelToProducto(ProductoModel productoModel){
        Producto producto = new Producto();
        producto.setId(productoModel.getId());
        producto.setNombre(productoModel.getNombre());
        producto.setDescripcion(productoModel.getDescripcion());
        producto.setPrecio(new BigDecimal(productoModel.getPrecio()));
        producto.setImagen(productoModel.getImagen());
        if(productoModel.getIdCategoria() != null && productoModel.getIdCategoria() != 0){
            Categoria categoria = new Categoria();
            categoria.setId(productoModel.getIdCategoria());
            categoria.setNombre(productoModel.getCategoria());
            producto.setCategoria(categoria);
        }
        return producto;
    }
}
