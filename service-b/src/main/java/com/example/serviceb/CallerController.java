package com.example.serviceb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CallerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api/callery")
    public String callServiceA() {
        // Use the logical service name "service-a" to call the service via Eureka registry
        String url = "http://service-a/api/hello";
        String response = restTemplate.getForObject(url, String.class);
        return "Service B called Service A and got response: " + response;
    }
}
