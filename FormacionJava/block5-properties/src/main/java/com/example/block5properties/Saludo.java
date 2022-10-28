package com.example.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Saludo implements CommandLineRunner {

    //Recogemos los valores del archivo Application.yml
    @Value("${greeting}")
    public String greeting;
    @Value("${my.number}")
    public Integer myNumber;
    @Value("${new.property:No tiene valor}")
    public String newProperty;

    //Método de la clase CommandLineRunner
    @Override
    public void run(String... args) throws Exception {
        //Imprime el valor recogido con @Value y guardado en las variables.
        System.out.println("El valor de greeting es:  (" + greeting + ")");
        System.out.println("El valor de my.number es:  (" + myNumber + ")");
        System.out.println("El valor de new.property es:  (" + newProperty + ")");
    }
}
