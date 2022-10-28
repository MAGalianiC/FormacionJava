package com.example.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class ClaseSecundaria implements CommandLineRunner {

    //Uso el método de CommandLineRunner y muestro con él un mensaje con println
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola desde clase secundaria");
    }
}

