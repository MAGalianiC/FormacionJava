package com.example.block1processfileandstreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@SpringBootApplication
public class Block1ProcessFileAndStreamsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Block1ProcessFileAndStreamsApplication.class, args);
		//Creo la variable path para guardar la direccion del fichero que se va a filtrar en toda la aplicación.
		String path = "block1-process-file-and-streams/src/main/resources/people.csv";
		//Creo una variable de tipo Optional para usarla mas tarde en los filtros.
		Optional personaFiltrada;
		//Creo los predicados de los diferentes apartados del ejercicio para usarlos con los métodos luego.
		Predicate<Person> predicate1 = (Person p) -> p.getEdad().isPresent() && p.getEdad().get() < 25 && p.getEdad().get() != 0;
		Predicate<Person> predicate2 = (Person p) -> !p.getName().toLowerCase().startsWith("a");
		Predicate<Person> predicate3 = (Person p) -> (p.getTown(). get().equalsIgnoreCase("madrid"));
		Predicate<Person> predicate4 = (Person p) -> (p.getTown(). get().equalsIgnoreCase("barcelona"));

		//Creo una lista de personas que contendrá la lista de personas que estarán filtradas según dice el apartado A del ejercicio.
		List<Person> filtradoA = Utils.listarConFiltroStream(path, predicate1);

		try {
			System.out.println("Ejercicio A:");
			//Listo solamente las personas cuya edad es menor de 25 años. No imprimo las personas con edad 0.
			Utils.listarConFiltroStream(path, predicate1).forEach(System.out::println);
			System.out.println("Ejercicio B:");
			//Listo todas las personas de la lista cuyo nombre no empiece por la letra "A"
			Utils.listarConFiltroStream(path, predicate2).forEach(System.out::println);
			System.out.println("Ejercicio C:");
			//Listo la primera persona de la lista cuya ciudad sea "Madrid" y que cumplen las condiciones del apartado A.
			personaFiltrada = Utils.filtradoUnElemento(path, filtradoA, predicate3).stream().findFirst();
			//Hago un if para poder devolver que no hay ningún elemento en la lista que cumpla los requisitos que se pieden en el apartado.
			if (personaFiltrada.isPresent()){
				System.out.println(personaFiltrada.get());
			} else {
				System.out.println("No hay ningún elemento en la lista que cumpla los requisitos de la búsqueda");
			}
			System.out.println("Ejercicio D:");
			//Listo la primera persona de la lista cuya ciudad sea "Barcelona" y que cumplen las condiciones del apartado A.
			personaFiltrada = Utils.filtradoUnElemento(path, filtradoA, predicate4).stream().findFirst();
			//Hago un if para poder devolver que no hay ningún elemento en la lista que cumpla los requisitos que se pieden en el apartado.
			if (personaFiltrada.isPresent()){
				System.out.println(personaFiltrada.get());
			} else {
				System.out.println("No hay ningún elemento en la lista que cumpla los requisitos de la búsqueda");
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
