package com.example.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block5CommandLineRunnerApplication {

	//Primero se crean los Componentes de Spring y luego los Beans de Java dentro del contexto de la aplicación de Spring.
	//Se crean primero los Components porque son objetos completos y luego los Beans porque son objetos más ligero.

	//Primero se crean los Components que se encuentran en el contexto de Spring y después se ejecutan los comandos.
	//Se ejecuta primero el PostConstruct porque se ejecuta justo después de que se cree su Component.
	//Luego se ejecuta el resto.

	public static void main(String[] args) {
		SpringApplication.run(Block5CommandLineRunnerApplication.class, args);
	}

	//Se crea un Bean donde se crea una instancia de la clase ClaseInicial para poder llamar al método primerMétodo.
	//Esto se ejecutará después de los Components.
	@Bean
	CommandLineRunner metodoClaseInicial(){
		return p ->{
			ClaseInicial clase = new ClaseInicial();
		};
	}
}
