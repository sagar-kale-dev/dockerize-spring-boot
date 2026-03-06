package co.in.sagarkale.dockerize_spring_boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {
    @GetMapping
    public String helloWorld(){
        return "Congratulations! You have dockerized Spring Boot app successfully!";
    }
}
