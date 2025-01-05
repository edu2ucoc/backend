package org.example.server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class ApiController {
    @GetMapping("/echo")
    public EchoDto index() {
        return EchoDto.builder().code(1).message("helloworld").build();
    }
}
