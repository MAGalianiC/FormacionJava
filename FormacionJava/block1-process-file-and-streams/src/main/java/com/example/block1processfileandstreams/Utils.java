package com.example.block1processfileandstreams;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Utils {

    public static List<Person> listarTodasPersonas() throws IOException {
        Path path = Paths.get("block1-process-file-and-streams/src/main/resources/people.csv");
        List<String> entireFile = Files.readAllLines(path);
        for (String linea : entireFile){
            System.out.println(linea);
        }
        return new ArrayList<Person>();
    }

    public static void listarConStream() throws IOException {
        String fichero = "block1-process-file-and-streams/src/main/resources/people.csv";

        try (Stream<String> stream = Files.lines(Paths.get(fichero))){
            stream.map(linea -> linea.split(":"))
                    .map(Person::buildFromArray)
                    .mapToInt(o ->o.getEdad())
                    .onClose(() -> System.out.println("termino"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
