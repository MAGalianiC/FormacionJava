package com.example.block6pathvariableheaders;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    //Se crea un template que se usará más adelante para darle forma a los textos que devolverá la aplicación.
    public String template = "Hello, %s!";
    //Se crea un contador para ir sumando a la variable "Id" para que no se repita al crear nuevos "Greetings".
    public AtomicLong counter = new AtomicLong();
    //Se crea una lista de "Greetings" para irlos almacenando.
    public List<Greeting> greetingList = new ArrayList<>();

    //Este método crea y devuelve un objeto de tipo Greeting dándole forma definida al template que creamos al principio.
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    //Este método crea y añade a la lista de Greeting un objeto de tipo Greeting con datos pasados por el body de
    //la petición HTTP.
    @PostMapping(value = "/greeting/json-introduced",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Greeting greetingByBody(@RequestBody Greeting greeting){
        greetingList.add(greeting);
        return greeting;
    }

    //Este método crea y añade a la lista de Greeting un objeto de tipo Greeting con datos pasados por el path de
    //la petición HTTP.
    @GetMapping("/greeting/params-path/{content}")
    public Greeting greetingByPath(@PathVariable String content){
        Greeting greeting = new Greeting(counter.incrementAndGet(), content);
        greetingList.add(greeting);
        return greeting;
    }

    //Este método modifica el "content" del objeto Greeting cuyo "Id" coincida con el "Id" que le pasamos por parámteros
    //a la petición HTTP. En caso de no encontrar en la lista ningún Greeting con el "Id" especificado, se devolverá
    //un String diciéndole al usuario que el "Id" especificado es inválido.
    @PutMapping("/greeting/sending-params")
    public String greetingByParams(@RequestParam Long id, String content){
        for (Greeting greeting : greetingList){
            if (greeting.getId() == id){
                greeting.setContent(content);
                return "The greeting with id " + id + " has this new content --> " + content.toString();
            }
        }
        return "Invalid ID";
    }

    //Este método lista todos los Greetings que haya en "greetingList"
    @GetMapping("greeting/all-greetings")
    public List<Greeting> getGreetingList(){
        return greetingList;
    }
}
