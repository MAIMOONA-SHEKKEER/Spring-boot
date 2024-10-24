package com.example.registration_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class DocumentController {

    @GetMapping("/document")
    public String generateDocument(
        @RequestParam String userName, 
        Model model
    ) {
        // Sample Data
        List<Map<String, Object>> items = List.of(
            Map.of("name", "Item 1", "description", "Description 1", "quantity", 2, "price", 20.00),
            Map.of("name", "Item 2", "description", "Description 2", "quantity", 5, "price", 50.00)
        );

        // Add data to the model
        model.addAttribute("userName", userName);
        model.addAttribute("date", LocalDate.now().toString());
        model.addAttribute("items", items);

        return "document"; // Corresponds to the 'document.html' template
    }
}