package com.example.block1processfileandstreams;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {

    //Método que lista y guarda todas las personas de un archivo CSV que cumplan una serie de requisitos.
    //Al método se le pasa un String con el path del archivo del cual queremos extraer la lista de Personas
    //El método devuelve una lista de personas que cumplen los requisitos
    public static List<Person> listarTodasPersonas(String path) throws IOException{
        //Guardo en una lista de Strings el contenido del fichero CSV separando cada String por cada línea distinta en el fichero (Por cada Enter).
        List<String> entireFile = Files.readAllLines(Paths.get(path));
        //Creo una lista de Personas para usarla en los diferentes métodos.
        List<Person> listaDePersonas = new ArrayList<>();

        //Integer contadorLineas = 0; //Creado para mostrarlo y embellecer la impresión en caso de querer hacerlo.

        //Creo un For para recorrerme entire file
        for (String linea : entireFile){
            //Creo la variable "columnas" para poder ir guardando dentro un array de Strings que corresponda
            //a cada línea del fichero, para poder separarlos en diferentes Strings usando Split.
            List<String> columnas = List.of(linea.split(":"));
            //Guardo en el String "name" el valor de cada primer elemento de la lista, que siempre va a contener el nombre.
            String name = columnas.get(0);
            try {
                //Nombre es obligatorio, si el campo "name" está vacío lanzo la excepción y no guardo a esa persona.
                if (name.isEmpty()){
                    throw new InvalidLineFormatException("El campo Name es obligatorio");
                }
                String town = "";
                try{
                    town= columnas.get(1);
                } catch (Exception e){
                    throw new InvalidLineFormatException("El campo Town es desconocido");
                }
                    Integer edad = 0;
                    try {
                        edad = Integer.valueOf(columnas.get(2));
                    } catch (Exception e){
                        throw new InvalidLineFormatException("El campo Edad es desconocido");
                    }
                    Person persona = new Person();

                    //Guardo los datos recogidos en cada línea en su correspondiente lugar para guardarlos en una Persona y añadirlos a la lista posteriormente.
                    persona.setName(name);
                    persona.setTown(town);
                    persona.setEdad(edad);

                    //Creo un Optional para tratar correctamente los elementos opcionales de la clase Person.
                    Optional<String> optionalPerson = Optional.ofNullable(name);

                    //Si la persona que estoy intentando crear tuviera el campo name null, no se añadiria a la lista de personas.
                   if (optionalPerson.isPresent()){
                      // System.out.println("["+contadorLineas+"="+linea+"] -> " +persona); // Este print me enseña la lista del fichero bien ordenadito.
                       listaDePersonas.add(persona);
                    } else {
                        throw new InvalidLineFormatException("El campo Nombre es obligatorio");
                    }
            } catch (InvalidLineFormatException e){
                //System.out.println("["+contadorLineas+"="+linea+"] -> " + e.getMessage()); // Este print me enseña los fallos de cada línea del fichero que no están bien puestos.
            }
            //contadorLineas++; // Añadido para embellecer la lista en caso de querer mostrarla.
        }
        //Devuelvo la lista de personas que contienen el campo obligatorio "name"
        return listaDePersonas;
    }

    //Método para filtrar una lista de Personas obteniendo un predicado.
    //Al método se le pasa un String con el path del fichero CSV y un Predicate que contenga la lógica a implementar en el filtrado.
    //El método devuelve una lista con todos los elementos tipo Person que pasaron el filtro dado por el Predicate.
    public static List<Person> listarConFiltroStream(String path, Predicate<Person> predicate) throws IOException {
        //Guardo la lista de personas generada en el método listar todas las personas en una variable tipo lista de Person para tratarla en el método.
        List<Person> listaPersonas =  listarTodasPersonas(path);
        //Hago un Stream con la lista de personas, le aplico un filter con el predicade que me pasan al método como filtro y lo devuelto como una lista con la clase Collector.
        List<Person> listaFiltrada = listaPersonas.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        //Devuelvo la lista filtrada.
        return listaFiltrada;
    }

    //Método para filtrar un elemento de la lista de personas.
    //Al método se le pasa un String con el path del fichero CSV, Una lista, filtrada o prefiltrada, de Personas sobre la que aplicar el filtro y el predicado con el filtro que queramos aplicar.
    //El método devuelve una Persona resultado del filtro.
    public static Optional filtradoUnElemento(String path, List<Person> listaFiltrada, Predicate<Person> predicate) throws IOException {
        Optional personaFiltrada;
        personaFiltrada = listaFiltrada.stream().filter(predicate).findFirst();
        return personaFiltrada;
    }
}
