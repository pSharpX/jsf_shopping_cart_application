package pe.edu.cibertec.util;

import org.modelmapper.ModelMapper;
import pe.edu.cibertec.producer.ModelMapperProducer;

import javax.enterprise.inject.Produces;

/**
 * Created by CHRISTIAN on 15/04/2018.
 */
public class ModelMapperFactory {

    @Produces
    @ModelMapperProducer
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }
}
