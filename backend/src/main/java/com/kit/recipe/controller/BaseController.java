package com.kit.recipe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/")
public class BaseController {


    @GetMapping("/")
    public ResponseEntity<String> sendHelloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/shopping")
    public ResponseEntity<List<String>> answerPaul() {
        List<String> shoppingList = List.of("Tomaten", "Gurke", "Bier");
        return ResponseEntity.ok(shoppingList);
    }



}
