package com.example.block1processfileandstreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Block1ProcessFileAndStreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block1ProcessFileAndStreamsApplication.class, args);
		String path = "block1-process-file-and-streams/src/main/resources/people.csv";
		/*try {
			Utils.listarTodasPersonas(path).forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}*/
		try {
			Utils.listarConFiltroStream(path).forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Ejecutado correctamente");
	}
}
