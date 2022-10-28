package com.example.block5commandlinerunner;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class ClaseInicial{
    //Creamos un método usando el postConstruct

    //Creo el método primerMétodo, usando un PostConstruct para que se ejecute lo primero.
    @PostConstruct
    public void primerMetodo(){
        System.out.println("Hola desde clase inicial");
    }
}