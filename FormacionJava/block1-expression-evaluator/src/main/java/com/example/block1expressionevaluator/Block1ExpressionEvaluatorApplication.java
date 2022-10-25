package com.example.block1expressionevaluator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Block1ExpressionEvaluatorApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Block1ExpressionEvaluatorApplication.class, args);

		//Creamos el string path que va a contener la ruta donde se encuentra el archivo de texto que vamos a evaluar
		String path = "block1-expression-evaluator/src/main/resources/Fichero.csv";

		//Llamamos al método evaluateExpression de la clase EvaluadorExpresiones y le pasamos el String con la ruta para que la linkee
		EvaluadorExpresiones.evaluateExpression(path);
	}
}
