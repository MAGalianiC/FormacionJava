package com.example.block5profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Profile implements CommandLineRunner {

    //Se usa la anotación @Value para decirle al programa que el valor que tendrá "perfil" será el spring.profiles.active
    //que corresponda al valor dado por el configurations.
    @Value("${spring.profiles.active}")
    public String perfil;

    //Se usa la anotación @Value para decirle al programa que el valor que tendrá "bdUrl" será el bd.url
    //que corresponda al valor dado por el configurations.
    @Value("${bd.url}")
    public String bdUrl;

    //Ejecutamos el Run de CommandLineRunner y dentro hacemos que imprima el perfil en el que nos encontramos con su correspondiente
    //url de la base de datos.
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Este es el perfil -> " + perfil);
        System.out.println(bdUrl);
    }
}
