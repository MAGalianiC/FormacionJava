package com.example.block1expressionevaluator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class EvaluadorExpresiones {

    //El método estático evaluateExpression es un método cuyo trabajo es recorrer cada línea identificando que tipo de caracteres hay dentro para poder enviar dicha línea al método de procesar que corresponda.
    //Se le pasa por parámetro un String path con el contenido de la ruta donde se encuentra el archivo que recorre.
    public static void evaluateExpression(String path) throws Exception {
        //Se crea la variable entireFile de tipo Lista de Strings, donde cada String corresponde a una línea del fichero de la ruta proporcionada por el path.
        List<String> entireFile = Files.readAllLines(Paths.get(path));
        //Se crea un for para poder recorrer entireFile línea a línea y poder tratarlas de manera individual en cada iteración del bucle.
        for (String linea : entireFile){
            try {
                //Si la línea contiene comillas dobles (") se entenderá que dentro de la línea se tratará con tipo String.
                if (linea.contains("\"")){
                    //Llamada al método estático procesadorStrings.
                    procesadorStrings(linea);
                //Si la línea tiene el formato exacto de una comparación entre fechas, se tratará como tipo Date.
                } else if (linea.matches("[0-9][0-9][0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9] [< <= > >= =] [0-9][0-9][0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9]$")) {
                    //Llamada al método estático procesadorDates.
                    procesadorDates(linea);
                //Si la línea no cumpliera con lo anterior, pero si que contuviera números, se tratará como tipo Integer.
                } else if (linea.matches(".*\\d.*")){
                    //Llamada al método estático procesadorIntegers.
                    procesadorIntegers(linea);
                }
            //En caso de que la línea que trata la iteración no tuviera coincidencia con ninguno de los casos dados anteriormente, estará mal escrita la línea y saltará un Exception.
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    //El método estático procesadorStrings se encarga de recoger las cadenas de texto, sumarlas si tienen el operador "+" entre ellas, y multiplicarlas si tienen el operador "*" entre una cadena y un número.
    //Se le pasa por parámetro la línea (String) de la iteración correspondiente que nos dá el método evaluateExpression, que ya habría determinado que dicha línea la trataremos como un String.
    public static void procesadorStrings(String lineaString) throws Exception {
        //Creamos un String para ir metiendo cada palabra que sumemos o multipliquemos en la línea para poder mostrarla al final.
        String cadenaProcesada = "";
        //Creamos una Lista de Strings donde vamos a meter tanto las palabras como los operadores, usando la función Split, para poder trabajar con ellos paso a paso.
        List<String> operandos = List.of(lineaString.split("(?<=[-+*/])|(?=[-+*/])"));
        //Esto trataría el caso de que solamente hubiera una sola palabra dentro, devolviendo un Exception si la palabra no estuviera bien formateada.
        if (operandos.size() == 1){
            if (lineaString.trim().matches("[\"]$")){
                throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
            }
            cadenaProcesada = lineaString;
        } else {
            //Creamos un for para ir recorriendo la lista posición por posición.
            for (int i=0; i<operandos.size()-1; i++){
                //Creamos la variable operando, que contendrá el primer operando que viene en la Lista de Strings.
                String operando = operandos.get(i).trim();
                //En el caso de que dicho operando no fuera una palabra, sino un operador, saltará la Exception.
                if (operandos.stream().findFirst().get().equals("+") || operandos.stream().findFirst().get().equals("*")){
                    throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
                } else {
                    //Formateamos operando para quitarle las comillas dobles (") y los espacios que pudiera contener a los lados de la palabra a tratar.
                    operando = operando.replaceAll("\"", "").trim();
                    //En el caso de que operando tenga el formato correcto, o sea el primer String de la Lista, se entrará aquí.
                    if (operando.matches("^[a-zA-ZñÑ]*$") || i>0){
                        //Se crea la variable nextOperando, que recogerá el valor del siguiente puesto de la Lista.
                        String nextOperando = operandos.get(i+1);
                        //Formateamos nextOperando para quitarle las comillas dobles (") y los espacios que pudiera contener a los lados de la palabra a tratar.
                        nextOperando = nextOperando.replaceAll("\"", "").trim();
                        //En caso de que nextOperando sea una suma (+), se entrará aquí.
                        if (nextOperando.equals("+")){
                            //Se crea la variable nextNextOperando, que recogerá el valor siguiente de nextOperando en la Lista.
                            String nextNextOperando = operandos.get(i+2);
                            //Formateamos nextNextOperanndo para quitarle las comillas dobles (") y los espacios que pudiera contener a los lados de la palabra a tratar.
                            nextNextOperando = nextNextOperando.replaceAll("\"", "").trim();
                            //En el caso de que nextNextOperando tenga el formato correcto, o sea el primer String de la Lista, se entrará aquí.
                            if (nextNextOperando.matches("^[a-zA-ZñÑ]*$")){
                                //Si estuviéramos en el primer String de la Lista, se añadiría a la cadenaProcesada.
                                if(i==0) {
                                    cadenaProcesada += operando.concat(nextNextOperando);
                                }else{
                                    cadenaProcesada += nextNextOperando;
                                }
                                i++;

                            //Si hubiera algún fallo de formato en el proceso, saltaría la Excepción correspondiente.
                            } else {
                                throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
                            }
                        //Si en la posición siguiente de operando hubiera un multiplicar (*), se entraría aquí.
                        } else if (nextOperando.equals("*")) {
                            //Se crea la variable nextNextOperando, que recogerá el valor del siguiente puesto de la Lista.
                            String nextNextOperando = operandos.get(i+2).trim();
                            //Si nextNextOperando contiene un número se entrará aquí.
                            if (nextNextOperando.matches("^[0-9]*$")){
                                //Se crea la variable producto, para almacenar el String resultante de la multiplicación que realizaremos.
                                String producto = "";
                                //Hacemos un bucle para repetir el texto tantas veces como se indique en el valor de la multiplicación.
                                for(int j = 0; j < Integer.parseInt(nextNextOperando.trim()); j++){
                                    producto = producto.concat(cadenaProcesada);
                                }
                                //Añadimos dicha multiplicación a la cadenaProcesada.
                                cadenaProcesada = producto;
                                i++;
                                //Si hubiera algún fallo de formato en el proceso, saltaría la Excepción correspondiente.
                            } else {
                                throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
                            }
                        }
                        //Si hubiera algún fallo de formato en el proceso, saltaría la Excepción correspondiente.
                        else {
                            throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
                        }
                    }
                }
            }
        }
        //Finalmente imprimimos por pantalla un mensaje con el contenido de la línea antes de procesarse, seguido, o bien del resultado, o bien del error de que no está bien formateado el texto.
        System.out.println(lineaString + " RESULTADO--> " + cadenaProcesada);
    }

    //El método procesadorDates se encarga de comparar dos fechas, usando las que nos vienen desde el método evaluateExpression, que previamente determinó que el contenido de la misma son de tipo Date.
    //Se le pasa por parámetro la línea (String) de la iteración correspondiente que nos dá el método evaluateExpression, que ya habría determinado que dicha línea la trataremos como un String.
    public static void procesadorDates(String lineaString) throws ParseException {
        //Se crea la variable resultadoComparación de tipo Boolean, para almacenar el resultado de la comparación.
        Boolean resultadoComparacion = false;
        //Se crea un SimpleDateFormat donde indicaremos el formato exacto que deberá usarse en la línea por el Date para poder proceder correctamente a su comparación.
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/mm/dd");
        //Se crea una Lista de Strings donde se guardarán las dos fechas y el operando como Strings, usando la función Split.
        List<String> dates = List.of(lineaString.split("(?<=[<>=])|(?=[<>=])"));
        //Creamos los dos Dates a tratar y les otorgamos el valor correspondiente a cada uno de ellos que hay en la Lista de Strings.
        Date date1 = formatoFecha.parse(dates.get(0));
        Date date2 = formatoFecha.parse(dates.get(2));
        //Creamos un String para almacenar el operador que se usará en la comparativa.
        String operador = dates.get(1);

        //Si el operador es un mayor que (>), se entrará aquí y se hará la comparativa de si Date1 es mayor que Date2, guardando el resultado en la variable resultadoComparación.
        if (operador.equals(">")){
            if (date1.after(date2)){
                resultadoComparacion = true;
            } else {
                resultadoComparacion = false;
            }
            //Si el operador es un menor que (<), se entrará aquí y se hará la comparativa de si Date1 es menor que Date2, guardando el resultado en la variable resultadoComparación.
        } else if (operador.equals("<")) {
            if (date1.before(date2)){
                resultadoComparacion = true;
            } else {
                resultadoComparacion = false;
            }
            //Si el operador es un igual (=), se entrará aquí y se hará la comparativa de si Date1 es igual que Date2, guardando el resultado en la variable resultadoComparación.
        } else {
            if (date1.equals(date2)){
                resultadoComparacion = true;
            } else {
                resultadoComparacion = false;
            }
        }
        //Finalmente imprimimos por pantalla un mensaje con el contenido de la línea antes de procesarse, seguido, o bien del resultado, o bien del error de que no está bien formateado el texto.
        System.out.println(lineaString + " RESULTADO--> " +  resultadoComparacion);
    }

    //El método procesadorIntegers se encarga de realizar operaciones matemáticas con Integers dados por el método evaluateExpression, que ya determinó previamente que era de tipo Integer.
    //Se le pasa por parámetro la línea String que el método determinó que contiene Integers.
    public static void procesadorIntegers(String lineaString) throws Exception{
        //Se crea la variable resultado que contendrá el resultado final de todas las operaciones que luego mostraremos.
        Integer resultado = 0;
        //Se crea una Lista de Strings donde se almacenarán como String cada número y cada operador por separado para poder trabajar con ellos individualmente.
        List<String> operandos = List.of(lineaString.split("(?<=[-+*/])|(?=[-+*/])"));
        //Se crea un for para recorrer la Lista de Strings paso a paso e ir haciendo las operaciones e ir almacenando el resultado.
        for (int i=0; i<operandos.size()-1; i++){
            //Se crea la variable operando para almacenar el valor del primer operando que se tratará.
            String operando = operandos.get(i).trim();
            //Si el primer operando está bien formateado o es el primer valor de la Lista se entrará aquí.
            if (operando.matches(".*\\d.*") || i>0){
                //Se crea la variable nextOperando, donde almacenaremos el siguiente valor de la Lista.
                String nextOperando = operandos.get(i+1).trim();
                //Si nextOperando es un sumar (+), se entrará aquí.
                if (nextOperando.equals("+")){
                    //Se crea la variable nextNextOperando, donde almacenaremos el siguiente valor de la lista.
                    String nextNextOperando = operandos.get(i+2).trim();
                    //Si nextNextOperando está bien formateado, se entrará aquí.
                    if (nextNextOperando.matches(".*\\d.*")){
                        //Si estuviéramos en el primer valor de la Lista de String se entraría aquí.
                        if(i==0) {
                            //Se añade el valor del primer operando a resultado.
                            resultado +=  Integer.parseInt(operando);
                        }
                        //Se suma el valor de nextNextOperando al valor de resultado.
                        resultado = resultado + Integer.parseInt(nextNextOperando);
                        i++;

                        //Si hubiera algún fallo de formato en el proceso, saltaría la Excepción correspondiente.
                    } else {
                        throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
                    }
                    //Si nextOperando es un restar (-), se entrará aquí.
                } else if (nextOperando.equals("-")) {
                    //Se crea la variable nextNextOperando, donde almacenaremos el siguiente valor de la lista.
                    String nextNextOperando = operandos.get(i+2).trim();
                    //Si nextNextOperando está bien formateado, se entrará aquí.
                    if (nextNextOperando.matches(".*\\d.*")){
                        //Si estuviéramos en el primer valor de la Lista de String se entraría aquí.
                        if(i==0) {
                            //Se añade el valor del primer operando a resultado.
                            resultado += resultado + Integer.parseInt(operando);
                        }
                        //Se resta el valor de nextNextOperando al valor de resultado.
                        resultado = resultado - Integer.parseInt(nextNextOperando);
                        i++;

                        //Si hubiera algún fallo de formato en el proceso, saltaría la Excepción correspondiente.
                    } else {
                        throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
                    }
                    //Si nextOperando es un multiplicar (*), se entrará aquí.
                } else if (nextOperando.equals("*")) {
                    //Se crea la variable nextNextOperando, donde almacenaremos el siguiente valor de la lista.
                    String nextNextOperando = operandos.get(i+2).trim();
                    //Si nextNextOperando está bien formateado, se entrará aquí.
                    if (nextNextOperando.matches(".*\\d.*")){
                        //Si estuviéramos en el primer valor de la Lista de String se entraría aquí.
                        if(i==0) {
                            //Se añade el valor del primer operando a resultado.
                            resultado += resultado + Integer.parseInt(operando);
                        }
                        //Se multiplica el valor de nextNextOperando al valor de resultado.
                        resultado = resultado * Integer.parseInt(nextNextOperando);
                        i++;

                        //Si hubiera algún fallo de formato en el proceso, saltaría la Excepción correspondiente.
                    } else {
                        throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
                    }
                    //Si nextOperando es un dividir (/), se entrará aquí.
                } else if (nextOperando.equals("/")) {
                    //Se crea la variable nextNextOperando, donde almacenaremos el siguiente valor de la lista.
                    String nextNextOperando = operandos.get(i+2).trim();
                    //Si nextNextOperando está bien formateado, se entrará aquí.
                    if (nextNextOperando.matches(".*\\d.*")){
                        //Si estuviéramos en el primer valor de la Lista de String se entraría aquí.
                        if(i==0) {
                            //Se añade el valor del primer operando a resultado.
                            resultado += resultado + Integer.parseInt(operando);
                        }
                        //Se divide el valor de nextNextOperando al valor de resultado.
                        resultado = resultado / Integer.parseInt(nextNextOperando);
                        i++;

                        //Si hubiera algún fallo de formato en el proceso, saltaría la Excepción correspondiente.
                    } else {
                        throw new InvalidLineFormatException(lineaString + "--> Formato de línea incorrecto");
                    }
                }
            }
        }
        //Finalmente imprimimos por pantalla un mensaje con el contenido de la línea antes de procesarse, seguido, o bien del resultado, o bien del error de que no está bien formateado el texto.
        System.out.println(lineaString + " RESULTADO--> " +  resultado);
    }
}
