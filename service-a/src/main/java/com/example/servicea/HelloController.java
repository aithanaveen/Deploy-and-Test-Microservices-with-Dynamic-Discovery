package com.example.servicea;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello from Service A!";
    }
}
