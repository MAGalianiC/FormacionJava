package com.example.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class ClaseTerciaria implements CommandLineRunner {

    //Uso el método de CommandLineRunner
    @Override
    public void run(String... args) throws Exception {
        //Recojemos aquí los datos que nos da el usuario por parámetros y los mostramos linea a linea con un .forEach
        Arrays.asList(args).forEach(System.out::println);
        System.out.println("Soy la tercera clase");
    }
}
