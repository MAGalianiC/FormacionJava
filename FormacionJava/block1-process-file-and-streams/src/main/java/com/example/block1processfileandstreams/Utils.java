package com.example.block1processfileandstreams;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static List<Person> listarTodasPersonas(String path) throws IOException {
        List<String> entireFile = Files.readAllLines(Paths.get(path));
        List<Person> listaDePersonas = new ArrayList<>();
        int numeroFila = 0;
        for (String linea : entireFile){
            List<String> columnas = List.of(linea.split(":"));
            String name = columnas.get(0);
            String town = columnas.get(1);
            if (town.isEmpty()){
                town = "unknown";
            }
            Integer edad = null;
            try {
                edad = Integer.valueOf(columnas.get(2));
            } catch (IndexOutOfBoundsException e){

            }
            Person persona = new Person();
            persona.setName(name);
            persona.setTown(town);
            persona.setEdad(edad);
            listaDePersonas.add(persona);
        }
        return listaDePersonas;
    }

    public static List<Person> listarConFiltroStream(String path) throws IOException {
        List<Person> listaPersonas =  listarTodasPersonas(path);
        System.out.println("LISTA CON FILTRO: ");
        Predicate <Person> predicade1 = (Person p) -> p.getEdad() < 25;
        List<Person> listaFiltrada = listaPersonas.stream()
                .filter(predicade1)
                .collect(Collectors.toList());;
        return listaFiltrada;
    }
}
