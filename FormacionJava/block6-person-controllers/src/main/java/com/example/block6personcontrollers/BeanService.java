package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class BeanService {

    //Aquí se crea un objeto tipo Persona con un Qualifier para poder darle un valor al objeto.
    @Qualifier("bean1")
    public Persona bean1;
    //Aquí se crea un objeto tipo Persona con un Qualifier para poder darle un valor al objeto.
    @Qualifier("bean2")
    public Persona bean2;
    //Aquí se crea un objeto tipo Persona con un Qualifier para poder darle un valor al objeto.
    @Qualifier("bean3")
    public Persona bean3;

    //Método que se encarga de asignar valores predefinidos al objeto Bean creado anteriormente.
    @Bean
    @Qualifier("bean1")
    public Persona createBean1(){
        return bean1 = new Persona("Rodrigous", 27, "Sevilla");
    }
    //Método que se encarga de asignar valores predefinidos al objeto Bean creado anteriormente.
    @Bean
    @Qualifier("bean2")
    public Persona createBean2(){
        return bean2 = new Persona("Esther", 23, "Sevilla Este");
    }
    //Método que se encarga de asignar valores predefinidos al objeto Bean creado anteriormente.
    @Bean
    @Qualifier("bean3")
    public Persona createBean3(){
        return bean3 = new Persona("Jose", 35, "Logroño");
    }

    //Método que se encarga de revisar el path que se recoge del http request y nos devuelve el Bean cuyo Qualifier coincida con el valor dado en la petición.
    public Persona getBean(String bean) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Field selectedBean = Class.forName("com.example.block6personcontrollers.BeanService").getDeclaredField(bean);
        Persona persona = (Persona) selectedBean.get(this);
        return persona;
    }

}
